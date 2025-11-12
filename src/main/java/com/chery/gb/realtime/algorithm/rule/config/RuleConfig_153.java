package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 纬度精确度不足
 * 精确度不足百万分之一度
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_153)
public class RuleConfig_153 extends BaseRuleConfig {

    public RuleConfig_153() {
        conditionBO = new RuleConditionBO();
    }

}
