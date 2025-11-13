package com.chery.gb.realtime.algorithm.workflow;


import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.util.YmlReaderUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流枚举
 *
 * @author S00003829
 */
public enum GbWorkFlowEnum {


    WF_1("1", "数据完整性", "检测整车、动力蓄电池、车辆位置、驱动电机、发动机、报警数据信息体是否上传", true, 0, null),

    WF_2("2", "前置状态", "前置状态", false, 0, null),
    WF_2_1("2_1", "车辆状态为null检测", "车辆状态为null检测", true, 0, WF_2),
    WF_2_2("2_2", "车辆状态为异常或无效检测", "车辆状态为异常或无效检测", true, 0, WF_2),
    WF_2_3("2_3", "车辆状态未定义检测", "车辆状态未定义检测", true, 0, WF_2),
    WF_2_4("2_4", "车辆状态非1启动且非2熄火检测", "车辆状态非1启动且非2熄火检测", true, 0, WF_2),
    WF_2_5("2_5", "车辆状态为2熄火车速不为0", "车辆状态为2熄火车速不为0", false, 0, WF_2),
    WF_2_6("2_6", "充电状态为空", "充电状态为空", true, 0, WF_2),
    WF_2_7("2_7", "充电状态为异常或无效", "充电状态为异常或无效", false, 1, WF_2),
    WF_2_8("2_8", "充电状态范围检测", "充电状态范无定义", false, 0, WF_2),
    WF_2_9("2_9", "车辆状态为2且充电状态为3", "熄火未充电", true, 0, WF_2),

    WF_3("3", "基础项", "基础项", false, 0, null),
    WF_3_1("3_1", "燃料电池汽车判断", "判断是否为燃料电池汽车", false, 0, WF_3),
    WF_3_1_1("3_1_1", "燃料电池汽车检测", "燃料电池数据检测", false, 0, WF_3_1),
    WF_3_2("3_2", "超级电容器汽车判断", "判断是否为超级电容器汽车", false, 0, WF_3),
    WF_3_2_1("3_2_1", "超级电容器汽车检测", "超级电容数据检测", false, 0, WF_3_2),
    WF_3_3("3_3", "最高报警等级=4", "最高报警等级=4", false, 0, WF_3),
    WF_3_3_1("3_3_1", "最高报警等级=4数据检测", "最高报警等级=4数据检测", false, 0, WF_3_3),
    WF_3_4("3_4", "最高报警等级数据关联逻辑检测", "最高报警等级数据关联逻辑检测", false, 0, WF_3),
    WF_3_5("3_5", "动力蓄电池数据关联逻辑检测", "动力蓄电池数据关联逻辑检测", false, 0, WF_3),
    WF_3_6("3_6", "最小并联单元数据关联逻辑检测", "最小并联单元数据关联逻辑检测", false, 0, WF_3),
    WF_3_7("3_7", "总电压数据关联逻辑检测", "总电压数据关联逻辑检测", false, 0, WF_3),
    WF_3_8("3_8", "故障数据关联逻辑检测", "故障数据关联逻辑检测", false, 0, WF_3),
    WF_3_8_1("3_8_1", "故障数据检测", "故障数据关联逻辑检测", false, 2, WF_3_8),
//    WF_3_8_2("3_8_2", "故障数据为异常或无效检测", "故障数据为异常或无效检测", false, 1, WF_3_8),
//    WF_3_8_3("3_8_3", "故障数据范围检测", "故障数据范围检测", false, 0, WF_3_8),

    WF_4("4", "充电状态不是1和4", "充电状态不是1和4", false, 0, null),
    WF_4_1("4_1", "车速校验空值", "车速校验是否为空", false, 0, WF_4),
    WF_4_1_1("4_1_1", "车速校验无效、异常", "车速校验是否无效、异常", false, 0, WF_4_1),
    WF_4_1_1_1("4_1_1_1", "车速校验越界", "车速校验是否越界", false, 0, WF_4_1_1),
    WF_4_2("4_2", "里程校验空值", "里程校验是否为空", false, 0, WF_4),
    WF_4_2_1("4_2_1", "里程校验无效、异常、越界", "里程校验是否无效、异常、越界", false, 0, WF_4_2),

    WF_5("5", "数据关联逻辑", "数据关联逻辑检测：挡位、车速", false, 0, null),

