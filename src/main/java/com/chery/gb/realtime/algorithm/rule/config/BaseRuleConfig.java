package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@Data
public class BaseRuleConfig {

    private List<String> ruleSubTypes = Arrays.asList("异常值", "数据越界", "异常值", "缺失");

    protected RuleConditionBO conditionBO;

    public List<RuleDetailBO> getCondition() {
        if (conditionBO == null) {
            return null;
        }
        return conditionBO.getConditions();
    }

    public boolean isReturn() {
        if (conditionBO == null) {
            return false;
        }
        return conditionBO.isReturn();
    }


    public String getDesc() {
        if (conditionBO == null) {
            return null;
        }
        return conditionBO.getDesc();
    }
}
