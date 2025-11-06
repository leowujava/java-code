package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 温度探针检测到的温度值异常
 * 0xFE
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_74)
public class RuleConfig_74 extends BaseConfig {

    public RuleConfig_74() {
        ruleConfigBO = new RuleConfigBO();
        RuleConditionBO condition = buildCondition();
        ruleConfigBO.setCondition(condition);
    }


}
