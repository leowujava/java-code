package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 曲轴转速异常
 * 纯电模式下曲轴转速>0
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_255)
public class RuleConfig_255 extends BaseRuleConfig {

    public RuleConfig_255() {
        conditionBO = new RuleConditionBO();
    }

}
