package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 燃料电池电堆数量不匹配
 * 燃料电池电堆个数≠燃料电池电堆信息表中燃料电池电堆信息个数
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_125)
public class RuleConfig_125 extends BaseRuleConfig {

    public RuleConfig_125() {
        conditionBO = new RuleConditionBO();
    }

}
