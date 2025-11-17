//package com.chery.gb.realtime.algorithm.workflow.config;
//
//
//import com.chery.gb.realtime.algorithm.annotation.WorkFlowConfig;
//import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
//import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
//import com.chery.gb.realtime.algorithm.enums.RuleRelationEnum;
//import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;
//import com.chery.gb.realtime.algorithm.enums.SignalEnum;
//import com.chery.gb.realtime.algorithm.workflow.GbWorkFlowEnum;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static com.chery.gb.realtime.algorithm.enums.SignalEnum.*;
//
///**
// * @author wugaoyang
// * @date 2025/11/10 星期一
// *
// */
//@WorkFlowConfig(GbWorkFlowEnum.WF_3_8_1)
//public class WorkFlowConfig_3_8_1 extends BaseWorkFlowConfig {
//    public WorkFlowConfig_3_8_1() {
//        RuleConditionBO ruleConditionBO = new RuleConditionBO();
//        List<RuleDetailBO> condition = new ArrayList<>();
//        List<SignalEnum> signalEnums = Arrays.asList(SIGNAL_215C, SIGNAL_2232, 定位状态未定义, 通用报警故障总数, 动力蓄电池包号, 最小并联单元总数, SIGNAL_215D, 坐标系, 温度探针检测到的温度值, 动力蓄电池包温度探针个数, 动力蓄电池包个数, 动力蓄电池包电流, SIGNAL_207B, 可充电储能装置故障总数N1, SIGNAL_220B, 驱动电机故障总数N2, 高压对地绝缘电阻, SIGNAL_207A, 发动机故障总数N3, SIGNAL_2187, 本帧最小并联单元电压, 其他故障总数N4, 动力蓄电池包电压);
//        signalEnums.forEach(signalEnum -> {
//            condition.add(RuleDetailBO.builder().signalId(signalEnum.getCode()).signalRule(RuleSymbolEnum.NE.name()).detailRelation(RuleRelationEnum.AND.name()).build());
//        });
//        ruleConditionBO.setConditions(condition);
//        setCondition(ruleConditionBO);
//    }
//}
