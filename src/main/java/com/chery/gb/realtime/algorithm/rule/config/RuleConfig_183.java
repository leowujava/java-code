package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 通用报警故障总数不匹配
 * 通用报警故障总数≠通用报警故障等级列表中通用报警故障等级个数
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_183)
public class RuleConfig_183 extends BaseRuleConfig {

    public RuleConfig_183() {
        conditionBO = new RuleConditionBO();
    }

}
