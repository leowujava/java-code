package com.chery.gb.realtime.algorithm.workflow;


import java.util.ArrayList;
import java.util.List;

/**
 * 工作流枚举
 *
 * @author S00003829
 */
public enum WorkFlowEnum {

    ALL_DATA("1", "数据完整性", "检测整车、动力蓄电池、车辆位置、驱动电机、发动机、报警数据信息体是否上传", false, 0, null),

    PRE_STATE("2", "前置状态", "前置状态", false, 0, null),

    //车辆状态
    VEHICLE_STATE_IS_NULL("2-1", "车辆状态为null检测", "车辆状态为null检测", false, 0, PRE_STATE),
    VEHICLE_STATE_IS_ERROR_OR_INVALID("2-2", "车辆状态为异常或无效检测", "车辆状态为异常或无效检测", false, 0, PRE_STATE),
    VEHICLE_STATE_IS_NOT_IN_RANGE("2-3", "车辆状态未定义检测", "车辆状态未定义检测", false, 0, PRE_STATE),
    VEHICLE_STATE_IS_NOT_1_2("2-4", "车辆状态非1启动且非2熄火检测", "车辆状态非1启动且非2熄火检测", false, 0, PRE_STATE),
    VEHICLE_STATE_IS_2_SPEED_GT_5("2-5", "车辆状态为2熄火车速不为0", "车辆状态为2熄火车速不为0", false, 0, PRE_STATE),
    //充电状态
    CHARGING_STATE_IS_NULL("2-6", "充电状态为空", "充电状态为空", true, 0, PRE_STATE),
    CHARGING_STATE_IS_ERROR_OR_INVALID("2-7", "充电状态为异常或无效", "充电状态为异常或无效", false, 1, PRE_STATE),

    BASIC_ITEM("3", "基础项", "基础项", false, 0, null),
    CHARGING_STATE_IS_NOT_1_OR_4("4", "充电状态", "充电状态", false, 0, null),
    SPEED_IS_NULL("4-1", "车速校验空值", "车速校验是否为空", false, 0, CHARGING_STATE_IS_NOT_1_OR_4),
    SPEED_VALIDATE("4-1-1", "车速校验无效、异常、越界", "车速校验是否无效、异常、越界", false, 0, SPEED_IS_NULL),
    DISTANCE_IS_NULL("4-2", "里程校验控制", "里程校验是否为空", false, 0, CHARGING_STATE_IS_NOT_1_OR_4),
    DISTANCE_VALIDATE("4-2-1", "里程校验无效、异常、越界", "里程校验是否无效、异常、越界", false, 0, DISTANCE_IS_NULL),

    DATA_LOGIC("5", "数据关联逻辑", "数据关联逻辑检测", false, 0, null),
    RUNNING_MODE("6", "运行模式", "运行模式检测", false, 0, null),

    ;

    String code;
    String name;
    String desc;
    int skip;
    boolean isReturn;
    WorkFlowEnum parent;

    WorkFlowEnum(String code, String name, String desc, boolean isReturn, int skip, WorkFlowEnum parent) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.isReturn = isReturn;
        this.skip = skip;
        this.parent = parent;
    }

    public static List<WorkFlowEnum> getTopFlow() {
        List<WorkFlowEnum> workFlowEnums = new ArrayList<>();
        for (WorkFlowEnum value : WorkFlowEnum.values()) {
            if (value.parent == null) {
                workFlowEnums.add(value);
            }
        }
        return workFlowEnums;
    }

    public static List<WorkFlowEnum> getSubFlow(WorkFlowEnum parent) {
        List<WorkFlowEnum> workFlowEnums = new ArrayList<>();
        for (WorkFlowEnum value : WorkFlowEnum.values()) {
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

    public WorkFlowEnum getParent() {
        return parent;
    }

    public int getSkip() {
        return skip;
    }

    public boolean isReturn() {
        return isReturn;
    }
}
