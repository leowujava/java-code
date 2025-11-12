package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 三/四级报警报文上报帧数不足
 * 触发三/四级报警的车辆数据报文,补发报警前数据帧数不足 30 帧
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_157)
public class RuleConfig_157 extends BaseRuleConfig {

    public RuleConfig_157() {
        conditionBO = new RuleConditionBO();
    }

}
