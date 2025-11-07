package com.chery.gb.realtime.algorithm.rule.validator;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

import java.util.List;
import java.util.Map;

/**
 * 平台登出报文延时>180s
 * 
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
@RuleValidate(rule = NewGbRuleCodeEnum.RULE_CODE_267)
public class RuleValidator_267 extends BaseRuleValidator {
    @Override
    public boolean validate(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {
       boolean flag = super.validate(signalMap, ruleMap, retData);
       return flag;
    }
}
