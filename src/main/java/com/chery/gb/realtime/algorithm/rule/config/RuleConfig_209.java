package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 最高电压管理系统号越界
 * 不在[1,250]范围内
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_209)
public class RuleConfig_209 extends BaseConfig {

    public RuleConfig_209() {
        ruleConfigBO = new RuleConfigBO();
        RuleConditionBO condition = buildCondition();
        ruleConfigBO.setCondition(condition);
    }


}
