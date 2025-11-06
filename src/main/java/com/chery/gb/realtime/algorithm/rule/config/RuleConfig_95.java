package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 车载氢系统中最高温度异常
 * 0xFF,0xFE
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_95)
public class RuleConfig_95 extends BaseConfig {

    public RuleConfig_95() {
        ruleConfigBO = new RuleConfigBO();
        RuleConditionBO condition = buildCondition();
        ruleConfigBO.setCondition(condition);
    }


}
