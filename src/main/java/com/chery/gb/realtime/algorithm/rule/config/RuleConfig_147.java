package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 冷却水出水口温度越界
 * 不在[0,250]范围内
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_147)
public class RuleConfig_147 extends BaseConfig {

    public RuleConfig_147() {
        ruleConfigBO = new RuleConfigBO();
        RuleConditionBO condition = buildCondition();
        ruleConfigBO.setCondition(condition);
    }


}
