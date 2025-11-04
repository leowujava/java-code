package com.chery.gb.realtime.algorithm.validate.rule;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;

import java.util.List;
import java.util.Map;

/**
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public abstract class BaseRuleValidate {

    public abstract void validate(Map<String, Object> signalMap, Map<String, Map<String, List<String>>> ruleMap, JSONArray retData);

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
}
