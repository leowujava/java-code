package com.chery.gb.realtime.algorithm.util.check;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.rule.factory.RuleConfigFactory;
import com.chery.gb.realtime.algorithm.rule.config.BaseRuleConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author wugaoyang
 * @date 2025/5/19 星期一
 */
@Slf4j
public class GbRuleCheckUtil {

    private static volatile Integer preSeq = null;

    private static volatile Map<String, Integer> vinLoginSeqMap = new HashMap<>();

    /**
     * 规则校验
     *
     * @param ruleDetail
     * @param fieldValue
     * @param flag
     * @return
     */
    public static boolean validateRule(Object ruleDetail, Object fieldValue, Boolean flag) {
        if (ruleDetail == null) {
            return false;
        }
        Map<String, Object> ruleDetailMap = JSON.parseObject(JSON.toJSONString(ruleDetail), Map.class);
        boolean flag2 = validateField(ruleDetailMap, fieldValue);
        Object relation = ruleDetailMap.get("detail_relation");
        if (relation == null || StrUtil.isBlank(relation.toString())) {
            return flag2;
        }
        if (flag == null) {
            return flag2;
        }
        if (Objects.equals(relation, "AND")) {
            flag = flag && flag2;
        }
        if (Objects.equals(relation, "OR")) {
            flag = flag || flag2;
        }
        return flag;
    }

    /**
     * 字段值规则校验
     *
     * @param ruleDetailMap
     * @param fieldValueObj
     * @return
     */
    private static boolean validateField(Map<String, Object> ruleDetailMap, Object fieldValueObj) {
        boolean flag2 = false;
        String signalValue = String.valueOf(ruleDetailMap.get("signal_value"));
        Object signalRule = ruleDetailMap.get("signal_rule");
        if (signalRule == null) {
            return false;
        }
        String numberRegex = "^-?(0|[1-9]\\d*)(\\.\\d+)?$";
        if (!(fieldValueObj instanceof List)) {
            String fieldValue = String.valueOf(fieldValueObj);
            boolean equals = Objects.equals(signalValue, fieldValue);
            if (Objects.equals(signalRule, "EQ")) {
                if (!fieldValue.matches(numberRegex) || !signalValue.matches(numberRegex)) {
                    if (Objects.equals(signalValue, "") || Objects.equals(signalValue, "null")) {
                        flag2 = Objects.equals("", fieldValue) || Objects.equals("null", fieldValue);
                    } else {
                        flag2 = equals;
                    }
                } else {
                    Double i = Double.valueOf(signalValue);
                    Double j = Double.valueOf(fieldValue);
                    flag2 = i.equals(j);
                }
            } else if (Objects.equals(signalRule, "NE")) {
                flag2 = !equals;
            } else if (Objects.equals(signalRule, "LT") || Objects.equals(signalRule, "GT") || Objects.equals(signalRule, "GE") || Objects.equals(signalRule, "LE")) {
                if (!fieldValue.matches(numberRegex) || !signalValue.matches(numberRegex)) {
                    return false;
                }
                Double i = Double.valueOf(signalValue);
                Double j = Double.valueOf(fieldValue);
                if (Objects.equals(signalRule, "LT")) {
                    flag2 = i > j;
                } else if (Objects.equals(signalRule, "GT")) {
                    flag2 = i < j;
                } else if (Objects.equals(signalRule, "LE")) {
                    flag2 = i >= j;
                } else if (Objects.equals(signalRule, "GE")) {
                    flag2 = i <= j;
                }
            }
        } else {
            Integer fieldValue = JSON.parseArray(JSON.toJSONString(fieldValueObj)).size();
            Integer i = Integer.valueOf(signalValue);
            if (Objects.equals(signalRule, "LT")) {
                flag2 = i > fieldValue;
            } else if (Objects.equals(signalRule, "GT")) {
                flag2 = i < fieldValue;
            } else if (Objects.equals(signalRule, "LE")) {
                flag2 = i >= fieldValue;
            } else if (Objects.equals(signalRule, "GE")) {
                flag2 = i <= fieldValue;
            } else if (Objects.equals(signalRule, "EQ")) {
                flag2 = i.equals(fieldValue);
            }
        }
        return flag2;
    }

    public static void checkByRuleCode(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData, String[] ruleCodes) {
        for (String ruleCode : ruleCodes) {
            GbRuleCheckUtil.checkByRuleCode(signalMap, ruleMap, retData, ruleCode);
        }
    }

