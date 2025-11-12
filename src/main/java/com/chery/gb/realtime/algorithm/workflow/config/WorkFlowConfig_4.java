package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.annotation.WorkFlowConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.RuleRelationEnum;
import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.workflow.GbWorkFlowEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/11/10 星期一
 *
 */
@WorkFlowConfig(GbWorkFlowEnum.WF_4)
public class WorkFlowConfig_4 extends BaseWorkFlowConfig {
    public WorkFlowConfig_4() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> list = new ArrayList<>();
        ruleConditionBO.setConditions(list);
        list.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalValue("1").signalRule(RuleSymbolEnum.NE.name()).detailRelation(RuleRelationEnum.AND.name()).build());
        list.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalValue("4").signalRule(RuleSymbolEnum.NE.name()).detailRelation(RuleRelationEnum.AND.name()).build());
        setCondition(ruleConditionBO);
    }
}
