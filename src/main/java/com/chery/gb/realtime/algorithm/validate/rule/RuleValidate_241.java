package com.chery.gb.realtime.algorithm.validate.rule;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalGroupEnum;

import java.util.List;
import java.util.Map;

/**
 * 动力蓄电池温度数据是否上传校验器
 *
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
@RuleValidate(rule = NewGbRuleCodeEnum.RULE_CODE_241)
public class RuleValidate_241 extends BaseRuleValidate {
    @Override
    public void validate(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {
        System.out.println("RuleValidate_" + getRuleCode() + ".validate");
        boolean flag = checkNullByGroup(signalMap, SignalGroupEnum.VEHICLE_POS);
        wrapErrorData(retData, flag);
        checkReturn(flag);
    }
}