    public static boolean checkByRuleCode(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData, String ruleCode) {
        Map<String, List<RuleDetailBO>> ruleDetailMap = ruleMap.get(ruleCode);
        if (CollectionUtil.isEmpty(ruleDetailMap)) {
            return true;
        }

        JSONObject resultDetailData = new JSONObject();
        resultDetailData.put("ruleCode", ruleCode);
        JSONObject gbValue = new JSONObject();
        Boolean flag = null;
        for (List<RuleDetailBO> rule : ruleDetailMap.values()) {
            for (RuleDetailBO ruleDetailBO : rule) {
                String signalId = ruleDetailBO.getSignalId();
                SignalEnum signal = SignalEnum.getByCode(signalId);
                if (signal != null && StrUtil.isNotBlank(signal.getParent())) {
                    Object o = signalMap.get(signal.getParent());
                    if (Objects.isNull(o)) {
                        flag = validateRule(ruleDetailBO, "", flag);
                        if (flag) {
                            gbValue.put(signalId, null);
                        }
                        continue;
                    }
                    if (o instanceof List) {
                        List<?> list = (List<?>) o;
                        Boolean flag2 = null;
                        for (Object item : list) {
                            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(item));
                            Object signalValue = jsonObject.get(signalId);
                            Object value = signalValue;
                            if (!Objects.isNull(signalValue) && signalValue instanceof List) {
                                if (((List<?>) signalValue).size() == 0) {
                                    value = null;
                                }
                            }
                            flag2 = validateRule(ruleDetailBO, String.valueOf(value), flag2);
                            if (flag2) {
                                gbValue.put(signalId, signalValue);
                            }
                            if (flag == null) {
                                flag = flag2;
                            } else {
                                flag = flag2 || flag;
                            }
                        }
                    }
                } else {
                    Object signalValue = signalMap.get(signalId);
                    flag = validateRule(ruleDetailBO, String.valueOf(signalValue), flag);
                    if (flag) {
                        gbValue.put(signalId, signalValue);
                    }
                }
            }
        }
        if (flag != null && flag) {
            resultDetailData.put("gbValue", gbValue);
            retData.add(resultDetailData);
        }
        return flag == null ? true : flag;
    }

    public static boolean checkByCondition(Map<String, Object> signalMap, List<RuleDetailBO> condition, JSONArray retData, String ruleCode) {
        JSONObject resultDetailData = new JSONObject();
        resultDetailData.put("ruleCode", ruleCode);
        JSONObject gbValue = new JSONObject();
        Boolean flag0 = null;
        if (CollectionUtil.isNotEmpty(condition)) {
            for (RuleDetailBO ruleDetailBO : condition) {
                String signalId = ruleDetailBO.getSignalId();
                SignalEnum signal = SignalEnum.getByCode(signalId);
                if (signal != null && StrUtil.isNotBlank(signal.getParent())) {
                    Object o = signalMap.get(signal.getParent());
                    if (Objects.isNull(o)) {
                        flag0 = validateRule(ruleDetailBO, "", flag0);
                        continue;
                    }
                    if (o instanceof List) {
                        List<?> list = (List<?>) o;
                        Boolean flag2 = null;
                        for (Object item : list) {
                            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(item));
                            Object signalValue = jsonObject.get(signalId);
                            Object value = signalValue;
                            if (!Objects.isNull(signalValue) && signalValue instanceof List) {
                                if (((List<?>) signalValue).size() == 0) {
                                    value = null;
                                }
                            }
                            flag2 = validateRule(ruleDetailBO, String.valueOf(value), flag2);
                            if (flag0 == null) {
                                flag0 = flag2;
                            } else {
                                flag0 = flag2 || flag0;
                            }
                        }
                    }
                } else {
                    Object signalValue = signalMap.get(signalId);
                    flag0 = validateRule(ruleDetailBO, String.valueOf(signalValue), flag0);
                    if (flag0) {
                        gbValue.put(signalId, signalValue);
                    }
                }
            }
        }
        if (flag0 != null && flag0) {
            if (StrUtil.isNotBlank(ruleCode) && ruleCode.matches("\\d+")) {
                resultDetailData.put("gbValue", gbValue);
                retData.add(resultDetailData);
            }
            return true;
        }
        return false;
    }
}
