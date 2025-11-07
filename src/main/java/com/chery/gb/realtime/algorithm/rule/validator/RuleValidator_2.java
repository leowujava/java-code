package com.chery.gb.realtime.algorithm.rule.validator;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

import java.util.List;
import java.util.Map;

/**
 * 车辆登入报文延时>30s
 * 
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
@RuleValidate(rule = NewGbRuleCodeEnum.RULE_CODE_2)
public class RuleValidator_2 extends BaseRuleValidator {
    @Override
    public boolean validate(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {
       boolean flag = super.validate(signalMap, ruleMap, retData);
       return flag;
    }
}
