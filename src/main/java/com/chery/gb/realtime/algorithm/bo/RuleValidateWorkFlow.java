package com.chery.gb.realtime.algorithm.bo;


import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/11/6 星期四
 *
 */
@Data
public class RuleValidateWorkFlow {

    /**
     * 名字
     */
    private String name;

    /**
     * 是否返回
     */
    private boolean isReturn;

    /**
     * 工作流类型：1-判断；2-校验
     */
    private Integer type;

    /**
     * 判断条件；当工作流为判断时有用
     */
    private RuleConditionBO ruleConditionBO;

    /**
     * 校验的规则
     */
    private List<NewGbRuleCodeEnum> ruleCodeList;

    /**
     * 下一个工作流
     */
    private RuleValidateWorkFlow nextWorkFlow;

    /**
     * 下一个工作流（如果是判断的工作流，则会有两个方向的工作流）；这个是判断结果为是的工作流
     */
    private RuleValidateWorkFlow nextWorkFlow2;

}
