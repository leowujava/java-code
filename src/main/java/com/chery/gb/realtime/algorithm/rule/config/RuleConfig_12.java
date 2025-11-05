package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆登出报文延时>30s
 * 车辆登出报文数据采集时间与服务端平台服务器接收时间相差超过 30秒
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_12)
public class RuleConfig_12 extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        List<RuleDetailBO> conditions = new ArrayList<>();
        ruleConfigBO.setCondition(RuleConditionBO.builder().conditions(conditions).isReturn(false).build());
        return ruleConfigBO;
    }

}
