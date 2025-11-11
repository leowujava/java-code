package com.chery.gb.realtime.algorithm.workflow;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.bo.RuleValidateWorkFlowBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.rule.factory.RuleValidatorFactory;
import com.chery.gb.realtime.algorithm.rule.validator.BaseRuleValidator;
import com.chery.gb.realtime.algorithm.util.check.GbRuleCheckUtil;
import com.chery.gb.realtime.algorithm.workflow.config.BaseWorkFlowConfig;
import com.chery.gb.realtime.algorithm.workflow.factory.WorkFlowConfigFactory;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

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
        RuleValidateWorkFlowBO workFlow = buildFlowFromEnums();
        run(signalMap, ruleMap, retData, workFlow);
    }

    static void run(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData, RuleValidateWorkFlowBO workFlow) {
        //1、判断工作流是否为空
        if (workFlow == null) {
            return;
        }
        String workFlowName = workFlow.getName();
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < 35 - workFlowName.length() * 1.5; i++) {
            s.append("=");
        }
        System.out.println("====================执行工作流：" + workFlowName + s);
        boolean result = false;
        RuleValidateWorkFlowBO subWorkFlow = workFlow.getSubWorkFlow();
        RuleConditionBO ruleCondition = workFlow.getRuleCondition();
        //运行判断
        if (ruleCondition != null) {
            result = GbRuleCheckUtil.checkByCondition(signalMap, ruleCondition.getConditions(), retData, ruleCondition.getRuleCode());
            //如果条件判断结果为true，需要返回时，抛出异常
            if (result && (ruleCondition.isReturn() || workFlow.isReturn())) {
                throw new GbException(workFlowName);
            }
            //如果工作流有判断条件时，条件为true才能进入支流
            if (subWorkFlow != null && result) {
                run(signalMap, ruleMap, retData, subWorkFlow);
            }
        }
        //校验规则
        List<NewGbRuleCodeEnum> ruleCodeList = workFlow.getRuleCodeList();
        if (CollectionUtils.isNotEmpty(ruleCodeList)) {
            for (NewGbRuleCodeEnum newGbRuleCodeEnum : ruleCodeList) {
                String ruleCode = newGbRuleCodeEnum.getCode();
                BaseRuleValidator ruleValidate = RuleValidatorFactory.getRuleValidate(ruleCode);
                if (ruleValidate != null) {
                    result = result || ruleValidate.validate(signalMap, ruleMap, retData);
                    if (result && workFlow.isReturn()) {
                        throw new GbException(workFlowName);
                    }
                } else {
                    result = result || GbRuleCheckUtil.checkByRuleCode(signalMap, ruleMap, retData, ruleCode);
                }
            }
        }
        //运行支流
        if (ruleCondition == null && subWorkFlow != null) {
            run(signalMap, ruleMap, retData, subWorkFlow);
        }
        RuleValidateWorkFlowBO next = getNextWorkFlow(workFlow, result);
        //进入下一步
        if (next != null) {
            run(signalMap, ruleMap, retData, next);
        }
    }

    private static RuleValidateWorkFlowBO getNextWorkFlow(RuleValidateWorkFlowBO workFlow, boolean result) {
        int skip = workFlow.getSkip();
        workFlow = workFlow.getNextWorkFlow();
        if (workFlow == null) {
            return null;
        }
        if (!result) {
            return workFlow;
        }
        for (int i = 1; i <= skip; i++) {
            if (workFlow == null) {
                break;
            }
            workFlow = workFlow.getNextWorkFlow();
            if (workFlow == null) {
                break;
            }
        }
        return workFlow;
    }

    /**
     * 设置下一步的工作流
     *
     * @param preWorkFlow
     * @param workFlow
     * @param subWorkFlow
     */
    private static void setNextFlow(RuleValidateWorkFlowBO preWorkFlow, RuleValidateWorkFlowBO workFlow, RuleValidateWorkFlowBO subWorkFlow) {
        if (preWorkFlow != null) {
            preWorkFlow.setNextWorkFlow(workFlow);
            if (subWorkFlow != null) {
                preWorkFlow.setSubWorkFlow(subWorkFlow);
            }
        }
    }

    private static RuleValidateWorkFlowBO buildFlowFromEnums() {
        RuleValidateWorkFlowBO workFlow = new RuleValidateWorkFlowBO();
        workFlow.setName("开始");
        List<WorkFlowEnum> topFlows = WorkFlowEnum.getTopFlow();
        RuleValidateWorkFlowBO preWorkFlow = null;
        for (WorkFlowEnum topFlow : topFlows) {
            if (preWorkFlow == null) {
                preWorkFlow = buildFlowFromEnums2(workFlow, topFlow);
            } else {
                preWorkFlow = buildFlowFromEnums2(preWorkFlow, topFlow);
            }
        }
        return workFlow;
    }

    private static RuleValidateWorkFlowBO buildFlowFromEnums2(RuleValidateWorkFlowBO preWorkFlow, WorkFlowEnum topFlow) {
        RuleValidateWorkFlowBO workFlow = BeanUtil.copyProperties(topFlow, RuleValidateWorkFlowBO.class);
        BaseWorkFlowConfig workFlowConfig = WorkFlowConfigFactory.getByCode(topFlow.getCode());
        if (workFlowConfig != null) {
            workFlow.setRuleCondition(workFlowConfig.getCondition());
            workFlow.setRuleCodeList(workFlowConfig.getRuleCodeList());
        }
        List<WorkFlowEnum> subEnums = WorkFlowEnum.getSubFlow(topFlow);
        RuleValidateWorkFlowBO subFlow = null;
        if (CollectionUtils.isNotEmpty(subEnums)) {
            for (WorkFlowEnum s : subEnums) {
                if (subFlow == null) {
                    subFlow = buildFlowFromEnums2(new RuleValidateWorkFlowBO(), s);
                    setNextFlow(preWorkFlow, workFlow, null);
                    setNextFlow(workFlow, null, subFlow);
                } else {
                    subFlow = buildFlowFromEnums2(subFlow, s);
                }
            }
        } else {
            setNextFlow(preWorkFlow, workFlow, null);
        }
        return workFlow;
    }

}
