package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 驱动电机数量不匹配
 * 驱动电机个数≠驱动电机总成信息列表中电机信息个数
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_78)
public class RuleConfig_78 extends BaseRuleConfig {

    public RuleConfig_78() {
        conditionBO = new RuleConditionBO();
    }

}
