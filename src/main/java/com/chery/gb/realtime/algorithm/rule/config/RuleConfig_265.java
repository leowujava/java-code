package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 车辆登出报文延时>180s
 * 车辆登出报文数据采集时间与服务端平台服务器接收时间相差超过 180秒
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_265)
public class RuleConfig_265 extends BaseConfig {

    public RuleConfig_265() {
        ruleConfigBO = new RuleConfigBO();
        RuleConditionBO condition = new RuleConditionBO();
        ruleConfigBO.setCondition(condition);
    }

}
