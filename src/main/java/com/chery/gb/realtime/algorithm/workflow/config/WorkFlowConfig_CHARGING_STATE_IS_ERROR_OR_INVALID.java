package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.anotation.WorkFlowConfig;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.rule.config.CommonCondition;
import com.chery.gb.realtime.algorithm.workflow.WorkFlowEnum;

import java.util.Arrays;

/**
 * @author wugaoyang
 * @date 2025/11/10 星期一
 *
 */
@WorkFlowConfig(WorkFlowEnum.CHARGING_STATE_IS_ERROR_OR_INVALID)
public class WorkFlowConfig_CHARGING_STATE_IS_ERROR_OR_INVALID extends BaseWorkFlowConfig {
    public WorkFlowConfig_CHARGING_STATE_IS_ERROR_OR_INVALID() {
        setRuleCodeList(Arrays.asList(NewGbRuleCodeEnum.RULE_CODE_25, NewGbRuleCodeEnum.RULE_CODE_26));
    }
}
