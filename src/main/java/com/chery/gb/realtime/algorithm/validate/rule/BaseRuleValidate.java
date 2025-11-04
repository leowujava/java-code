package com.chery.gb.realtime.algorithm.validate.rule;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.enums.SignalGroupEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.util.RetDataUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public abstract class BaseRuleValidate {

    public abstract void validate(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData);

    public void checkReturn(boolean flag) {
        if (flag) {
            RuleValidate declaredAnnotation = getClass().getDeclaredAnnotation(RuleValidate.class);
            NewGbRuleCodeEnum rule = declaredAnnotation.rule();
            if (declaredAnnotation != null) {
                throw new GbException(rule);
            }
            throw new GbException();

        }
    }

    public String getRuleCode() {
        RuleValidate ruleValidate = getClass().getDeclaredAnnotation(RuleValidate.class);
        if (ruleValidate != null && ruleValidate.rule() != null) {
            return ruleValidate.rule().getCode();
        }
        return null;
    }

    /**
     * 根据分组校验信号是否为Null
     *
     */
    protected static boolean checkNullByGroup(Map<String, Object> signalMap, SignalGroupEnum signalGroupEnum) {
        List<SignalEnum> byGroup = SignalEnum.getByGroup(signalGroupEnum);
        boolean flag = true;
        for (SignalEnum signalEnum : byGroup) {
            flag = signalMap.get(signalEnum.getCode()) == null;
            if (!flag) {
                break;
            }
        }
        return flag;
    }

    /**
     * 封装异常数据
     *
     * @param retData
     * @param flag
     */
    protected void wrapErrorData(JSONArray retData, boolean flag) {
        if (flag) {
            Map<String, Object> gbValueMap = new HashMap<>();
            retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap(getRuleCode(), gbValueMap))));
        }
    }
}
