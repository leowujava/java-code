package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 其他故障总数 N4 不匹配
 * 其他故障总数 N4≠其他故障代码列表中其他故障信息个数
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_179)
public class RuleConfig_179 extends BaseRuleConfig {

    public RuleConfig_179() {
        conditionBO = new RuleConditionBO();
    }

}
