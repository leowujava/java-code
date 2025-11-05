package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * 充电状态无定义
 * 不在[0x01、0x02、0x03、0x04]范围内
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_27)
public class RuleConfig_27 extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        ruleConfigBO.setCondition(CommonCondition.buildRangeCondition(SignalEnum.SIGNAL_21AA, false));
        return ruleConfigBO;
    }

}
