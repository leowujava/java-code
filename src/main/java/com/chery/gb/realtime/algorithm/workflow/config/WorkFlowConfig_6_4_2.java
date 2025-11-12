package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.anotation.WorkFlowConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.workflow.GbWorkFlowEnum;

import java.util.ArrayList;

/**
 * @author wugaoyang
 * @date 2025/11/10 星期一
 *
 */
@WorkFlowConfig(GbWorkFlowEnum.WF_6_4_2)
public class WorkFlowConfig_6_4_2 extends BaseWorkFlowConfig {
    public WorkFlowConfig_6_4_2() {
        RuleConditionBO condition = new RuleConditionBO();
        ArrayList<RuleDetailBO> conditions = new ArrayList<>();
        conditions.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AB.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("2").build());
        condition.setConditions(conditions);
        setCondition(condition);
    }
}
