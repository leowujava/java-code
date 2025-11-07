package com.chery.gb.realtime.algorithm.enums;


/**
 * 工作流枚举
 *
 * @author S00003829
 */
public enum WorkFlowEnums {

    ALL_DATA("1", "数据完整性", "检测整车、动力蓄电池、车辆位置、驱动电机、发动机、报警数据信息体是否上传", null),
    PRE_STATE("2", "前置状态", "前置状态", null),
    VEHICLE_STATE("2-1", "车辆状态", "车辆状态检测", PRE_STATE),
    BASIC_ITEM("3", "基础项", "基础项", null),
    CHARGING_STATE("4", "充电状态", "充电状态", null),
    DATA_LOGIC("5", "数据关联逻辑", "数据关联逻辑检测", null),
    RUNNING_MODE("6", "运行模式", "运行模式检测", null),

    ;

    String code;
    String name;
    String desc;

    WorkFlowEnums(String code, String name, String desc, WorkFlowEnums parent) {
        this.code = code;
        this.name = name;
        this.desc = desc;
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
}
