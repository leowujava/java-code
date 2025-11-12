package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 超级电容温度探针总数不匹配
 * 超级电容温度探针总数≠探针温度值个数
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_203)
public class RuleConfig_203 extends BaseRuleConfig {

    public RuleConfig_203() {
        conditionBO = new RuleConditionBO();
    }

}
