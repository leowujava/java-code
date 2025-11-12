package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 最低温度值不匹配
 * 最低温度值≠探针温度值列表中温度最低值
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_238)
public class RuleConfig_238 extends BaseRuleConfig {

    public RuleConfig_238() {
        conditionBO = new RuleConditionBO();
    }

}
