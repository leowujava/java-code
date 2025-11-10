package com.chery.gb.realtime.algorithm;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.util.*;
import com.chery.gb.realtime.algorithm.util.check.NewGBRuleDataCheckUtil;
import com.chery.gb.realtime.algorithm.util.check.CheckRuleDataByHandNewUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.state.*;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.KeyedBroadcastProcessFunction;
import org.apache.flink.util.Collector;

import java.util.*;

@Slf4j
public class DimensionDataCleaner extends KeyedBroadcastProcessFunction<String, String, String, String> {

    private final MapStateDescriptor<String, Map<String, List<RuleDetailBO>>> dimensionDescriptor;

    private ListState<String> actionState;

    public DimensionDataCleaner(
            MapStateDescriptor<String, Map<String, List<RuleDetailBO>>> dimensionDescriptor) {
        this.dimensionDescriptor = dimensionDescriptor;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        ListStateDescriptor<String> descriptor =
                new ListStateDescriptor<>("actions", String.class);
        actionState = getRuntimeContext().getListState(descriptor);
    }

    /***
     * 主流（kafka)业务数据处理
     * */
    @Override
    public void processElement(String value, KeyedBroadcastProcessFunction<String, String, String, String>.ReadOnlyContext ctx, Collector<String> collector) throws Exception {
        try {
            JSONObject businessData = JSONObject.parseObject(value);
            String vin = businessData.getString("vin");
            if (vin == null || "".equals(vin)) {
                return;
            }

            System.out.println(DateUtil.now() + " 接受kafka处理前的数据:" + businessData.toJSONString());

            Map<String, Object> tboxDataMapTmp = JSON.parseObject(value);
            //处理偏移量
            SignalUtil.convert(tboxDataMapTmp);
            System.out.println(DateUtil.now() + " 接受kafka处理后的数据:" + JSONObject.toJSONString(tboxDataMapTmp));
            JSONObject cleanedData = cleanData(tboxDataMapTmp, ctx);

            // 数据清洗和关联
            if (cleanedData != null && !cleanedData.isEmpty()) {
                System.out.println(DateUtil.now() + " 清洗后的结果:" + cleanedData.toJSONString() + "\n");
                collector.collect(cleanedData.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(DateUtil.now() + " 清洗数据异常:" + e);
        }

    }

    /***
     * 维度数据流处理
     * */
    @Override
    public void processBroadcastElement(String value, KeyedBroadcastProcessFunction<String, String, String, String>.Context ctx, Collector<String> collector) throws Exception {
        JSONObject json = JSONObject.parseObject(value);

        //维度数据map
        BroadcastState<String, Map<String, List<RuleDetailBO>>> dimensionState = ctx.getBroadcastState(dimensionDescriptor);
        String op = json.getString("op");
        if (json.get("after") != null && op != null) {
            JSONObject afterData = json.getJSONObject("after");
            //表来源为空，不做处理
            JSONObject source = json.getJSONObject("source");
            if (source == null || source.isEmpty()) {
                return;
            }
            //表名为空，不做处理
            String tableName = source.getString("table");
            if (tableName == null || tableName.isEmpty()) {
                return;
            }
            //国标类型为空或新国标的规则，本项目目前不做处理
            Object gbType = afterData.get("gb_type");
            if (gbType == null || !"NEW".equals(gbType.toString())) {
                return;
            }

            if (tableName.equals("gb_data_admin_rule_detail")) {
                Object dealDataType = afterData.get("deal_data_type");
                if (dealDataType != null && "realtime".equals(dealDataType.toString())) {
                    //创建方式: GB导入，HAND手动创建
                    String resource = afterData.getString("source_from");
                    if ("GB".equals(resource)) {
                        handleGBRules(op, afterData, dimensionState);
                    } else if ("HAND".equals(resource)) {
                        handleHandRules(op, afterData, dimensionState);
                    }
                }
            }
        }
    }

    private void handleHandRules(String op, JSONObject afterData, BroadcastState<String, Map<String, List<RuleDetailBO>>> dimensionState) throws Exception {
        String ruleCode = "hand-" + afterData.getString("rule_id") + "";
        String id = afterData.getInteger("id") + "";
        String signalId = afterData.getString("signal_id");
        Map<String, List<RuleDetailBO>> detailMap = dimensionState.get(ruleCode);
//        String key = ruleCode+"_"+id;
        if (detailMap == null || detailMap.isEmpty()) {
            detailMap = new HashMap<>();
        }
        if ("c".equals(op) || "u".equals(op) || "r".equals(op)) {
            int isDelete = afterData.getInteger("is_deleted");
            if (isDelete == 1) {
                // 删除维度数据
                detailMap.remove(signalId);
                if (detailMap.isEmpty()) {
                    dimensionState.remove(ruleCode);
                }
            } else {
                List<RuleDetailBO> dataList = detailMap.get(signalId);
                if (Objects.nonNull(dataList)) {
                    dataList.add(JSON.parseObject(afterData.toJSONString(), RuleDetailBO.class));
                    detailMap.put(signalId, dataList);
                } else {
                    dataList = new ArrayList<>();
                    dataList.add(JSON.parseObject(afterData.toJSONString(), RuleDetailBO.class));
                    detailMap.put(signalId, dataList);
                }
                // 插入或更新维度数据
                dimensionState.put(ruleCode, detailMap);

            }
        } else if ("d".equals(op)) {
            // 删除维度数据
            detailMap.remove(signalId);
            if (detailMap.isEmpty()) {
                dimensionState.remove(ruleCode);
            }
        }
    }

    private void handleGBRules(String op, JSONObject afterData, BroadcastState<String, Map<String, List<RuleDetailBO>>> dimensionState) throws Exception {
        String ruleCode = "gb-" + afterData.getInteger("rule_id");
        String signalId = afterData.getString("signal_id");
        Map<String, List<RuleDetailBO>> detailMap = dimensionState.get(ruleCode);
        if (CollUtil.isEmpty(detailMap)) {
            detailMap = new HashMap<>();
        }
        if ("c".equals(op) || "u".equals(op) || "r".equals(op)) {
            int isDelete = afterData.getInteger("is_deleted");
            if (isDelete == 1) {
                // 删除维度数据
                detailMap.remove(signalId);
                if (detailMap.isEmpty()) {
                    dimensionState.remove(ruleCode);
                }
            } else {
                List<RuleDetailBO> dataList = detailMap.get(signalId);
                if (Objects.nonNull(dataList)) {
                    dataList.add(JSON.parseObject(afterData.toJSONString(), RuleDetailBO.class));
                    detailMap.put(signalId, dataList);
                } else {
                    dataList = new ArrayList<>();
                    dataList.add(JSON.parseObject(afterData.toJSONString(), RuleDetailBO.class));
                    detailMap.put(signalId, dataList);
                }
                // 插入或更新维度数据
                dimensionState.put(ruleCode, detailMap);
            }
        } else if ("d".equals(op)) {
            // 删除维度数据
            detailMap.remove(signalId);
            if (detailMap.isEmpty()) {
                dimensionState.remove(ruleCode);
            }
        }
    }

    // 数据清洗逻辑
    private JSONObject cleanData(Map<String, Object> signalMap, KeyedBroadcastProcessFunction<String, String, String, String>.ReadOnlyContext ctx) throws Exception {
        JSONObject result = new JSONObject();

        Object vin = signalMap.get("vin");
        result.put("vin", vin);
        result.put("ct", signalMap.get("ct"));
        result.put("st", signalMap.get("st"));

        //只处理实时报文和补发报文 1:登入,2:实时，3:补发,4:登出
        Object command = signalMap.get("command");
        if (command == null || StringUtils.isBlank(command.toString())) {
            System.out.println("报文command 为 null");
            return null;
        }
        int colType = Integer.parseInt(command.toString());
        if (colType != 2 && colType != 3) {
            System.out.println("报文类型不对：" + colType);
            return null;
        }
        JSONArray retData = new JSONArray();
        Map<String, Map<String, List<RuleDetailBO>>> ruleMap = ruleDetailDataTransToMap(ctx);
        //新国标数据检测
        NewGBRuleDataCheckUtil.checkDataFromWorkFlow(signalMap, getRuleMap(ruleMap, "gb-"), retData);
        //手动添加规则检测
        CheckRuleDataByHandNewUtil.checkData(signalMap, getRuleMap(ruleMap, "hand-"), retData);
        //校验规则数据
        result.put("data", retData);
        return result;
    }

    private Map<String, Map<String, List<RuleDetailBO>>> getRuleMap(Map<String, Map<String, List<RuleDetailBO>>> ruleMap, String ruleType) {
        Map<String, Map<String, List<RuleDetailBO>>> dataMap = new HashMap<>();
        ruleMap.forEach((code, data) -> {
            if (code.startsWith(ruleType)) {
                dataMap.put(code.replace(ruleType, ""), data);
            }
        });
        return dataMap;
    }


    /**
     * 规则数据转Map
     */
    private Map<String, Map<String, List<RuleDetailBO>>> ruleDetailDataTransToMap(KeyedBroadcastProcessFunction<String, String, String, String>.ReadOnlyContext ctx) throws Exception {
        Map<String, Map<String, List<RuleDetailBO>>> ruleDetailMap = new HashMap<>();
        ReadOnlyBroadcastState<String, Map<String, List<RuleDetailBO>>> broadcastState = ctx.getBroadcastState(dimensionDescriptor);
        for (Map.Entry<String, Map<String, List<RuleDetailBO>>> immutableEntry : broadcastState.immutableEntries()) {
            String entryKey = immutableEntry.getKey();
            Map<String, List<RuleDetailBO>> entryValue = immutableEntry.getValue();
            ruleDetailMap.put(entryKey, entryValue);
        }
        return ruleDetailMap;
    }

}
