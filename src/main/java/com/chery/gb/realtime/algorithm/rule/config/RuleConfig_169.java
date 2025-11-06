package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 驱动电机故障总数 N2 无效
 * 0xFF
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_169)
public class RuleConfig_169 extends BaseConfig {

    public RuleConfig_169() {
        ruleConfigBO = new RuleConfigBO();
        RuleConditionBO condition = buildCondition();
        ruleConfigBO.setCondition(condition);
    }


}
