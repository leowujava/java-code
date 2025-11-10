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
public class WorkFlowConfig_SPEED_IS_NULL extends BaseWorkFlowConfig {
    public WorkFlowConfig_SPEED_IS_NULL() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        ruleConditionBO.setDesc("车速 is null");
        List<RuleDetailBO> list = new ArrayList<>();
        list.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_2001.getCode()).signalRule(RuleSymbolEnum.NE.name()).build());
        ruleConditionBO.setConditions(list);
        setRuleConditionBO(ruleConditionBO);
        setRuleCodeList(null);
    }
}
