package com.chery.gb.realtime.algorithm.validate.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆状态异常
 * 0xFE
 *
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_22)
public class RuleConfig_22 extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        List<RuleDetailBO> preConditions = new ArrayList<>();
        preConditions.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalValue("null").signalRule(RuleSymbolEnum.EQ.name()).build());
        ruleConfigBO.setPreConditions(preConditions);
        ruleConfigBO.setPreReturn(true);
        ruleConfigBO.setPreDesc("车辆状态is null");

        List<RuleDetailBO> conditions = new ArrayList<>();
        ruleConfigBO.setConditions(conditions);
        ruleConfigBO.setReturn(true);
        return ruleConfigBO;
    }

}
