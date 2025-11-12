package com.chery.gb.realtime.algorithm.workflow;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.bo.RuleValidateWorkFlowBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.rule.config.BaseRuleConfig;
import com.chery.gb.realtime.algorithm.rule.factory.RuleConfigFactory;
import com.chery.gb.realtime.algorithm.rule.factory.RuleValidatorFactory;
import com.chery.gb.realtime.algorithm.rule.validator.BaseRuleValidator;
import com.chery.gb.realtime.algorithm.util.CommonDataUtil;
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
        List<GbWorkFlowEnum> topFlows = GbWorkFlowEnum.getTopFlow();
        RuleValidateWorkFlowBO workFlow = buildFlowFromEnums(topFlows);
        run(signalMap, ruleMap, retData, workFlow);
    }

    static void run(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData, RuleValidateWorkFlowBO workFlow) {
        //1、判断工作流是否为空
        if (workFlow == null) {
            return;
        }
        String workFlowName = workFlow.getName();
        StringBuffer s = new StringBuffer();
        int length = workFlowName.length();
        for (int i = 0; i < 35 - length * 1.5; i++) {
            s.append("=");
        }
        if (length % 2 == 0) {
            s.append("=");
        }
        CommonDataUtil.log("====================工作流：" + workFlow.getCode() + ":" + workFlowName + s);
        //校验规则
        List<NewGbRuleCodeEnum> ruleCodeList = workFlow.getRuleCodeList();
        if (CollectionUtils.isNotEmpty(ruleCodeList)) {
            for (NewGbRuleCodeEnum newGbRuleCodeEnum : ruleCodeList) {
                String ruleCode = newGbRuleCodeEnum.getCode();
                BaseRuleValidator ruleValidate = RuleValidatorFactory.getRuleValidate(ruleCode);
                boolean flag;
                if (ruleValidate != null) {
                    flag = ruleValidate.validate(signalMap, ruleMap, retData);
                } else {
                    flag = GbRuleCheckUtil.checkByRuleCode(signalMap, ruleMap, retData, ruleCode);
                }
                if (flag) {
                    BaseRuleConfig config = RuleConfigFactory.getConfig(ruleCode, ruleMap);
                    if (config != null && config.getConditionBO() != null) {
                        CommonDataUtil.log("触发规则：" + ruleCode + ":" + config.getConditionBO().getDesc());
                    }
                }
                if (flag && workFlow.isReturn()) {
                    throw new GbException(workFlowName);
                }
            }
        }
        //运行判断
        boolean result = false;
        RuleValidateWorkFlowBO subWorkFlow = workFlow.getSubWorkFlow();
        RuleConditionBO ruleCondition = workFlow.getRuleCondition();
        if (ruleCondition != null) {
            result = GbRuleCheckUtil.checkByCondition(signalMap, ruleCondition.getConditions(), retData, ruleCondition.getRuleCode());
            String desc = ruleCondition.getDesc();
            if (result && StrUtil.isNotBlank(desc)) {
                CommonDataUtil.log(desc);
            }
            //如果条件判断结果为true，需要返回时，抛出异常
            if (result && (ruleCondition.isReturn() || workFlow.isReturn())) {
                throw new GbException(workFlowName);
            }
            //如果工作流有判断条件时，条件为true才能进入支流
            if (subWorkFlow != null && result) {
                run(signalMap, ruleMap, retData, subWorkFlow);
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

    private static RuleValidateWorkFlowBO buildFlowFromEnums(List<GbWorkFlowEnum> topFlows) {
        RuleValidateWorkFlowBO workFlow = new RuleValidateWorkFlowBO();
        workFlow.setName("开始");
        RuleValidateWorkFlowBO preWorkFlow = null;
        for (GbWorkFlowEnum topFlow : topFlows) {
            if (preWorkFlow == null) {
                preWorkFlow = buildFlowFromEnums2(workFlow, topFlow);
            } else {
                preWorkFlow = buildFlowFromEnums2(preWorkFlow, topFlow);
            }
        }
        return workFlow;
    }

    private static RuleValidateWorkFlowBO buildFlowFromEnums2(RuleValidateWorkFlowBO preWorkFlow, GbWorkFlowEnum topFlow) {
        RuleValidateWorkFlowBO workFlow = BeanUtil.copyProperties(topFlow, RuleValidateWorkFlowBO.class);
        BaseWorkFlowConfig workFlowConfig = WorkFlowConfigFactory.getByCode(topFlow.getCode());
        if (workFlowConfig != null) {
            workFlow.setRuleCondition(workFlowConfig.getCondition());
            workFlow.setRuleCodeList(workFlowConfig.getRuleCodeList());
        }
        List<GbWorkFlowEnum> subEnums = GbWorkFlowEnum.getSubFlow(topFlow);
        RuleValidateWorkFlowBO subFlow = null;
        if (CollectionUtils.isNotEmpty(subEnums)) {
            for (GbWorkFlowEnum s : subEnums) {
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
