package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 三/四级报警报文未持续至报警标志位消失
 * 触发 3|4 级报警后, 报文未持续到报警标志位消失
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_261)
public class RuleConfig_261 extends BaseRuleConfig {

    public RuleConfig_261() {
        conditionBO = new RuleConditionBO();
    }

}
