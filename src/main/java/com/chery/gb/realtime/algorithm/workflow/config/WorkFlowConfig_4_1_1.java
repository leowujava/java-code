package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.annotation.WorkFlowConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.RuleRelationEnum;
import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.signal.config.BaseSignalConfig;
import com.chery.gb.realtime.algorithm.signal.factory.SignalConfigFactory;
import com.chery.gb.realtime.algorithm.workflow.GbWorkFlowEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/11/10 星期一
 *
 */
@WorkFlowConfig(GbWorkFlowEnum.WF_4_1_1)
public class WorkFlowConfig_4_1_1 extends BaseWorkFlowConfig {
    public WorkFlowConfig_4_1_1() {
        BaseSignalConfig signalConfig = SignalConfigFactory.getByCode(SignalEnum.SIGNAL_2001.getCode());
        if (signalConfig == null || signalConfig.getSignalConfigBO() == null) {
            return;
        }
        SignalConfigBO signalConfigBO = signalConfig.getSignalConfigBO();
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_2001.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue(signalConfigBO.getErrorValue()).detailRelation(RuleRelationEnum.AND.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_2001.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue(signalConfigBO.getInvalidValue()).detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        setCondition(ruleConditionBO);
    }
}
