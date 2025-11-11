package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 驱动电机故障总数 N2 不匹配
 * 驱动电机故障总数 N2≠驱动电机故障代码列表中驱动电机故障信息个数
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_171)
public class RuleConfig_171 extends BaseRuleConfig {

    public RuleConfig_171() {
        conditionBO = new RuleConditionBO();
    }

}
