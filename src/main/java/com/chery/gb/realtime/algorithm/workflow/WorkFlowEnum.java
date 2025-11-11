package com.chery.gb.realtime.algorithm.workflow;


import java.util.ArrayList;
import java.util.List;

/**
 * 工作流枚举
 *
 * @author S00003829
 */
public enum WorkFlowEnum {

    //数据完整性
    ALL_DATA("1", "数据完整性", "检测整车、动力蓄电池、车辆位置、驱动电机、发动机、报警数据信息体是否上传", false, 0, null),

    //前置状态
    PRE_STATE("2", "前置状态", "前置状态", false, 0, null),
    //车辆状态
    VEHICLE_STATE_IS_NULL("2-1", "车辆状态为null检测", "车辆状态为null检测", false, 0, PRE_STATE),
    VEHICLE_STATE_IS_ERROR_OR_INVALID("2-2", "车辆状态为异常或无效检测", "车辆状态为异常或无效检测", false, 0, PRE_STATE),
    VEHICLE_STATE_IS_NOT_IN_RANGE("2-3", "车辆状态未定义检测", "车辆状态未定义检测", false, 0, PRE_STATE),
    VEHICLE_STATE_IS_NOT_1_2("2-4", "车辆状态非1启动且非2熄火检测", "车辆状态非1启动且非2熄火检测", true, 0, PRE_STATE),
    VEHICLE_STATE_IS_2_SPEED_GT_5("2-5", "车辆状态为2熄火车速不为0", "车辆状态为2熄火车速不为0", false, 0, PRE_STATE),
    //充电状态
    CHARGING_STATE_IS_NULL("2-6", "充电状态为空", "充电状态为空", true, 0, PRE_STATE),
    CHARGING_STATE_IS_ERROR_OR_INVALID("2-7", "充电状态为异常或无效", "充电状态为异常或无效", false, 1, PRE_STATE),
    CHARGING_STATE_IS_OUT_OF_RANGE("2-8", "充电状态范围检测", "充电状态范无定义", false, 0, PRE_STATE),
    VEHICLE_STATE_IS_2_AND_CHARGING_STATE_IS_3("2-9", "车辆状态为2且充电状态为3", "熄火未充电", true, 0, PRE_STATE),

    //基础项
    BASIC_ITEM("3", "基础项", "基础项", false, 0, null),
    FUEL_CELL_DATA_VALIDATION("3-2", "燃料电池汽车检测", "燃料电池数据检测", false, 0, BASIC_ITEM),
    SUPERCAPACITOR_DATA_VALIDATION("3-3", "超级电容器汽车检测", "超级电容数据检测", false, 0, BASIC_ITEM),
    HIGHEST_ALERT_LEVEL_IS_4("3-4", "最高报警等级=4", "最高报警等级=4", false, 0, BASIC_ITEM),
    ALERT_LEVEL_VALIDATE("3-5", "最高报警等级数据关联逻辑检测", "最高报警等级数据关联逻辑检测", false, 0, BASIC_ITEM),
    POWER_CELL_DATA_VALIDATE("3-6", "动力蓄电池数据关联逻辑检测", "动力蓄电池数据关联逻辑检测", false, 0, BASIC_ITEM),
    MINIMUM_PARALLEL_UNIT_DATA_VALIDATE("3-7", "最小并联单元数据关联逻辑检测", "最小并联单元数据关联逻辑检测", false, 0, BASIC_ITEM),
    TOTAL_VOLTAGE_DATA_VALIDATE("3-8", "总电压数据关联逻辑检测", "总电压数据关联逻辑检测", false, 0, BASIC_ITEM),
    FAULT_DATA_VALIDATE("3-9", "故障数据关联逻辑检测", "故障数据关联逻辑检测", false, 0, BASIC_ITEM),
    FAULT_DATA_NULL_VALIDATE("3-9-1", "故障数据为空检测", "故障数据关联逻辑检测", false, 2, FAULT_DATA_VALIDATE),
    FAULT_DATA_ERROR_OR_VALID_VALIDATE("3-9-2", "故障数据为异常或无效检测", "故障数据为异常或无效检测", false, 1, FAULT_DATA_VALIDATE),
    FAULT_DATA_RANGE_VALIDATE("3-9-3", "故障数据范围检测", "故障数据范围检测", false, 0, FAULT_DATA_VALIDATE),


