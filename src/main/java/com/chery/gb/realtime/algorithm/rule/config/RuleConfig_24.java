package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.RuleRelationEnum;
import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆状态无定义
 * 不在[0x01、0x02、0x03]范围内
 *
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_24)
public class RuleConfig_24 extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        RuleConditionBO ruleConditionBO = CommonCondition.vehicleStateNot123();
        ruleConditionBO.setReturn(true);
        ruleConfigBO.setCondition(ruleConditionBO);
        return ruleConfigBO;
    }

}
