package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import lombok.Data;

import java.util.*;

/**
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@Data
public abstract class BaseConfig {

    private List<String> ruleSubTypes = Arrays.asList("异常值", "数据越界", "异常值", "缺失");

    protected RuleConfigBO ruleConfigBO;

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

    public String getPreDesc() {
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

    public RuleConditionBO buildCondition() {
        RuleConfig ruleValidate = getClass().getDeclaredAnnotation(RuleConfig.class);
        if (ruleValidate != null && ruleValidate.rule() != null) {
            NewGbRuleCodeEnum ruleCodeEnum = ruleValidate.rule();

            String ruleSubType = ruleCodeEnum.getRuleSubType();
            if (ruleSubType.equals("异常值") && !ruleCodeEnum.getDesc().contains("0xFE")) {
                return null;
            }
            SignalEnum signalEnum = NewGbRuleCodeEnum.getSignalByRuleCode(ruleCodeEnum.getCode());
            if (Objects.equals(ruleSubType, "缺失")) {
                return CommonCondition.buildNullCondition(signalEnum, false);
            }
            if (Objects.equals(ruleSubType, "异常值")) {
                return CommonCondition.buildErrorCondition(signalEnum, false);
            }
            if (Objects.equals(ruleSubType, "无效值")) {
                return CommonCondition.buildInvalidCondition(signalEnum, false);
            }
            if (Objects.equals(ruleSubType, "数据越界")) {
                return CommonCondition.buildRangeCondition(signalEnum, false);
            }
        }
        return null;
    }
}
