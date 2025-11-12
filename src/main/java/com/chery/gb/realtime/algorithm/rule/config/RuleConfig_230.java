package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 最高温度值不匹配
 * 最高温度值≠探针温度值列表中温度最高值
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_230)
public class RuleConfig_230 extends BaseRuleConfig {

    public RuleConfig_230() {
        conditionBO = new RuleConditionBO();
    }

}
