package com.chery.gb.realtime.algorithm.rule.config;


import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.RuleRelationEnum;
import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

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
    public static RuleConditionBO vehicleStateIsNull() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue(null).detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setDesc("车辆状态 is null");
        return ruleConditionBO;
    }

    /**
     * 车辆状态 = 2 (熄火)
     * 并且
     * 车速 > 50(5km/h)
     *
     * @return
     */
    public static RuleConditionBO vehicleStateIS2AndSpeedGt50() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("2").detailRelation(RuleRelationEnum.AND.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_2001.getCode()).signalRule(RuleSymbolEnum.GT.name()).signalValue("5").detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setDesc("车辆状态为熄火车速不为0");
        return  ruleConditionBO;
    }

    /**
     * 车辆状态不在 [1,2,3]内
     * @return
     */
    public static RuleConditionBO vehicleStateNot123() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("1").detailRelation(RuleRelationEnum.AND.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("2").detailRelation(RuleRelationEnum.AND.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("3").detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setDesc("车辆状态无定义");
        return  ruleConditionBO;
    }
    /**
     * 车辆状态非 1 启动且非 2 熄火
     * @return
     */
    public static RuleConditionBO vehicleStateNot12() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("1").detailRelation(RuleRelationEnum.AND.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_220C.getCode()).signalRule(RuleSymbolEnum.NE.name()).signalValue("2").detailRelation(RuleRelationEnum.AND.name()).build());
        ruleConditionBO.setConditions(condition);
        ruleConditionBO.setDesc("车辆状态非 1 启动且非 2 熄火");
        return  ruleConditionBO;
    }

    /**
     * 充电状态is null
     *
     * @return
     */
    public static RuleConditionBO chargingStateIsNull() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).build());
        return ruleConditionBO;
    }


    /**
     * 充电状态等于1或者等于4
     *
     * @return
     */
    public static RuleConditionBO chargingState() {
        RuleConditionBO ruleConditionBO = new RuleConditionBO();
        List<RuleDetailBO> condition = new ArrayList<>();
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("1").detailRelation(RuleRelationEnum.OR.name()).build());
        condition.add(RuleDetailBO.builder().signalId(SignalEnum.SIGNAL_21AA.getCode()).signalRule(RuleSymbolEnum.EQ.name()).signalValue("4").detailRelation(RuleRelationEnum.OR.name()).build());
        ruleConditionBO.setConditions(condition);
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
}
