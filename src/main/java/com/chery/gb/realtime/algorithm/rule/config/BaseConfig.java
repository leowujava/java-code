package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;

import java.util.Collections;
import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
public abstract class BaseConfig {

    public abstract RuleConfigBO getRuleConfigBO();

    public List<RuleDetailBO> getCondition() {
        RuleConditionBO conditions = getRuleConfigBO().getCondition();
        if (conditions == null) {
            return null;
        }
        return conditions.getConditions();
    }

    public List<RuleDetailBO> getPreCondition() {
        RuleConditionBO preConditions = getRuleConfigBO().getPreCondition();
        if (preConditions == null) {
            return Collections.emptyList();
        }
        return preConditions.getConditions();
    }

    public String getPreDesc(){
        RuleConditionBO preConditions = getRuleConfigBO().getPreCondition();
        if (preConditions == null) {
            return null;
        }
        return preConditions.getDesc();
    }

    public boolean isReturn() {
        RuleConditionBO conditions = getRuleConfigBO().getCondition();
        if (conditions == null) {
            return false;
        }
        return conditions.isReturn();
    }

    public boolean isPreReturn() {
        RuleConditionBO preConditions = getRuleConfigBO().getPreCondition();
        if (preConditions == null) {
            return false;
        }
        return preConditions.isReturn();
    }

    public String getDesc() {
        RuleConditionBO condition = getRuleConfigBO().getCondition();
        if (condition == null) {
            return null;
        }
        return condition.getDesc();
    }
}
