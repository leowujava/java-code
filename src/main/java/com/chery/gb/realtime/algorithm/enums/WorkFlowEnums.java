package com.chery.gb.realtime.algorithm.enums;


import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum.*;

/**
 * 工作流枚举
 *
 * @author S00003829
 */
public enum WorkFlowEnums {

    ALL_DATA("1", "数据完整性", "检测整车、动力蓄电池、车辆位置、驱动电机、发动机、报警数据信息体是否上传", false, 0, null, null, null),

    PRE_STATE("2", "前置状态", "前置状态", false, 0, null, null, null),

    //车辆状态
    VEHICLE_STATE_IS_NULL("2-1", "车辆状态为null检测", "车辆状态为null检测", false, 0, null, Arrays.asList(RULE_CODE_VEHICLE_STATE_IS_NULL), PRE_STATE),
    VEHICLE_STATE_IS_ERROR_OR_INVALID("2-2", "车辆状态为异常或无效检测", "车辆状态为异常或无效检测", false, 0, null, Arrays.asList(RULE_CODE_22, RULE_CODE_23), PRE_STATE),
    VEHICLE_STATE_IS_NOT_IN_RANGE("2-3", "车辆状态未定义检测", "车辆状态未定义检测", false, 0, null, Arrays.asList(RULE_CODE_24), PRE_STATE),
    VEHICLE_STATE_IS_NOT_1_2("2-4", "车辆状态非1启动且非2熄火检测", "车辆状态非1启动且非2熄火检测", false, 0, null, Arrays.asList(RULE_CODE_259), PRE_STATE),
    VEHICLE_STATE_IS_2_SPEED_GT_5("2-5", "车辆状态为2熄火车速不为0", "车辆状态为2熄火车速不为0", false, 0, null, Arrays.asList(RULE_CODE_250), PRE_STATE),
    //充电状态
    CHARGING_STATE_IS_NULL("2-6", "充电状态为空", "充电状态为空", true, 0, null, Arrays.asList(RULE_CODE_CHARGING_STATE_IS_NULL), PRE_STATE),
    CHARGING_STATE_IS_ERROR_OR_INVALID("2-7", "充电状态为异常或无效", "充电状态为异常或无效", false, 1, null, Arrays.asList(RULE_CODE_25, RULE_CODE_26), PRE_STATE),

    BASIC_ITEM("3", "基础项", "基础项", false, 0, null, null, null),
    CHARGING_STATE_IS_1_OR_4("4", "充电状态", "充电状态", false, 0, null, null, null),
    DATA_LOGIC("5", "数据关联逻辑", "数据关联逻辑检测", false, 0, null, null, null),
    RUNNING_MODE("6", "运行模式", "运行模式检测", false, 0, null, null, null),

    ;

    String code;
    String name;
    String desc;
    int skip;
    boolean isReturn;
    RuleConditionBO ruleConditionBO;
    List<NewGbRuleCodeEnum> ruleCodeList;
    WorkFlowEnums parent;

    WorkFlowEnums(String code, String name, String desc, boolean isReturn, int skip, RuleConditionBO ruleConditionBO, List<NewGbRuleCodeEnum> ruleCodeList, WorkFlowEnums parent) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.isReturn = isReturn;
        this.skip = skip;
        this.ruleConditionBO = ruleConditionBO;
        this.parent = parent;
        this.ruleCodeList = ruleCodeList;
    }

    public static List<WorkFlowEnums> getTopFlow() {
        List<WorkFlowEnums> workFlowEnums = new ArrayList<>();
        for (WorkFlowEnums value : WorkFlowEnums.values()) {
            if (value.parent == null) {
                workFlowEnums.add(value);
            }
        }
        return workFlowEnums;
    }

    public static List<WorkFlowEnums> getSubFlow(WorkFlowEnums parent) {
        List<WorkFlowEnums> workFlowEnums = new ArrayList<>();
        for (WorkFlowEnums value : WorkFlowEnums.values()) {
            if (value.parent == parent) {
                workFlowEnums.add(value);
            }
        }
        return workFlowEnums;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public WorkFlowEnums getParent() {
        return parent;
    }

    public int getSkip() {
        return skip;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public List<NewGbRuleCodeEnum> getRuleCodeList() {
        return ruleCodeList;
    }
}
