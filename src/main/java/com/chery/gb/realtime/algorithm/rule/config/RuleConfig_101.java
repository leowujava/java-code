package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 氢气最高浓度异常
 * 0xFF,0xFE
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_101)
public class RuleConfig_101 extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        List<RuleDetailBO> conditions = new ArrayList<>();
        ruleConfigBO.setCondition(RuleConditionBO.builder().conditions(conditions).isReturn(false).build());
        return ruleConfigBO;
    }

}
