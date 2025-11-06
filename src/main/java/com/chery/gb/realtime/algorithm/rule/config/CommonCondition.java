package com.chery.gb.realtime.algorithm.rule.config;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.RuleRelationEnum;
import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.signal.config.BaseSignalConfig;
import com.chery.gb.realtime.algorithm.signal.factory.SignalConfigFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用配置
 *
 * @author wugaoyang
 * @date 2025/11/5 星期三
 *
 */
public class CommonCondition {

    /**
     * 车辆状态 is null
     *
     * @return
     */
    public static RuleConditionBO vehicleStateIsNull(boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue(null).detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setDesc("车辆状态 is null");
        ruleConditionBO.setReturn(isReturn);
        return ruleConditionBO;
    }

    /**
     * 车辆状态 = 2 (熄火)
     * 并且
     * 车速 > 50(5km/h)
     *
     * @return
     */
    public static RuleConditionBO vehicleStateIS2AndSpeedGt5(boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("2").detailRelation(RuleRelationEnum.AND.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_2001.getCode()).signalRule(RuleSymbolEnum.GT.name()).signalValue("5").detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setDesc("车辆状态为熄火车速不为0");
        ruleConditionBO.setReturn(isReturn);
        return ruleConditionBO;
    }

    /**
     * 车辆状态不在 [1,2,3]内
     *
     * @return
     */
    public static RuleConditionBO vehicleStateNot1_2_3(boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("1").detailRelation(RuleRelationEnum.AND.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("2").detailRelation(RuleRelationEnum.AND.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("3").detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setDesc("车辆状态无定义");
        ruleConditionBO.setReturn(isReturn);
        return ruleConditionBO;
    }

    /**
     * 车辆状态非 1 启动且非 2 熄火
     *
     * @return
     */
    public static RuleConditionBO vehicleStateNot1_2() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("1").detailRelation(RuleRelationEnum.AND.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("2").detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setDesc("车辆状态非 1 启动且非 2 熄火");
        return ruleConditionBO;
    }

    /**
     * 充电状态 is null
     *
     * @return
     */
    public static RuleConditionBO chargingStateIsNull(boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setReturn(isReturn);
        ruleConditionBO.setDesc("充电状态 is null");
        return ruleConditionBO;
    }

    /**
     * 充电状态异常
     *
     * @return
     */
    public static RuleConditionBO chargingStateIsError(boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("254").build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setReturn(isReturn);
        ruleConditionBO.setDesc("充电状态异常");
        return ruleConditionBO;
    }

    /**
     * 充电状态无效
     *
     * @return
     */
    public static RuleConditionBO chargingStateIsInvalid(boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("255").build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setReturn(isReturn);
        ruleConditionBO.setDesc("充电状态异常");
        return ruleConditionBO;
    }

    /**
     * 充电状态有效范围[1,2,3,4]
     *
     * @return
     */
    public static RuleConditionBO chargingStateRange(boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("255").build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setReturn(isReturn);
        ruleConditionBO.setDesc("充电状态异常");
        return ruleConditionBO;
    }


    /**
     * 充电状态等于1或者等于4
     *
     * @return
     */
    public static RuleConditionBO chargingStateIs1_4() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("1").detailRelation(RuleRelationEnum.OR.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("4").detailRelation(RuleRelationEnum.OR.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setDesc("充电状态等于1或者等于4");
        return ruleConditionBO;
    }

    /**
     * 充电状态不等于1并且不等于4
     *
     * @return
     */
    public static RuleConditionBO chargingState2() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("1").detailRelation(RuleRelationEnum.AND.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("4").detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        return ruleConditionBO;
    }

    /**
     * 构建空值条件
     *
     * @param signalEnum
     * @param isReturn
     * @return
     */
    public static RuleConditionBO buildNullCondition(SignalEnum signalEnum, boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        ruleConditionBO.setReturn(isReturn);
        if (signalEnum == null) {
            return ruleConditionBO;
        }
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(signalEnum.getCode()).detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        return ruleConditionBO;
    }

    /**
     * 构建异常值条件
     *
     * @param signalEnum
     * @param isReturn
     * @return
     */
    public static RuleConditionBO buildErrorCondition(SignalEnum signalEnum, boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        ruleConditionBO.setReturn(isReturn);
        if (signalEnum == null) {
            return ruleConditionBO;
        }
        List<RuleDetailBO> condition = new ArrayList<>();
        BaseSignalConfig signalConfig = SignalConfigFactory.getByCode(signalEnum.getCode());
        if (signalConfig == null || signalConfig.getSignalConfigBO() == null) {
            return ruleConditionBO;
        }
        SignalConfigBO signalConfigBO = signalConfig.getSignalConfigBO();
        String errorValue = signalConfigBO.getErrorValue();
        if (StrUtil.isBlank(errorValue)) {
            return ruleConditionBO;
        }
        condition.add(RuleDetailBO.builder().signalId(signalEnum.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue(errorValue).detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        return ruleConditionBO;
    }

    /**
     * 构建无效值条件
     *
     * @param signalEnum
     * @param isReturn
     * @return
     */
    public static RuleConditionBO buildInvalidCondition(SignalEnum signalEnum, boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        ruleConditionBO.setReturn(isReturn);
        if (signalEnum == null) {
            return ruleConditionBO;
        }
        List<RuleDetailBO> condition = new ArrayList<>();
        BaseSignalConfig signalConfig = SignalConfigFactory.getByCode(signalEnum.getCode());
        if (signalConfig == null || signalConfig.getSignalConfigBO() == null) {
            return ruleConditionBO;
        }
        SignalConfigBO signalConfigBO = signalConfig.getSignalConfigBO();
        String invalidValue = signalConfigBO.getInvalidValue();
        if (StrUtil.isBlank(invalidValue)) {
            return ruleConditionBO;
        }
        condition.add(RuleDetailBO.builder().signalId(signalEnum.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue(invalidValue).detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        return ruleConditionBO;
    }

    /**
     * 构建有效值范围条件
     *
     * @param signalEnum
     * @param isReturn
     * @return
     */
    public static RuleConditionBO buildRangeCondition(SignalEnum signalEnum, boolean isReturn) {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        if (signalEnum == null) {
            return ruleConditionBO;
        }
        ruleConditionBO.setReturn(isReturn);
        List<RuleDetailBO> condition = new ArrayList<>();
        BaseSignalConfig signalConfig = SignalConfigFactory.getByCode(signalEnum.getCode());
        if (signalConfig == null || signalConfig.getSignalConfigBO() == null) {
            return ruleConditionBO;
        }
        SignalConfigBO signalConfigBO = signalConfig.getSignalConfigBO();
        Integer max = signalConfigBO.getMax();
        Integer min = signalConfigBO.getMin();
        if (max == null || min == null) {
            return ruleConditionBO;
        }
        condition.add(RuleDetailBO.builder().signalId(signalEnum.getCode()).signalRule(RuleSymbolEnum.GT.name()).signalValue(String.valueOf(max)).detailRelation(RuleRelationEnum.OR.name()).build());
        condition.add(RuleDetailBO.builder().signalId(signalEnum.getCode()).signalRule(RuleSymbolEnum.LT.name()).signalValue(String.valueOf(min)).detailRelation(RuleRelationEnum.OR.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setDesc(signalEnum.getName() + "无定义");
        return ruleConditionBO;
    }
}
