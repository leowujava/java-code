package com.chery.gb.realtime.algorithm.validate.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

import java.util.ArrayList;

/**
 * 三/四级报警报文未持续至报警标志位消失
 * 触发 3|4 级报警后, 报文未持续到报警标志位消失
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_261)
public class RuleConfig_261 extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        ArrayList<RuleDetailBO> conditions = new ArrayList<>();
        ruleConfigBO.setConditions(conditions);
        ruleConfigBO.setReturn(false);
        return ruleConfigBO;
    }

}
