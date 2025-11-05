package com.chery.gb.realtime.algorithm.validate.config;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

import java.util.ArrayList;

/**
 * 车辆数据采集时间越界
 * “年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_9)
public class RuleConfig_9 extends BaseConfig {

    @Override
    public RuleConfigBO getRuleConfigBO() {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        ArrayList<RuleDetailBO> conditions = new ArrayList<>();
        ruleConfigBO.setConditions(conditions);
        ruleConfigBO.setReturn(false);
        return ruleConfigBO;
    }

}
