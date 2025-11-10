package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 车辆状态为熄火车速不为 0
 * 车辆熄火, 但车速>5km/h
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_250)
public class RuleRuleConfig_250 extends BaseRuleConfig {

    public RuleRuleConfig_250() {
        ruleConfigBO = new RuleConfigBO();
        RuleConditionBO condition = CommonCondition.vehicleStateIS2AndSpeedGt5(false);
        ruleConfigBO.setCondition(condition);
    }

}
