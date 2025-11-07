package com.chery.gb.realtime.algorithm.util;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.bo.RuleValidateWorkFlow;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.rule.config.CommonCondition;
import com.chery.gb.realtime.algorithm.rule.factory.RuleValidatorFactory;
import com.chery.gb.realtime.algorithm.rule.validator.BaseRuleValidator;
import com.chery.gb.realtime.algorithm.util.check.GbRuleCheckUtil;
import org.apache.commons.collections.CollectionUtils;

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

    /**
     * 初始化工作流
     *
     * @return
     */
    private static RuleValidateWorkFlow initWorkFlow() {
        RuleValidateWorkFlow workFlow = buildDataIntegrityValidateFlow();
        RuleValidateWorkFlow preStateValidateFow = buildPreStateValidateFow(workFlow);
        RuleValidateWorkFlow workFlow1 = buildWorkFlow(preStateValidateFow);
        return workFlow;
    }

    static void run(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData, RuleValidateWorkFlow workFlow) {
        //1、判断工作流是否为空
        if (workFlow == null) {
            return;
        }
        System.out.println("工作流：" + workFlow.getName());
        boolean result = runValidateWorkFlow(signalMap, ruleMap, retData, workFlow);
        //2、获取处理结果进行下一步
        //2-1、如果是判断的工作流，并且结果为true，则进入支流
        if (result && 1 == workFlow.getType()) {
            run(signalMap, ruleMap, retData, workFlow.getNextWorkFlow2());
        }
        //2-2、进入主流下一步
        run(signalMap, ruleMap, retData, workFlow.getNextWorkFlow());
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
        if (CollectionUtils.isNotEmpty(ruleCodeList)) {
            for (NewGbRuleCodeEnum newGbRuleCodeEnum : ruleCodeList) {
                String ruleCode = newGbRuleCodeEnum.getCode();
                BaseRuleValidator ruleValidate = RuleValidatorFactory.getRuleValidate(ruleCode);
                if (ruleValidate != null) {
                    boolean validate = ruleValidate.validate(signalMap, ruleMap, retData);
                    if (validate && workFlow.isReturn()) {
                        throw new GbException(workFlow.getName());
                    }
                } else {
                    GbRuleCheckUtil.checkByRuleCode(signalMap, ruleMap, retData, ruleCode);
                }
            }
        }
        return runCheckWorkFlow(signalMap, ruleMap, retData, workFlow);
    }

    /**
     * 前置状态检测工作流
     *
     * @param preWorkFlow
     * @return
     */
    private static RuleValidateWorkFlow buildPreStateValidateFow(RuleValidateWorkFlow preWorkFlow) {
        RuleValidateWorkFlow workFlow = new RuleValidateWorkFlow();
        workFlow.setType(2);
        workFlow.setName("前置状态检测工作流");
        setNextFlow(preWorkFlow, workFlow, null);
        RuleValidateWorkFlow vehicleStateIsNullFlow = buildVehicleStateIsNullFlow(workFlow);
        RuleValidateWorkFlow vehicleStateValidate = buildVehicleStateValidate(vehicleStateIsNullFlow);
        RuleValidateWorkFlow vehicleStateValidate2 = buildVehicleStateValidate2(vehicleStateValidate);
        return vehicleStateValidate2;
    }

    private static void setNextFlow(RuleValidateWorkFlow preWorkFlow, RuleValidateWorkFlow workFlow, RuleValidateWorkFlow workFlow2) {
        if (preWorkFlow != null) {
            preWorkFlow.setNextWorkFlow(workFlow);
            preWorkFlow.setNextWorkFlow2(workFlow2);
        }
    }

    /**
     * 车辆状态=2（熄火）并且车速大于5
     *
     * @param preWorkFlow
     * @return
     */
    private static RuleValidateWorkFlow buildWorkFlow(RuleValidateWorkFlow preWorkFlow) {
        RuleValidateWorkFlow workFlow = new RuleValidateWorkFlow();
        setNextFlow(preWorkFlow, workFlow, null);
        workFlow.setName("车辆状态=2（熄火）并且车速大于5");
        workFlow.setReturn(false);
        workFlow.setType(2);
        List<NewGbRuleCodeEnum> ruleCodeList = new ArrayList<>();
        ruleCodeList.add(RULE_CODE_250);
        return workFlow;
    }

    /**
     * 充电状态
     *
     * @param preWorkFlow
     * @return
     */
    private static RuleValidateWorkFlow buildWorkFlow2(RuleValidateWorkFlow preWorkFlow) {
        RuleValidateWorkFlow workFlow = new RuleValidateWorkFlow();
        setNextFlow(preWorkFlow, workFlow, null);
        workFlow.setReturn(false);
        workFlow.setType(2);
        List<NewGbRuleCodeEnum> ruleCodeList = new ArrayList<>();
        ruleCodeList.add(RULE_CODE_250);
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
        workFlow.setName("车辆状态为null的校验");
        workFlow.setType(1);
        workFlow.setReturn(true);
        workFlow.setRuleConditionBO(CommonCondition.buildVehicleStateIsNull(true));
        setNextFlow(preWorkFlow, workFlow, null);
        return workFlow;
    }


    /**
     * 车辆状态校验工作流：异常、无效、无定义
     *
     * @param preWorkFlow
     */
    private static RuleValidateWorkFlow buildVehicleStateValidate(RuleValidateWorkFlow preWorkFlow) {
        RuleValidateWorkFlow workFlow = new RuleValidateWorkFlow();
        workFlow.setName("车辆状态校验");
        workFlow.setReturn(true);
        workFlow.setType(2);
        List<NewGbRuleCodeEnum> ruleCodeList = new ArrayList<>();
        ruleCodeList.add(RULE_CODE_22);
        ruleCodeList.add(RULE_CODE_23);
        ruleCodeList.add(RULE_CODE_24);
        workFlow.setRuleCodeList(ruleCodeList);
        setNextFlow(preWorkFlow, workFlow, null);
        return workFlow;
    }


    /**
     * 车辆状态校验：不等于1并且不等于2
     *
     * @param preWorkFlow
     * @return
     */
    private static RuleValidateWorkFlow buildVehicleStateValidate2(RuleValidateWorkFlow preWorkFlow) {
        RuleValidateWorkFlow workFlow = new RuleValidateWorkFlow();
        workFlow.setName("车辆状态校验：不等于1并且不等于2");
        workFlow.setReturn(true);
        workFlow.setType(1);
        workFlow.setRuleConditionBO(CommonCondition.vehicleStateNot1_2(true));
        setNextFlow(preWorkFlow, workFlow, null);
        return workFlow;
    }

}
