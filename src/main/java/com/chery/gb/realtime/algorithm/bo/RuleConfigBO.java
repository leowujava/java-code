package com.chery.gb.realtime.algorithm.bo;


import lombok.Data;

/**
 * 规则配置
 *
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@Data
public class RuleConfigBO {
    /**
     * 前置条件
     */
    private RuleConditionBO preCondition;
    /**
     * 校验条件
     */
    private RuleConditionBO condition;
}
