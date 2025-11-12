package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 平台登出报文延时>30s
 * 平台登出报文时间与服务端平台服务器接收时间相差超过 30 秒
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_19)
public class RuleConfig_19 extends BaseRuleConfig {

    public RuleConfig_19() {
        conditionBO = new RuleConditionBO();
    }

}
