package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.anotation.WorkFlowConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.RuleRelationEnum;
import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.workflow.WorkFlowEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/11/10 星期一
 *
 */
@WorkFlowConfig(WorkFlowEnum.CHARGING_STATE_IS_NULL)
public class WorkFlowConfig_CHARGING_STATE_IS_NULL extends BaseWorkFlowConfig {
    public WorkFlowConfig_CHARGING_STATE_IS_NULL() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> list = new ArrayList<>();
        ruleConditionBO.setConditions(list);
        list.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).detailRelation(RuleRelationEnum.AND.name()).build());
        setRuleConditionBO(ruleConditionBO);
        setRuleCodeList(null);
    }
}
