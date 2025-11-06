package com.chery.gb.realtime.algorithm.util;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.bo.RuleValidateWorkFlow;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.rule.config.CommonCondition;
import com.chery.gb.realtime.algorithm.rule.factory.RuleValidateFactory;
import com.chery.gb.realtime.algorithm.rule.validator.BaseRuleValidator;
import com.chery.gb.realtime.algorithm.util.check.GbRuleCheckUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum.*;

/**
 * @author wugaoyang
 * @date 2025/11/6 星期四
 *
 */
public class RuleValidateWorkFlowUtil {


    /**
     * 运行工作流
     *
     * @param signalMap
     * @param ruleMap
     * @param retData
     */
    public static void run(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {
        run(signalMap, ruleMap, retData, initWorkFlow());
    }

    static void run(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData, RuleValidateWorkFlow workFlow) {
        //1、判断工作流类型进行处理
        if (workFlow == null || workFlow.getType() == null) {
            return;
        }
        boolean result;
        if (workFlow.getType() == 1) {
            result = runCheckWorkFlow(signalMap, ruleMap, retData, workFlow);
        } else {
            result = runValidateWorkFlow(signalMap, ruleMap, retData, workFlow);
        }
        //2、获取处理结果进行下一步
        if (result) {
            RuleValidateWorkFlow nextWorkFlow = workFlow.getNextWorkFlow2();
            run(signalMap, ruleMap, retData, nextWorkFlow);
        } else {
            RuleValidateWorkFlow nextWorkFlow = workFlow.getNextWorkFlow();
            run(signalMap, ruleMap, retData, nextWorkFlow);
        }
    }

    /**
     * 运行判断工作流
     *
     * @param signalMap
     * @param ruleMap
     * @param retData
     * @param workFlow
     * @return
     */
    private static boolean runCheckWorkFlow(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData, RuleValidateWorkFlow workFlow) {
        RuleConditionBO ruleConditionBO = workFlow.getRuleConditionBO();
        if (ruleConditionBO == null) {
            return false;
        }
        boolean check = GbRuleCheckUtil.checkByCondition(signalMap, ruleConditionBO.getConditions(), retData, null);
        if (check && workFlow.isReturn()) {
            throw new GbException(workFlow.getName());
        }
        return check;
    }

    /**
     * 运行校验工作流
     *
     * @param signalMap
     * @param ruleMap
     * @param retData
     * @param workFlow
     * @return
     */
    private static boolean runValidateWorkFlow(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData, RuleValidateWorkFlow workFlow) {
        List<NewGbRuleCodeEnum> ruleCodeList = workFlow.getRuleCodeList();
        for (NewGbRuleCodeEnum newGbRuleCodeEnum : ruleCodeList) {
            String ruleCode = newGbRuleCodeEnum.getCode();
            BaseRuleValidator ruleValidate = RuleValidateFactory.getRuleValidate(ruleCode);
            if (ruleValidate != null) {
                ruleValidate.validate(signalMap, ruleMap, retData);
                if (workFlow.isReturn()) {
                    throw new GbException(workFlow.getName());
                }
            } else {
                GbRuleCheckUtil.checkByRuleCode(signalMap, ruleMap, retData, ruleCode);
            }
        }
        return false;
    }

    /**
     * 初始化工作流
     *
     * @return
     */
    private static RuleValidateWorkFlow initWorkFlow() {
        RuleValidateWorkFlow workFlow = buildDataIntegrityValidateFlow();
        RuleValidateWorkFlow vehicleStateIsNullFlow = buildVehicleStateIsNullFlow(workFlow);
        RuleValidateWorkFlow vehicleStateValidate = buildVehicleStateValidate(vehicleStateIsNullFlow);
        RuleValidateWorkFlow vehicleStateValidate2 = buildVehicleStateValidate2(vehicleStateValidate);
        return workFlow;
    }

    /**
     * 构建数据完整性校验
     *
     * @return
     */
    private static RuleValidateWorkFlow buildDataIntegrityValidateFlow() {
        RuleValidateWorkFlow ruleValidateWorkFlow = new RuleValidateWorkFlow();
        ruleValidateWorkFlow.setName("数据完整性校验");
        ruleValidateWorkFlow.setReturn(true);
        ruleValidateWorkFlow.setType(2);
        List<NewGbRuleCodeEnum> ruleCodeList = new ArrayList<>();
        ruleCodeList.add(RULE_CODE_239);
        ruleCodeList.add(RULE_CODE_240);
        ruleCodeList.add(RULE_CODE_241);
        ruleCodeList.add(RULE_CODE_242);
        ruleCodeList.add(RULE_CODE_245);
        ruleCodeList.add(RULE_CODE_246);
        ruleCodeList.add(RULE_CODE_258);
        ruleValidateWorkFlow.setRuleCodeList(ruleCodeList);
        return ruleValidateWorkFlow;
    }

    /**
     * 车辆状态为null的校验工作流
     *
     * @return
     */
    private static RuleValidateWorkFlow buildVehicleStateIsNullFlow(RuleValidateWorkFlow preWorkFlow) {
        RuleValidateWorkFlow workFlow = new RuleValidateWorkFlow();
        if (preWorkFlow != null) {
            preWorkFlow.setNextWorkFlow(workFlow);
        }
        workFlow.setReturn(true);
        workFlow.setType(1);
        workFlow.setRuleConditionBO(CommonCondition.buildVehicleStateIsNull(true));
        return workFlow;
    }


    /**
     * 车辆状态校验工作流：异常、无效、无定义
     *
     * @param workFlow
     */
    private static RuleValidateWorkFlow buildVehicleStateValidate(RuleValidateWorkFlow workFlow) {
        RuleValidateWorkFlow ruleValidateWorkFlow = new RuleValidateWorkFlow();
        ruleValidateWorkFlow.setName("车辆状态校验工作流");
        ruleValidateWorkFlow.setReturn(true);
        ruleValidateWorkFlow.setType(2);
        List<NewGbRuleCodeEnum> ruleCodeList = new ArrayList<>();
        ruleCodeList.add(RULE_CODE_22);
        ruleCodeList.add(RULE_CODE_23);
        ruleCodeList.add(RULE_CODE_24);
        ruleValidateWorkFlow.setRuleCodeList(ruleCodeList);
        workFlow.setNextWorkFlow(ruleValidateWorkFlow);
        return ruleValidateWorkFlow;
    }


    /**
     * 车辆状态校验：不等于1并且不等于2
     *
     * @param preWorkFlow
     * @return
     */
    private static RuleValidateWorkFlow buildVehicleStateValidate2(RuleValidateWorkFlow preWorkFlow) {
        RuleValidateWorkFlow workFlow = new RuleValidateWorkFlow();
        if (preWorkFlow != null) {
            preWorkFlow.setNextWorkFlow(workFlow);
        }
        workFlow.setReturn(true);
        workFlow.setType(1);
        workFlow.setRuleConditionBO(CommonCondition.vehicleStateNot1_2(true));
        preWorkFlow.setNextWorkFlow(workFlow);
        return workFlow;
    }

}
