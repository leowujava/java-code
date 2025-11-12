package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 高压下电监测报文缺失
 * 车辆下高压未上报 59min30s 至 1h 监测报文
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_260)
public class RuleConfig_260 extends BaseRuleConfig {

    public RuleConfig_260() {
        conditionBO = new RuleConditionBO();
    }

}
