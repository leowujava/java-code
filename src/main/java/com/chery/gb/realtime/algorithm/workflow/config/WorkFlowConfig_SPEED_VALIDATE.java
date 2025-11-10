package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.anotation.WorkFlowConfig;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.workflow.WorkFlowEnum;

import java.util.Arrays;

/**
 * @author wugaoyang
 * @date 2025/11/10 星期一
 *
 */
@WorkFlowConfig(WorkFlowEnum.SPEED_VALIDATE)
public class WorkFlowConfig_SPEED_VALIDATE extends BaseWorkFlowConfig {
    public WorkFlowConfig_SPEED_VALIDATE() {
        setRuleConditionBO(null);
        setRuleCodeList(Arrays.asList(NewGbRuleCodeEnum.RULE_CODE_31,
                NewGbRuleCodeEnum.RULE_CODE_32,
                NewGbRuleCodeEnum.RULE_CODE_33));
    }
}
