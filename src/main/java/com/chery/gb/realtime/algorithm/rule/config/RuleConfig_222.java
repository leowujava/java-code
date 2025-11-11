package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 超级电容单体电压最低值不匹配
 * 超级电容单体电压最低值≠超级电容单体电压列表中电压最低值
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_222)
public class RuleConfig_222 extends BaseRuleConfig {

    public RuleConfig_222() {
        conditionBO = new RuleConditionBO();
    }

}
