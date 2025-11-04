package com.chery.gb.realtime.algorithm.util;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.RuleRelationEnum;

import java.util.*;

/**
 * @author zhonghua
 * @date 2025/6/18
 */
public class RetDataBuild {

    /**
     * 设置规则数据
     * @param ruleCode 规则代码
     * @param ruleMap 规则详情数据
     * @param retData 设置数据
     */
    public static boolean build(String ruleCode, Map<String,Object> signalMap, Map<String,Map<String, List<String>>> ruleMap, JSONArray retData){
        try {
            Map<String, List<String>> rMap = ruleMap.get(ruleCode);
            if (CollUtil.isEmpty(rMap)) {
                return true;
            }

            List<RuleDetailBO> detailsByOr = new ArrayList<>();
            List<RuleDetailBO> detailsByAnd = new ArrayList<>();

            rMap.forEach((signalId, ruleObjs) -> {
                for (String data : ruleObjs) {
                    RuleDetailBO ruleDetailBO = JSONObject.parseObject(data, RuleDetailBO.class);
                    Object signalValue = signalMap.get(signalId);
                    if (Objects.isNull(signalValue)) {
                        System.out.println("规则代码:"+ruleDetailBO.getRuleId()+",信号未上传:"+signalId);
                        continue;
                    }
                    ruleDetailBO.setVinSignalValue(signalValue);

                    //2个信号值之间比较
                    if(ruleDetailBO.getCompareType() == 1){
                        Object calcSignalValue = signalMap.get(ruleDetailBO.getSignalValue());
                        if (Objects.isNull(calcSignalValue)) {
                            System.out.println("规则代码:"+ruleDetailBO.getRuleId()+",信号未上传:"+ruleDetailBO.getSignalValue());
                            continue;
                        }
                        ruleDetailBO.setCompareSignalValue(calcSignalValue);
                    }

                    if (ruleDetailBO.getDetailRelation().equals(RuleRelationEnum.OR.name())) {
                        detailsByOr.add(ruleDetailBO);
                    } else if (ruleDetailBO.getDetailRelation().equals(RuleRelationEnum.AND.name())) {
                        detailsByAnd.add(ruleDetailBO);
                    } else {
                        detailsByAnd.add(ruleDetailBO);
                    }
                }
            });

            Map<String, Object> gbValueMap = new HashMap<>();
            //or关系
            boolean boolOr = false;
            for (RuleDetailBO detailBO : detailsByOr) {
                if(detailBO.getCompareType() == 1){
                    boolOr = CompareUtil.compare(detailBO.getSignalRule(), detailBO.getCompareSignalValue().toString(), detailBO.getVinSignalValue());
                }else {
                    Object vinSignalValue = detailBO.getVinSignalValue();
                    //数组类型，特殊处理
                    if(vinSignalValue instanceof List) {
                        List signalValue = (List) vinSignalValue;
                        for (Object value : signalValue) {
                            boolOr = CompareUtil.compare(detailBO.getSignalRule(), detailBO.getSignalValue(), value);
                        }
                    }else {
                        boolOr = CompareUtil.compare(detailBO.getSignalRule(), detailBO.getSignalValue(), detailBO.getVinSignalValue());
                    }
                }
                if (boolOr) {
                    if(detailBO.getCompareType() == 1){
                        gbValueMap.put(detailBO.getSignalValue(), detailBO.getCompareSignalValue());
                    }
                    gbValueMap.put(detailBO.getSignalId(), detailBO.getVinSignalValue());
                    break;
                }
            }

            //and关系
            boolean boolAnd = false;
            for (RuleDetailBO detailBO : detailsByAnd) {

                if(detailBO.getCompareType() == 1){
                    boolAnd = CompareUtil.compare(detailBO.getSignalRule(), detailBO.getCompareSignalValue().toString(), detailBO.getVinSignalValue());
                }else {
                    Object vinSignalValue = detailBO.getVinSignalValue();
                    //数组类型，特殊处理
                    if(vinSignalValue instanceof List) {
                        List signalValue = (List)vinSignalValue;
                        for (Object value : signalValue) {
                            boolAnd = CompareUtil.compare(detailBO.getSignalRule(), detailBO.getSignalValue(), value);
                        }
                    }else {
                        boolAnd = CompareUtil.compare(detailBO.getSignalRule(), detailBO.getSignalValue(), detailBO.getVinSignalValue());
                    }
                }
                if (!boolAnd) {
                    break;
                }
            }

            //判断是否符合条件,and 和 or 同时存在
            if (CollUtil.isNotEmpty(detailsByOr) && CollUtil.isNotEmpty(detailsByAnd)) {
                if (boolOr && boolAnd) {
                    for (RuleDetailBO detailBO : detailsByAnd) {
                        if(detailBO.getCompareType() == 1){
                            gbValueMap.put(detailBO.getSignalValue(), detailBO.getCompareSignalValue());
                        }
                        gbValueMap.put(detailBO.getSignalId(), detailBO.getVinSignalValue());
                    }
                    Map<String, Object> retMap = RetDataUtil.buildRetMap(ruleCode, gbValueMap);
                    retData.add(JSONObject.parseObject(JSON.toJSONString(retMap)));
                    return true;
                }
                return false;
            }

            //判断是否符合条件，or 关系
            if (CollUtil.isNotEmpty(detailsByOr)) {
                if (boolOr) {
                    Map<String, Object> retMap = RetDataUtil.buildRetMap(ruleCode, gbValueMap);
                    retData.add(JSONObject.parseObject(JSON.toJSONString(retMap)));
                    return true;
                }
                return false;
            }

            //判断是否符合条件，and 关系
            if (CollUtil.isNotEmpty(detailsByAnd)) {
                if (boolAnd) {
                    for (RuleDetailBO detailBO : detailsByAnd) {
                        if(detailBO.getCompareType() == 1){
                            gbValueMap.put(detailBO.getSignalValue(), detailBO.getCompareSignalValue());
                        }
                        gbValueMap.put(detailBO.getSignalId(), detailBO.getVinSignalValue());
                    }
                    Map<String, Object> retMap = RetDataUtil.buildRetMap(ruleCode, gbValueMap);
                    retData.add(JSONObject.parseObject(JSON.toJSONString(retMap)));
                    return true;
                }
                return false;
            }
        }catch (Exception e){
            System.out.println("计算错误,规则代码:"+ruleCode+","+e);
        }
        return false;
    }

    /**
     * 批量构建数据
     */
    public static void batchBuild(String[] ruleCodes,Map<String,Object> signalMap,Map<String,Map<String,List<String>>> ruleMap,JSONArray retData){
        for (String ruleCode : ruleCodes) {
            RetDataBuild.build(ruleCode,signalMap,ruleMap,retData);
        }
    }
}
