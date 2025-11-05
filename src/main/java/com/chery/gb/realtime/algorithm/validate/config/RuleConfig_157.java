package com.chery.gb.realtime.algorithm.validate.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

import java.util.ArrayList;

/**
 * 三/四级报警报文上报帧数不足
 * 触发三/四级报警的车辆数据报文,补发报警前数据帧数不足 30 帧
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_157)
public class RuleConfig_157 extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        ArrayList<RuleDetailBO> conditions = new ArrayList<>();
        ruleConfigBO.setConditions(conditions);
        ruleConfigBO.setReturn(false);
        return ruleConfigBO;
    }

}
