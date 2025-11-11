package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 本帧最小并联单元数量不匹配
 * 最小并联单元总数≠最小并联单元电压个数
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_70)
public class RuleConfig_70 extends BaseRuleConfig {

    public RuleConfig_70() {
        conditionBO = new RuleConditionBO();
    }

}
