package com.chery.gb.realtime.algorithm.rule.validator;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

import java.util.List;
import java.util.Map;

/**
 * 当次车辆登入流水号与登出流水号不一致
 * 
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
@RuleValidate(rule = NewGbRuleCodeEnum.RULE_CODE_14)
public class RuleValidator_14 extends BaseRuleValidator {
    @Override
    public boolean validate(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {
       boolean flag = super.validate(signalMap, ruleMap, retData);
       return flag;
    }
}
