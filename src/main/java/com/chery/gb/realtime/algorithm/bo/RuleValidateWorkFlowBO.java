package com.chery.gb.realtime.algorithm.bo;


import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/11/6 星期四
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleValidateWorkFlowBO {
    /**
     * 名字
     */
    private String name;
    /**
     * 是否返回
     */
    private boolean isReturn;
    /**
     * 跳过工作流；步数
     */
    private int skip;
    /**
     * 判断条件
     */
    private RuleConditionBO ruleCondition;
    /**
     * 校验的规则
     */
    private List<NewGbRuleCodeEnum> ruleCodeList;
    /**
     * 下一个工作流
     */
    private RuleValidateWorkFlowBO nextWorkFlow;
    /**
     * 支流
     */
    private RuleValidateWorkFlowBO subWorkFlow;

}
