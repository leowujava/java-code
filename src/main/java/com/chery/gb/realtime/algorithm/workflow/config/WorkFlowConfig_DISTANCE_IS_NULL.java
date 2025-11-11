package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.anotation.WorkFlowConfig;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.rule.config.CommonCondition;
import com.chery.gb.realtime.algorithm.workflow.WorkFlowEnum;

/**
 * @author wugaoyang
 * @date 2025/11/10 星期一
 *
 */
@WorkFlowConfig(WorkFlowEnum.DISTANCE_IS_NULL)
public class WorkFlowConfig_DISTANCE_IS_NULL extends BaseWorkFlowConfig {
    public WorkFlowConfig_DISTANCE_IS_NULL() {
        setCondition(CommonCondition.buildNullCondition(SignalEnum.SIGNAL_2009, false));
        setRuleCodeList(null);
    }
}
