package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 最小并联单元电压精确度不足
 * 电压精度不满足 0.001V
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_268)
public class RuleConfig_268 extends BaseRuleConfig {

    public RuleConfig_268() {
        conditionBO = new RuleConditionBO();
    }

}
