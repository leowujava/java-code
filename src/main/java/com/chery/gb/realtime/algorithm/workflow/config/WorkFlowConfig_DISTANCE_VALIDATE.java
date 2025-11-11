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
@WorkFlowConfig(WorkFlowEnum.DISTANCE_VALIDATE)
public class WorkFlowConfig_DISTANCE_VALIDATE extends BaseWorkFlowConfig {
    public WorkFlowConfig_DISTANCE_VALIDATE() {
        setCondition(null);
        setRuleCodeList(Arrays.asList(NewGbRuleCodeEnum.RULE_CODE_34,
                NewGbRuleCodeEnum.RULE_CODE_35,
                NewGbRuleCodeEnum.RULE_CODE_36));
    }
}
