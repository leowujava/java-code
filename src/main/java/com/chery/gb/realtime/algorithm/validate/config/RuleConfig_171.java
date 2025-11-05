package com.chery.gb.realtime.algorithm.validate.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

import java.util.ArrayList;

/**
 * 驱动电机故障总数 N2 不匹配
 * 驱动电机故障总数 N2≠驱动电机故障代码列表中驱动电机故障信息个数
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_171)
public class RuleConfig_171 extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        ArrayList<RuleDetailBO> conditions = new ArrayList<>();
        ruleConfigBO.setConditions(conditions);
        ruleConfigBO.setReturn(false);
        return ruleConfigBO;
    }

}
