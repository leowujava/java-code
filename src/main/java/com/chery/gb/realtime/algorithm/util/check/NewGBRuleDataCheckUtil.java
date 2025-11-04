package com.chery.gb.realtime.algorithm.util.check;

import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.factory.RuleValidateFactory;
import com.chery.gb.realtime.algorithm.validate.rule.BaseRuleValidate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * 新国标校验规则
 *
 * @author S00003829
 */
@Slf4j
public class NewGBRuleDataCheckUtil {

    //新国标数据治理校验流程（固定模式）
    public static void checkData(Map<String, Object> signalMap, Map<String, Map<String, List<String>>> ruleMap, JSONArray retData) {
        try {
            //前置数据检测
            PreDataCheckUtil.checkPreData(signalMap, retData);

            //前置状态检测
            PreStateCheckUtil.checkPreState(signalMap, retData);

            //基础数据检测
            BaseDataCheckUtil.checkBaseData(signalMap, ruleMap, retData);

            //充电状态检测
            checkChargingState(signalMap, ruleMap, retData);

            //数据关联逻辑检测
            checkDataRelationLogic(signalMap, ruleMap, retData);

            //运行模式检测
            RunModeCheckUtil.checkRunModeData(signalMap, ruleMap, retData);
        } catch (GbException e) {
            e.printStackTrace();
        }
    }

    //新国标数据治理校验流程（规则配置模式-固定配置）
    public static void checkDataFromRule(Map<String, Object> signalMap, Map<String, Map<String, List<String>>> ruleMap, JSONArray retData) {
        List<NewGbRuleCodeEnum> bySort = NewGbRuleCodeEnum.getBySort();
        for (NewGbRuleCodeEnum newGbRuleCodeEnum : bySort) {
            BaseRuleValidate ruleValidate = RuleValidateFactory.getRuleValidate(newGbRuleCodeEnum.getCode());
            if (ruleValidate != null) {
                ruleValidate.validate(signalMap, ruleMap, retData);
            } else {

            }
        }
    }

    //新国标数据治理校验流程（规则配置模式-读取数据库）
    public static void checkDataFromDB(Map<String, Object> signalMap, Map<String, Map<String, List<String>>> ruleMap, JSONArray retData) {
        List<NewGbRuleCodeEnum> bySort = NewGbRuleCodeEnum.getBySort();
        for (NewGbRuleCodeEnum newGbRuleCodeEnum : bySort) {
            BaseRuleValidate ruleValidate = RuleValidateFactory.getRuleValidate(newGbRuleCodeEnum.getCode());
            if (ruleValidate != null) {
                ruleValidate.validate(signalMap, ruleMap, retData);
            } else {

            }
        }
    }

    private static void checkDataRelationLogic(Map<String, Object> signalMap, Map<String, Map<String, List<String>>> ruleMap, JSONArray retData) {

    }

    private static void checkChargingState(Map<String, Object> signalMap, Map<String, Map<String, List<String>>> ruleMap, JSONArray retData) {

    }

}
