package com.chery.gb.realtime.algorithm.workflow.config;


import com.chery.gb.realtime.algorithm.anotation.WorkFlowConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.workflow.WorkFlowEnum;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

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

    public BaseWorkFlowConfig() {
        WorkFlowConfig declaredAnnotation = this.getClass().getDeclaredAnnotation(WorkFlowConfig.class);
        if (declaredAnnotation == null) {
            return;
        }
        List<NewGbRuleCodeEnum> ruleCodeListByCode = WorkFlowEnum.getRuleCodeListByCode(declaredAnnotation.value().getCode());
        if (CollectionUtils.isEmpty(ruleCodeListByCode)) {
            return;
        }
        this.ruleCodeList = ruleCodeListByCode;
    }
}
