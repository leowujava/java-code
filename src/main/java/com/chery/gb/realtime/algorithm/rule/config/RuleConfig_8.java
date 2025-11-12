package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 动力蓄电池包总编码个数不匹配
 * 动力蓄电池包总编码个数≠各电池管理系统对应动力蓄电池包个数之和
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_8)
public class RuleConfig_8 extends BaseRuleConfig {

    public RuleConfig_8() {
        conditionBO = new RuleConditionBO();
    }

}
