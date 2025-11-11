package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 当次平台登入流水号与登出流水号不一致
 * 当次平台登入流水号≠登出流水号
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_21)
public class RuleConfig_21 extends BaseRuleConfig {

    public RuleConfig_21() {
        conditionBO = new RuleConditionBO();
    }

}
