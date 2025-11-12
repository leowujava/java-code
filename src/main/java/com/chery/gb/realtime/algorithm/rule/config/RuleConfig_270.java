package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 30 秒内里程跳变大于 4 公里
 * 相差 30 秒的 2 帧报文里程跳变大于 4 公里
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_270)
public class RuleConfig_270 extends BaseRuleConfig {

    public RuleConfig_270() {
        conditionBO = new RuleConditionBO();
    }

}
