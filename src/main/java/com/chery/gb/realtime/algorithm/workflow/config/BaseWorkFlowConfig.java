package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/11/10 星期一
 *
 */
@Data
public class BaseWorkFlowConfig {

    /**
     * 判断条件
     */
    private RuleConditionBO condition;
    /**
     * 校验的规则
     */
    private List<NewGbRuleCodeEnum> ruleCodeList;
}
