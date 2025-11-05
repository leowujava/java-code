package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.RuleRelationEnum;
import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.ArrayList;

/**
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_VEHICLE_STATE_IS_NULL)
public class RuleConfig_VehicleStateIsNull extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        ArrayList<RuleDetailBO> conditions = new ArrayList<>();
        conditions.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.EQ.name()).detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConfigBO.setCondition(RuleConditionBO.builder().conditions(conditions).isReturn(true).build());
        return ruleConfigBO;
    }

}
