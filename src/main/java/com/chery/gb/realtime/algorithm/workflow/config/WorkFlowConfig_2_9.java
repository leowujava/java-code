package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.anotation.WorkFlowConfig;
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
@WorkFlowConfig(GbWorkFlowEnum.WF_2_9)
public class WorkFlowConfig_2_9 extends BaseWorkFlowConfig {
    public WorkFlowConfig_2_9() {
        RuleConditionBO condition = new RuleConditionBO();
        List<RuleDetailBO> list = new ArrayList<>();
        condition.setConditions(list);
        list.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("2").detailRelation(RuleRelationEnum.AND.name()).build());
        list.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("3").detailRelation(RuleRelationEnum.AND.name()).build());
        setCondition(condition);
    }
}
