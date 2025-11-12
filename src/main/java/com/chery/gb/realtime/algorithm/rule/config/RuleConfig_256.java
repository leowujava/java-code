package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 总电压不匹配
 * 总电压≠最小并联单元电压之和(偏差超出 5V)
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_256)
public class RuleConfig_256 extends BaseRuleConfig {

    public RuleConfig_256() {
        conditionBO = new RuleConditionBO();
    }

}
