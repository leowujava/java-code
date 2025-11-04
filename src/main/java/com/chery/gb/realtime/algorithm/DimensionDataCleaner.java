package com.chery.gb.realtime.algorithm;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.enums.SignalGroupEnum;
import com.chery.gb.realtime.algorithm.util.*;
import com.chery.gb.realtime.algorithm.util.check.NewGBRuleDataCheckUtil;
import com.chery.gb.realtime.algorithm.util.check.CheckRuleDataByHandNewUtil;
import com.chery.gb.realtime.algorithm.util.check.CheckRuleDataItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.state.*;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.KeyedBroadcastProcessFunction;
import org.apache.flink.util.Collector;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DimensionDataCleaner extends KeyedBroadcastProcessFunction<String, String, String, String> {

    private final MapStateDescriptor<String, Map<String, List<String>>> dimensionDescriptor;

    private ListState<String> actionState;

    public DimensionDataCleaner(
            MapStateDescriptor<String, Map<String, List<String>>> dimensionDescriptor) {
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
//            Map<String,Object> tboxDataMap = new HashMap<>();
            //转成map
//            tboxDataToMap(tboxDataMapTmp,tboxDataMap);
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
        BroadcastState<String, Map<String, List<String>>> dimensionState = ctx.getBroadcastState(dimensionDescriptor);
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

    private void handleHandRules(String op, JSONObject afterData, BroadcastState<String, Map<String, List<String>>> dimensionState) throws Exception {
        String ruleCode = "hand-" + afterData.getString("rule_id") + "";
        String id = afterData.getInteger("id") + "";
        String signalId = afterData.getString("signal_id");
        Map<String, List<String>> detailMap = dimensionState.get(ruleCode);
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
                List<String> dataList = detailMap.get(signalId);
                if (Objects.nonNull(dataList)) {
                    dataList.add(afterData.toJSONString());
                    detailMap.put(signalId, dataList);
                } else {
                    dataList = new ArrayList<>();
                    dataList.add(afterData.toJSONString());
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

    private void handleGBRules(String op, JSONObject afterData, BroadcastState<String, Map<String, List<String>>> dimensionState) throws Exception {
        String ruleCode = "gb-" + afterData.getInteger("rule_id");
        String signalId = afterData.getString("signal_id");
        Map<String, List<String>> detailMap = dimensionState.get(ruleCode);
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
                List<String> dataList = detailMap.get(signalId);
                if (Objects.nonNull(dataList)) {
                    dataList.add(afterData.toJSONString());
                    detailMap.put(signalId, dataList);
                } else {
                    dataList = new ArrayList<>();
                    dataList.add(afterData.toJSONString());
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
            return null;
        }
        JSONArray retData = new JSONArray();

        //采集时间校验，车辆数据采集时间格式无效或未上传。280
        Object ct = signalMap.get("ct");
        if (Objects.isNull(ct) || ct.toString().length() < 13) {
            return RetDataUtil.buildResult("280", result);
        }

        //整车数据 287
        if (!CheckRuleDataItem.checkByGroup(signalMap, SignalGroupEnum.HOLE_VEHICLE)) {
            return RetDataUtil.buildResult("287", result);
        }

        //车辆位置 288
        if (!CheckRuleDataItem.checkByGroup(signalMap, SignalGroupEnum.VEHICLE_POS)) {
            return RetDataUtil.buildResult("288", result);
        }

        //极值数据 289
        if (!CheckRuleDataItem.checkByGroup(signalMap, SignalGroupEnum.ENGINE)) {
            return RetDataUtil.buildResult("289", result);
        }

        //报警数据 290
        if (!CheckRuleDataItem.checkByGroup(signalMap, SignalGroupEnum.ALERT)) {
            return RetDataUtil.buildResult("290", result);
        }

        //上传实时报文的报文时间与服务器接收的标准时间误差超过 30 秒。113
        CheckRuleDataItem.check113(signalMap, retData);

        //实时数据时间与服务器时间相差超过 180 秒 269
        CheckRuleDataItem.check269(signalMap, retData);

        CheckRuleDataItem.checkCode266(signalMap, retData);
        CheckRuleDataItem.checkCode135(signalMap, retData);
        //121 单体电压精确度不足
        CheckRuleDataItem.check121(signalMap, retData);
        //281 经度精确度不足
        CheckRuleDataItem.check281(signalMap, retData);
        //282 纬度精确度不足
        CheckRuleDataItem.check282(signalMap, retData);
        //判断是否是三级告警
        Object highestAlertLevelObj = signalMap.get("220B");
        if (Objects.nonNull(highestAlertLevelObj) && Integer.parseInt(highestAlertLevelObj.toString()) == 3) {
            result.put("type", 3);//type:3 三级告警报文
            Map<String, Object> valueMap = new HashMap<>();
            valueMap.put("vin", vin);
            Object ct2 = signalMap.get("ct");
            valueMap.put("ct", ct2);
            valueMap.put("st", signalMap.get("st"));
            Iterable<String> strings = actionState.get();
            if (CollUtil.isNotEmpty(strings)) {
                long maxCt = 0L;
                int size = 0;
                for (String string : strings) {
                    JSONObject jsonObject = JSON.parseObject(string);
                    Long ct1 = jsonObject.getLong("ct");
                    if (ct1 > maxCt) {
                        maxCt = ct1;
                    }
                    size++;
                }
                long l = Long.valueOf(ct2.toString()) - maxCt;
                if (l >= 2000) {
                    Map<String, Object> gbValueMap = new HashMap<>();
                    gbValueMap.put("size", size);
                    retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("129", gbValueMap))));
                    actionState.clear();
                }
            }
            actionState.add(JSON.toJSONString(valueMap));
            System.out.println(JSON.toJSONString(actionState.get()));
        } else {
            Iterable<String> strings = actionState.get();
            int size = 0;
            Set<String> set = new HashSet<>();
            for (String str : strings) {
                JSONObject jsonObject = JSON.parseObject(str);
                size++;
                set.add(jsonObject.getString("ct"));
            }
            if (size > 0 && size < 31) {
                Map<String, Object> gbValueMap = new HashMap<>();
                gbValueMap.put("size", size);
                retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("129", gbValueMap))));
            }
            if (size > 31) {
                List<String> list = set.stream().sorted().collect(Collectors.toList());
                String ctStr = null;
                for (String s : list) {
                    if (ctStr == null) {
                        ctStr = s;
                    } else {
                        long l = Long.parseLong(s) - Long.parseLong(ctStr);
                        ctStr = s;
                        if (l >= 2000) {
                            Map<String, Object> gbValueMap = new HashMap<>();
                            gbValueMap.put("size", size);
                            retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("129", gbValueMap))));
                            break;
                        }
                    }
                }
            }
            actionState.clear();
        }
        Map<String, Map<String, List<String>>> ruleMap = ruleDetailDataTransToMap(ctx);

        //国标数据检测
        NewGBRuleDataCheckUtil.checkData(signalMap, getRuleMap(ruleMap, "gb-"), retData);
        //手动添加规则检测
        CheckRuleDataByHandNewUtil.checkData(signalMap, getRuleMap(ruleMap, "hand-"), retData);
        //校验规则数据
        result.put("data", retData);
        result.put(SignalEnum.SIGNAL_1BC2.getCode(), signalMap.get(SignalEnum.SIGNAL_1BC2.getCode()));
        result.put(SignalEnum.SIGNAL_2009.getCode(), signalMap.get(SignalEnum.SIGNAL_2009.getCode()));
        return result;
    }

    private Map<String, Map<String, List<String>>> getRuleMap(Map<String, Map<String, List<String>>> ruleMap, String ruleType) {
        Map<String, Map<String, List<String>>> dataMap = new HashMap<>();
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
    private Map<String, Map<String, List<String>>> ruleDetailDataTransToMap(KeyedBroadcastProcessFunction<String, String, String, String>.ReadOnlyContext ctx) throws Exception {
        Map<String, Map<String, List<String>>> ruleDetailMap = new HashMap<>();
        ReadOnlyBroadcastState<String, Map<String, List<String>>> broadcastState = ctx.getBroadcastState(dimensionDescriptor);
        for (Map.Entry<String, Map<String, List<String>>> immutableEntry : broadcastState.immutableEntries()) {
            String entryKey = immutableEntry.getKey();
            Map<String, List<String>> entryValue = immutableEntry.getValue();
            ruleDetailMap.put(entryKey, entryValue);
        }
        return ruleDetailMap;
    }

}
