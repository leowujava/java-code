package com.chery.gb.realtime.algorithm.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/11/5 星期三
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleConditionBO {

    /**
     * 规则code
     */
    private String ruleCode;
    /**
     * 条件
     */
    private List<RuleDetailBO> conditions;
    /**
     * 是否中断流程返回
     */
    private boolean isReturn;
    /**
     * 条件描述
     */
    private String desc;
}
