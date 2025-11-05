package com.chery.gb.realtime.algorithm.rule.validator;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalGroupEnum;

import java.util.List;
import java.util.Map;

/**
 * 未上传动力蓄电池最小并联单元电压数据
 * 
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
@RuleValidate(rule = NewGbRuleCodeEnum.RULE_CODE_240)
public class RuleValidator_240 extends BaseRuleValidator {
    @Override
    public void validate(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {
        super.validate(signalMap, ruleMap, retData);
        boolean flag = checkNullByGroup(signalMap, SignalGroupEnum.MINIMUM_PARALLEL_UNIT_VOLTAGE);
        wrapErrorData(retData, flag);
        checkReturn(flag);
    }
}
