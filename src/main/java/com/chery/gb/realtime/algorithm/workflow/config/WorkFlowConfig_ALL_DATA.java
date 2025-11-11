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
@WorkFlowConfig(WorkFlowEnum.ALL_DATA)
public class WorkFlowConfig_ALL_DATA extends BaseWorkFlowConfig {
    public WorkFlowConfig_ALL_DATA() {
        setCondition(null);
        setRuleCodeList(Arrays.asList(NewGbRuleCodeEnum.RULE_CODE_239,
                NewGbRuleCodeEnum.RULE_CODE_240,
                NewGbRuleCodeEnum.RULE_CODE_241,
                NewGbRuleCodeEnum.RULE_CODE_242,
                NewGbRuleCodeEnum.RULE_CODE_245,
                NewGbRuleCodeEnum.RULE_CODE_246,
                NewGbRuleCodeEnum.RULE_CODE_258));
    }
}
