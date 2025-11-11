package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 超级电容单体总数不匹配
 * 超级电容单体总数≠超级电容单体电压个数
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_196)
public class RuleConfig_196 extends BaseRuleConfig {

    public RuleConfig_196() {
        conditionBO = new RuleConditionBO();
    }

}
