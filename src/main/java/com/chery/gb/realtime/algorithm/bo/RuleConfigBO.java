package com.chery.gb.realtime.algorithm.bo;


import lombok.Data;

import java.util.List;

/**
 * 规则配置
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@Data
public class RuleConfigBO {
    /**
     * 前置条件
     */
    private List<RuleDetailBO> preConditions;
    /**
     * 校验条件
     */
    private List<RuleDetailBO> conditions;
    /**
     * 是否中断流程返回-前置条件
     */
    private boolean isPreReturn;
    /**
     * 是否中断流程返回-校验条件
     */
    private boolean isReturn;
    /**
     * 前置条件描述
     */
    private String preDesc;
}
