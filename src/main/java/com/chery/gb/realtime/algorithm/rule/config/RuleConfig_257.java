package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 车辆行驶中能量回收显示停车充电
 * 纯电动车速>0 电流为负数 充电状态 1 同时出现
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_257)
public class RuleConfig_257 extends BaseRuleConfig {

    public RuleConfig_257() {
        conditionBO = new RuleConditionBO();
    }

}
