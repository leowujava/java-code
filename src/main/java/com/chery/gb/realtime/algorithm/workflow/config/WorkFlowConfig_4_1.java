package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.anotation.WorkFlowConfig;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.rule.config.CommonCondition;
import com.chery.gb.realtime.algorithm.workflow.GbWorkFlowEnum;

/**
 * @author wugaoyang
 * @date 2025/11/10 星期一
 *
 */
@WorkFlowConfig(GbWorkFlowEnum.WF_4_1)
public class WorkFlowConfig_4_1 extends BaseWorkFlowConfig {
    public WorkFlowConfig_4_1() {
        setCondition(CommonCondition.buildNullCondition(SignalEnum.SIGNAL_2001, false));
    }
}