    WF_6("6", "运行模式", "运行模式检测", false, 0, null),
    WF_6_1("6_1", "运行模式为空检测", "运行模式为空检测", true, 0, WF_6),
    WF_6_2("6_2", "运行模式异常或无效检测", "运行模式异常或无效检测", true, 0, WF_6),
    WF_6_3("6_3", "运行模式范围检测", "运行模式范围检测", true, 0, WF_6),
    WF_6_4("6_4", "充电模式不等于1且车辆状态不等于2", "充电模式不等于1且车辆状态不等于2", false, 0, WF_6),

    WF_6_4_1("6_4_1", "运行模式1", "运行模式1", false, 0, WF_6_4),
    WF_6_4_1_1("6_4_1_1", "运行模式1：曲轴转速>0", "纯电模式下曲轴转速>0", false, 0, WF_6_4_1),
    WF_6_4_1_2("6_4_1_2", "运行模式1：驱动电机列表数据检测", "驱动电机列表数据检测", false, 0, WF_6_4_1),
    WF_6_4_1_3("6_4_1_3", "运行模式1：总电流<0", "纯电行驶中能量回收显示停车充电", false, 0, WF_6_4_1),

    WF_6_4_2("6_4_2", "运行模式2", "运行模式2", false, 0, WF_6_4),
    WF_6_4_2_1("6_4_2_1", "运行模式2：驱动电机列表数据检测2", "驱动电机列表数据检测", false, 0, WF_6_4_2),
    WF_6_4_2_2("6_4_2_2", "运行模式2：曲轴转速>0", "曲轴转速异常、无效或越界", false, 0, WF_6_4_2),

    WF_6_4_3("6_4_3", "运行模式3", "运行模式3", false, 0, WF_6_4),
    WF_6_4_3_1("6_4_3_1", "运行模式3：曲轴转速>0", "曲轴转速异常、无效或越界", false, 0, WF_6_4_3),

    ;

    String code;
    String name;
    String desc;
    int skip;
    boolean isReturn;
    GbWorkFlowEnum parent;
    private static volatile Map<String, List<NewGbRuleCodeEnum>> workflowRuleCodeRelationMap = new HashMap<>();

    GbWorkFlowEnum(String code, String name, String desc, boolean isReturn, int skip, GbWorkFlowEnum parent) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.isReturn = isReturn;
        this.skip = skip;
        this.parent = parent;
    }

    public static List<GbWorkFlowEnum> getTopFlow() {
        List<GbWorkFlowEnum> gbWorkFlowEnums = new ArrayList<>();
        for (GbWorkFlowEnum value : GbWorkFlowEnum.values()) {
            if (value.parent == null) {
                gbWorkFlowEnums.add(value);
            }
        }
        return gbWorkFlowEnums;
    }

    public static List<GbWorkFlowEnum> getSubFlow(GbWorkFlowEnum parent) {
        List<GbWorkFlowEnum> gbWorkFlowEnums = new ArrayList<>();
        for (GbWorkFlowEnum value : GbWorkFlowEnum.values()) {
            if (value.parent == parent) {
                gbWorkFlowEnums.add(value);
            }
        }
        return gbWorkFlowEnums;
    }

    public static List<NewGbRuleCodeEnum> getRuleCodeListByCode(String code) {
        initWorkflowRuleCodeRelationMap();
        return workflowRuleCodeRelationMap.get(code);
    }

    private static void initWorkflowRuleCodeRelationMap() {
        if (workflowRuleCodeRelationMap != null && workflowRuleCodeRelationMap.size() > 0) {
            return;
        }
        Map<String, Object> config = YmlReaderUtil.readConfig("./config/workflow_rulecode_relation_config.yml");
        if (config == null) {
            return;
        }
        config.forEach((key, value) -> {
            if (value == null) {
                return;
            }
            String[] codes = String.valueOf(value).split(",");
            List<NewGbRuleCodeEnum> ruleCodeList = new ArrayList<>();
            for (String code : codes) {
                NewGbRuleCodeEnum gbRuleCodeEnum = NewGbRuleCodeEnum.getByCode(code);
                if (gbRuleCodeEnum != null) {
                    ruleCodeList.add(gbRuleCodeEnum);
                }
            }
            workflowRuleCodeRelationMap.put(key, ruleCodeList);
        });
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

    public GbWorkFlowEnum getParent() {
        return parent;
    }

    public int getSkip() {
        return skip;
    }

    public boolean isReturn() {
        return isReturn;
    }
}
