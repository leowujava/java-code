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
import java.util.List;

/**
 * 车辆状态非 1 启动且非 2 熄火
 * 车辆状态既不是启动也不是熄火
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_259)
public class RuleConfig_259 extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        List<RuleDetailBO> conditions = new ArrayList<>();
        conditions.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalValue("1").signalRule(RuleSymbolEnum.NE.name()).detailRelation(RuleRelationEnum.AND.name()).build());
        conditions.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalValue("2").signalRule(RuleSymbolEnum.NE.name()).detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConfigBO.setCondition(RuleConditionBO.builder().conditions(conditions).isReturn(true).build());
        return ruleConfigBO;
    }

}
