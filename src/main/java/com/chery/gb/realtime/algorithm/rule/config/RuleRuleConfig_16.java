package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 平台登入报文延时>30s
 * 平台登入报文时间与服务端平台服务器接收时间相差超过 30 秒
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_16)
public class RuleRuleConfig_16 extends BaseRuleConfig {

    public RuleRuleConfig_16() {
        ruleConfigBO = new RuleConfigBO();
        RuleConditionBO condition = new RuleConditionBO();
        ruleConfigBO.setCondition(condition);
    }

}
