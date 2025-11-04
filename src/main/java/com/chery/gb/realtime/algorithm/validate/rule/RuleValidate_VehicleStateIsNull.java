package com.chery.gb.realtime.algorithm.validate.rule;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.factory.RuleConfigFactory;
import com.chery.gb.realtime.algorithm.validate.config.BaseConfig;

import java.util.List;
import java.util.Map;

/**
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
@RuleValidate(rule = NewGbRuleCodeEnum.RULE_CODE_VEHICLE_STATE_IS_NULL)
public class RuleValidate_VehicleStateIsNull extends BaseRuleValidate {
    @Override
    public void validate(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {
        System.out.println("RuleValidate_" + getRuleCode() + ".validate");
        boolean flag = true;
        BaseConfig config = RuleConfigFactory.getConfig(getRuleCode(), ruleMap);

        checkReturn(flag);
    }
}