    //充电状态不是1和4
    CHARGING_STATE_IS_NOT_1_OR_4("4", "充电状态不是1和4", "充电状态不是1和4", false, 0, null),
    SPEED_IS_NULL("4-1", "车速校验空值", "车速校验是否为空", false, 0, CHARGING_STATE_IS_NOT_1_OR_4),
    SPEED_VALIDATE("4-1-1", "车速校验无效、异常、越界", "车速校验是否无效、异常、越界", false, 0, SPEED_IS_NULL),
    DISTANCE_IS_NULL("4-2", "里程校验控制", "里程校验是否为空", false, 0, CHARGING_STATE_IS_NOT_1_OR_4),
    DISTANCE_VALIDATE("4-2-1", "里程校验无效、异常、越界", "里程校验是否无效、异常、越界", false, 0, DISTANCE_IS_NULL),

    //数据关联逻辑
    DATA_LOGIC("5", "数据关联逻辑", "数据关联逻辑检测：挡位、车速", false, 0, null),

    //运行模式
    RUNNING_MODE("6", "运行模式", "运行模式检测", false, 0, null),
    RUNNING_MODE_NULL_VALIDATE("6-1", "运行模式为空检测", "运行模式为空检测", true, 0, RUNNING_MODE),
    RUNNING_MODE_ERROR_OR_VALID_VALIDATE("6-2", "运行模式异常或无效检测", "运行模式异常或无效检测", true, 0, RUNNING_MODE),
    RUNNING_MODE_RANGE_VALIDATE("6-3", "运行模式范围检测", "运行模式范围检测", false, 0, RUNNING_MODE),
    CHARGING_STATE_IS_NOT_1_AND_VEHICLE_STATE_IS_NOT_2("6-4", "充电模式不等于1且车辆状态不等于2", "充电模式不等于1且车辆状态不等于2", true, 0, RUNNING_MODE),

    //纯电模式
    RUNNING_MODE_1("6-4-1", "运行模式1", "运行模式1", false, 0, CHARGING_STATE_IS_NOT_1_AND_VEHICLE_STATE_IS_NOT_2),
    CRANKSHAFT_SPEED_VALIDATE("6-4-1-1", "运行模式1：曲轴转速>0", "纯电模式下曲轴转速>0", false, 0, RUNNING_MODE_1),
    DRIVE_MOTOR_LIST("6-4-1-2", "运行模式1：驱动电机列表数据检测", "驱动电机列表数据检测", false, 0, RUNNING_MODE_1),
    TOTAL_VOLTAGE_LT_0("6-4-1-3", "运行模式1：总电流<0", "纯电行驶中能量回收显示停车充电", false, 0, RUNNING_MODE_1),

    //
    RUNNING_MODE_2("6-4-2", "运行模式2", "运行模式2", false, 0, CHARGING_STATE_IS_NOT_1_AND_VEHICLE_STATE_IS_NOT_2),
    DRIVE_MOTOR_LIST_2("6-4-2-1", "运行模式2：驱动电机列表数据检测2", "驱动电机列表数据检测", false, 0, RUNNING_MODE_2),
    CRANKSHAFT_SPEED_VALIDATE_2("6-4-2-2", "运行模式2：曲轴转速>0", "曲轴转速异常、无效或越界", false, 0, RUNNING_MODE_2),


    RUNNING_MODE_3("6-4-3", "运行模式3", "运行模式3", false, 0, CHARGING_STATE_IS_NOT_1_AND_VEHICLE_STATE_IS_NOT_2),
    CRANKSHAFT_SPEED_VALIDATE_3("6-4-3-1", "运行模式3：曲轴转速>0", "曲轴转速异常、无效或越界", false, 0, RUNNING_MODE_3),

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
