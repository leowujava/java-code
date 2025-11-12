package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 可充电储能装置故障总数 N1不匹配
 * 可充电储能装置故障总数 N1≠可充电储能装置故障代码列表中可充电储能装置故障信息个数
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_167)
public class RuleConfig_167 extends BaseRuleConfig {

    public RuleConfig_167() {
        conditionBO = new RuleConditionBO();
    }

}
