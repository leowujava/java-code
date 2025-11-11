package com.chery.gb.realtime.algorithm.enums;

import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.rule.factory.RuleConfigFactory;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新国标规则项
 *
 * @author S00003829
 */
public enum NewGbRuleCodeEnum {
    RULE_CODE_1("1", "车辆登入时间越界", "“年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5", "数据越界", "车辆登入"),
    RULE_CODE_2("2", "车辆登入报文延时>30s", "车辆登入报文数据采集时间与服务端平台服务器接收时间相差超过 30秒", "延时", "车辆登入"),
    RULE_CODE_3("3", "车辆登入流水号越界", "不在[1,65531]范围内", "数据越界", "车辆登入"),
    RULE_CODE_4("4", "ICCID 异常", "ICCID 非 20 位数字码", "异常值", "车辆登入"),
    RULE_CODE_5("5", "电池管理系统数越界", "不在[0,20]范围内", "数据越界", "车辆登入"),
    RULE_CODE_6("6", "电池管理系统对应动力蓄电池包个数越界", "不在[0,50]范围内", "数据越界", "车辆登入"),
    RULE_CODE_7("7", "动力蓄电池包编码异常", "动力蓄电池包编码非 24 位", "异常值", "车辆登入"),
    RULE_CODE_8("8", "动力蓄电池包总编码个数不匹配", "动力蓄电池包总编码个数≠各电池管理系统对应动力蓄电池包个数之和", "字段间逻辑异常", "车辆登入"),
    RULE_CODE_9("9", "车辆数据采集时间越界", "“年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5", "数据越界", "传输规则数据"),
    RULE_CODE_10("10", "车辆数据报文延时>30s", "车辆数据报文数据采集时间与服务端平台服务器接收时间相差超过 30秒", "延时", "传输规则数据"),
    RULE_CODE_11("11", "车辆登出时间越界", "“年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5", "数据越界", "车辆登出"),
    RULE_CODE_12("12", "车辆登出报文延时>30s", "车辆登出报文数据采集时间与服务端平台服务器接收时间相差超过 30秒", "延时", "车辆登出"),
    RULE_CODE_13("13", "车辆登出流水号越界", "不在[1,65531]范围内", "数据越界", "车辆登出"),
    RULE_CODE_14("14", "当次车辆登入流水号与登出流水号不一致", "当次车辆登入流水号≠登出流水号", "字段间逻辑异常", "车辆登出"),
    RULE_CODE_15("15", "平台登入时间越界", "“年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5", "数据越界", "平台登入"),
    RULE_CODE_16("16", "平台登入报文延时>30s", "平台登入报文时间与服务端平台服务器接收时间相差超过 30 秒", "延时", "平台登入"),
    RULE_CODE_17("17", "平台登入流水号越界", "不在[1,65531]范围内", "数据越界", "平台登入"),
    RULE_CODE_18("18", "平台登出时间越界", "“年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5", "数据越界", "平台登出"),
    RULE_CODE_19("19", "平台登出报文延时>30s", "平台登出报文时间与服务端平台服务器接收时间相差超过 30 秒", "延时", "平台登出"),
    RULE_CODE_20("20", "平台登出流水号越界", "不在[1,65531]范围内", "数据越界", "平台登出"),
    RULE_CODE_21("21", "当次平台登入流水号与登出流水号不一致", "当次平台登入流水号≠登出流水号", "字段间逻辑异常", "平台登出"),
    RULE_CODE_22("22", "车辆状态异常", "0xFE", "异常值", "整车数据"),
    RULE_CODE_23("23", "车辆状态无效", "0xFF", "无效值", "整车数据"),
    RULE_CODE_24("24", "车辆状态无定义", "不在[0x01、0x02、0x03]范围内", "数据越界", "整车数据"),
    RULE_CODE_25("25", "充电状态异常", "0xFE", "异常值", "整车数据"),
    RULE_CODE_26("26", "充电状态无效", "0xFF", "无效值", "整车数据"),
    RULE_CODE_27("27", "充电状态无定义", "不在[0x01、0x02、0x03、0x04]范围内", "数据越界", "整车数据"),
    RULE_CODE_28("28", "运行模式异常", "0xFE", "异常值", "整车数据"),
    RULE_CODE_29("29", "运行模式无效", "0xFF", "无效值", "整车数据"),
    RULE_CODE_30("30", "运行模式无定义", "不在[0x01、0x02、0x03]范围内", "数据越界", "整车数据"),
    RULE_CODE_31("31", "车速异常", "0xFF,0xFE", "异常值", "整车数据"),
    RULE_CODE_32("32", "车速无效", "0xFF,0xFF", "无效值", "整车数据"),
    RULE_CODE_33("33", "车速越界", "不在[0,3000]范围内", "数据越界", "整车数据"),
    RULE_CODE_34("34", "累计里程异常", "0xFF,0xFF,0xFF,0xFE", "异常值", "整车数据"),
    RULE_CODE_35("35", "累计里程无效", "0xFF,0xFF,0xFF,0xFF", "无效值", "整车数据"),
    RULE_CODE_36("36", "累计里程越界", "不在[0,9999999]范围内", "数据越界", "整车数据"),
    RULE_CODE_37("37", "总电压异常", "0xFF,0xFE", "异常值", "整车数据"),
    RULE_CODE_38("38", "总电压无效", "0xFF,0xFF", "无效值", "整车数据"),
    RULE_CODE_39("39", "总电压越界", "不在[0,60000]范围内", "数据越界", "整车数据"),
    RULE_CODE_40("40", "总电流异常", "0xFF,0xFE", "异常值", "整车数据"),
    RULE_CODE_41("41", "总电流无效", "0xFF,0xFF", "无效值", "整车数据"),
    RULE_CODE_42("42", "总电流越界", "不在[0,60000]范围内", "数据越界", "整车数据"),
    RULE_CODE_43("43", "SOC 异常", "0xFE", "异常值", "整车数据"),
    RULE_CODE_44("44", "SOC 无效", "0xFF", "无效值", "整车数据"),
    RULE_CODE_45("45", "SOC 越界", "不在[0,100]范围内", "数据越界", "整车数据"),
    RULE_CODE_46("46", "DC-DC 状态异常", "0xFE", "异常值", "整车数据"),
    RULE_CODE_47("47", "DC-DC 状态无效", "0xFF", "无效值", "整车数据"),
    RULE_CODE_48("48", "DC-DC 状态无定义", "不在[0x01、0x02]范围内", "数据越界", "整车数据"),
    RULE_CODE_49("49", "档位状态位未定义", "不符合 GB/T32960.3-2024 附录 A.1", "数据越界", "整车数据"),
    RULE_CODE_50("50", "高压对地绝缘电阻越界", "不在[0,60000]范围内", "数据越界", "整车数据"),
    RULE_CODE_51("51", "动力蓄电池包个数异常", "0xFE", "异常值", "动力蓄电池数据"),
    RULE_CODE_52("52", "动力蓄电池包个数无效", "0xFF", "无效值", "动力蓄电池数据"),
    RULE_CODE_53("53", "动力蓄电池包个数越界", "不在[0,50]范围内", "数据越界", "动力蓄电池数据"),
    RULE_CODE_54("54", "动力蓄电池包数量不匹配", "动力蓄电池包个数≠动力蓄电池最小并联单元电压信息列表中动力蓄电池信息个数", "字段间逻辑异常", "动力蓄电池数据"),
    RULE_CODE_55("55", "动力蓄电池包号异常", "0xFE", "异常值", "动力蓄电池数据"),
    RULE_CODE_56("56", "动力蓄电池包号无效", "0xFF", "无效值", "动力蓄电池数据"),
    RULE_CODE_57("57", "动力蓄电池包号越界", "不在[1,50]范围内", "数据越界", "动力蓄电池数据"),
    RULE_CODE_58("58", "动力蓄电池包电压异常", "0xFF,0xFE", "异常值", "动力蓄电池数据"),
    RULE_CODE_59("59", "动力蓄电池包电压无效", "0xFF,0xFF", "无效值", "动力蓄电池数据"),
    RULE_CODE_60("60", "动力蓄电池包电压越界", "不在[0,60000]范围内", "数据越界", "动力蓄电池数据"),
    RULE_CODE_61("61", "动力蓄电池包电流异常", "0xFF,0xFE", "异常值", "动力蓄电池数据"),
    RULE_CODE_62("62", "动力蓄电池包电流无效", "0xFF,0xFF", "无效值", "动力蓄电池数据"),
    RULE_CODE_63("63", "动力蓄电池包电流越界", "不在[0,60000]范围内", "数据越界", "动力蓄电池数据"),
    RULE_CODE_64("64", "最小并联单元总数异常", "0xFF,0xFE", "异常值", "动力蓄电池数据"),
    RULE_CODE_65("65", "最小并联单元总数无效", "0xFF,0xFF", "无效值", "动力蓄电池数据"),
    RULE_CODE_66("66", "最小并联单元总数越界", "不在[1,65531]范围内", "数据越界", "动力蓄电池数据"),
    RULE_CODE_67("67", "本帧最小并联单元电压异常", "0xFF,0xFE", "异常值", "动力蓄电池数据"),
    RULE_CODE_68("68", "本帧最小并联单元电压无效", "0xFF,0xFF", "无效值", "动力蓄电池数据"),
    RULE_CODE_69("69", "本帧最小并联单元电压越界", "不在[0,60000]范围内", "数据越界", "动力蓄电池数据"),
    RULE_CODE_70("70", "本帧最小并联单元数量不匹配", "最小并联单元总数≠最小并联单元电压个数", "字段间逻辑异常", "动力蓄电池数据"),
    RULE_CODE_71("71", "动力蓄电池包温度探针个数异常", "0xFF,0xFE", "异常值", "动力蓄电池数据"),
    RULE_CODE_72("72", "动力蓄电池包温度探针个数无效", "0xFF,0xFF", "无效值", "动力蓄电池数据"),
    RULE_CODE_73("73", "动力蓄电池包温度探针个数越界", "不在[1,65531]范围内", "数据越界", "动力蓄电池数据"),
    RULE_CODE_74("74", "温度探针检测到的温度值异常", "0xFE", "异常值", "动力蓄电池数据"),
    RULE_CODE_75("75", "温度探针检测到的温度值无效", "0xFF", "无效值", "动力蓄电池数据"),
    RULE_CODE_76("76", "温度探针检测到的温度值越界", "不在[0,250]范围内", "数据越界", "动力蓄电池数据"),
    RULE_CODE_77("77", "驱动电机个数越界", "不在[1,253]范围内", "数据越界", "驱动电机数据"),
    RULE_CODE_78("78", "驱动电机数量不匹配", "驱动电机个数≠驱动电机总成信息列表中电机信息个数", "字段间逻辑异常", "驱动电机数据"),
    RULE_CODE_79("79", "驱动电机序号越界", "不在[1,253]范围内", "数据越界", "驱动电机数据"),
    RULE_CODE_80("80", "驱动电机状态异常", "0xFE", "异常值", "驱动电机数据"),
    RULE_CODE_81("81", "驱动电机状态无效", "0xFF", "无效值", "驱动电机数据"),
    RULE_CODE_82("82", "驱动电机状态无定义", "不在[0x01、0x02、0x03、0x04]范围内", "数据越界", "驱动电机数据"),
    RULE_CODE_83("83", "驱动电机控制器温度异常", "0xFE", "异常值", "驱动电机数据"),
    RULE_CODE_84("84", "驱动电机控制器温度无效", "0xFF", "无效值", "驱动电机数据"),
    RULE_CODE_85("85", "驱动电机控制器温度越界", "不在[0,250]范围内", "数据越界", "驱动电机数据"),
    RULE_CODE_86("86", "驱动电机转速异常", "0xFF,0xFE", "异常值", "驱动电机数据"),
    RULE_CODE_87("87", "驱动电机转速无效", "0xFF,0xFF", "无效值", "驱动电机数据"),
    RULE_CODE_88("88", "驱动电机转速越界", "不在[0,65531]范围内", "数据越界", "驱动电机数据"),
    RULE_CODE_89("89", "驱动电机转矩异常", "0xFF,0xFE", "异常值", "驱动电机数据"),
    RULE_CODE_90("90", "驱动电机转矩无效", "0xFF,0xFF", "无效值", "驱动电机数据"),
    RULE_CODE_91("91", "驱动电机转矩越界", "不在[0,400000]范围内", "数据越界", "驱动电机数据"),
    RULE_CODE_92("92", "驱动电机温度异常", "0xFE", "异常值", "驱动电机数据"),
    RULE_CODE_93("93", "驱动电机温度无效", "0xFF", "无效值", "驱动电机数据"),
    RULE_CODE_94("94", "驱动电机温度越界", "不在[0,250]范围内", "数据越界", "驱动电机数据"),
    RULE_CODE_95("95", "车载氢系统中最高温度异常", "0xFF,0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_96("96", "车载氢系统中最高温度无效", "0xFF,0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_97("97", "车载氢系统中最高温度越界", "不在[0,2500]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_98("98", "车载氢系统中最高温度探针代号异常", "0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_99("99", "车载氢系统中最高温度探针代号无效", "0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_100("100", "车载氢系统中最高温度探针代号越界", "不在[1,252]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_101("101", "氢气最高浓度异常", "0xFF,0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_102("102", "氢气最高浓度无效", "0xFF,0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_103("103", "氢气最高浓度越界", "不在[0,60000]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_104("104", "氢气最高浓度传感器代号异常", "0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_105("105", "氢气最高浓度传感器代号无效", "0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_106("106", "氢气最高浓度传感器代号越界", "不在[1,252]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_107("107", "氢气最高压力异常", "0xFF,0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_108("108", "氢气最高压力无效", "0xFF,0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_109("109", "氢气最高压力越界", "不在[0,1000]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_110("110", "氢气最高压力传感器代号异常", "0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_111("111", "氢气最高压力传感器代号无效", "0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_112("112", "氢气最高压力传感器代号越界", "不在[1,252]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_113("113", "高压 DC/DC 状态异常", "0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_114("114", "高压 DC/DC 状态无效", "0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_115("115", "高压 DC/DC 状态无定义", "不在[0x01、0x02]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_116("116", "剩余氢量百分比异常", "0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_117("117", "剩余氢量百分比无效", "0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_118("118", "剩余氢量百分比越界", "不在[0,100]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_119("119", "高压 DC/DC 控制器温度异常", "0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_120("120", "高压 DC/DC 控制器温度无效", "0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_121("121", "高压 DC/DC 控制器温度越界", "不在[0,250]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_122("122", "燃料电池电堆个数异常", "0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_123("123", "燃料电池电堆个数无效", "0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_124("124", "燃料电池电堆个数越界", "不在[1,250]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_125("125", "燃料电池电堆数量不匹配", "燃料电池电堆个数≠燃料电池电堆信息表中燃料电池电堆信息个数", "字段间逻辑异常", "燃料电池数据"),
    RULE_CODE_126("126", "燃料电池电堆序号异常", "0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_127("127", "燃料电池电堆序号无效", "0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_128("128", "燃料电池电堆序号越界", "不在[1,250]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_129("129", "燃料电池电堆电压异常", "0xFF,0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_130("130", "燃料电池电堆电压无效", "0xFF,0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_131("131", "燃料电池电堆电压越界", "不在[0,20000]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_132("132", "燃料电池电堆电流异常", "0xFF,0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_133("133", "燃料电池电堆电流无效", "0xFF,0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_134("134", "燃料电池电堆电流越界", "不在[0,20000]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_135("135", "氢气入口压力异常", "0xFF,0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_136("136", "氢气入口压力无效", "0xFF,0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_137("137", "氢气入口压力越界", "不在[0,5000]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_138("138", "空气入口压力异常", "0xFF,0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_139("139", "空气入口压力无效", "0xFF,0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_140("140", "空气入口压力越界", "不在[0,5000]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_141("141", "空气入口温度异常", "0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_142("142", "空气入口温度无效", "0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_143("143", "空气入口温度越界", "不在[0,250]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_144("144", "冷却水出水口温度探针总数异常", "0xFF,0xFE", "异常值", "燃料电池数据"),
    RULE_CODE_145("145", "冷却水出水口温度探针总数无效", "0xFF,0xFF", "无效值", "燃料电池数据"),
    RULE_CODE_146("146", "冷却水出水口温度探针总数越界", "不在[0,65531]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_147("147", "冷却水出水口温度越界", "不在[0,250]范围内", "数据越界", "燃料电池数据"),
    RULE_CODE_148("148", "曲轴转速异常", "0xFF,0xFE", "异常值", "发动机数据"),
    RULE_CODE_149("149", "曲轴转速无效", "0xFF,0xFF", "无效值", "发动机数据"),
    RULE_CODE_150("150", "曲轴转速越界", "不在[0,60000]范围内", "数据越界", "发动机数据"),
    RULE_CODE_151("151", "定位状态未定义", "不符合 GB/T32960.3-2024 表 22", "数据越界", "车辆位置数据"),
    RULE_CODE_152("152", "经度精确度不足", "精确度不足百万分之一度", "数据精度不足", "车辆位置数据"),
    RULE_CODE_153("153", "纬度精确度不足", "精确度不足百万分之一度", "数据精度不足", "车辆位置数据"),
    RULE_CODE_154("154", "经度越界", "不在[0,180000000]范围内", "数据越界", "车辆位置数据"),
    RULE_CODE_155("155", "纬度越界", "不在[0,90000000]范围内", "数据越界", "车辆位置数据"),
    RULE_CODE_156("156", "三/四级报警报文采集频率异常", "触发三/四级报警的车辆数据报文,采集频率不足 1s 一帧", "传输规则", "报警数据"),
    RULE_CODE_157("157", "三/四级报警报文上报帧数不足", "触发三/四级报警的车辆数据报文,补发报警前数据帧数不足 30 帧", "传输规则", "报警数据"),
    RULE_CODE_158("158", "下高压后三/四级报警报文上报帧数不足", "车辆下高压后触发三/四级报警的车辆数据报文,上报帧数不足 30 帧", "传输规则", "报警数据"),
    RULE_CODE_159("159", "最高报警等级异常", "0xFE", "异常值", "报警数据"),
    RULE_CODE_160("160", "最高报警等级无效", "0xFF", "无效值", "报警数据"),
    RULE_CODE_161("161", "最高报警等级无定义", "不在[0、1、2、3、4]范围内", "数据越界", "报警数据"),
    RULE_CODE_162("162", "最高报警等级不匹配", "最高报警等级≠通用报警故障等级列表中的最高通用报警故障等级", "字段间逻辑异常", "报警数据"),
    RULE_CODE_163("163", "通用报警标志不匹配", "最高报警等级为 1/2/3/4 时,通用报警标志字段中无报警位(指 4 个字节的 32 位均为 0)", "字段间逻辑异常", "报警数据"),
    RULE_CODE_164("164", "可充电储能装置故障总数 N1异常", "0xFE", "异常值", "报警数据"),
    RULE_CODE_165("165", "可充电储能装置故障总数 N1无效", "0xFF", "无效值", "报警数据"),
    RULE_CODE_166("166", "可充电储能装置故障总数 N1越界", "不在[0,252]范围内", "数据越界", "报警数据"),
    RULE_CODE_167("167", "可充电储能装置故障总数 N1不匹配", "可充电储能装置故障总数 N1≠可充电储能装置故障代码列表中可充电储能装置故障信息个数", "字段间逻辑异常", "报警数据"),
    RULE_CODE_168("168", "驱动电机故障总数 N2 异常", "0xFE", "异常值", "报警数据"),
    RULE_CODE_169("169", "驱动电机故障总数 N2 无效", "0xFF", "无效值", "报警数据"),
    RULE_CODE_170("170", "驱动电机故障总数 N2 越界", "不在[0,252]范围内", "数据越界", "报警数据"),
    RULE_CODE_171("171", "驱动电机故障总数 N2 不匹配", "驱动电机故障总数 N2≠驱动电机故障代码列表中驱动电机故障信息个数", "字段间逻辑异常", "报警数据"),
    RULE_CODE_172("172", "发动机故障总数 N3 异常", "0xFE", "异常值", "报警数据"),
    RULE_CODE_173("173", "发动机故障总数 N3 无效", "0xFF", "无效值", "报警数据"),
    RULE_CODE_174("174", "发动机故障总数 N3 越界", "不在[0,252]范围内", "数据越界", "报警数据"),
    RULE_CODE_175("175", "发动机故障总数 N3 不匹配", "发动机故障总数 N3≠发动机故障代码列表中发动机故障信息个数", "字段间逻辑异常", "报警数据"),
    RULE_CODE_176("176", "其他故障总数 N4 异常", "0xFE", "异常值", "报警数据"),
    RULE_CODE_177("177", "其他故障总数 N4 无效", "0xFF", "无效值", "报警数据"),
    RULE_CODE_178("178", "其他故障总数 N4 越界", "不在[0,252]范围内", "数据越界", "报警数据"),
    RULE_CODE_179("179", "其他故障总数 N4 不匹配", "其他故障总数 N4≠其他故障代码列表中其他故障信息个数", "字段间逻辑异常", "报警数据"),
    RULE_CODE_180("180", "通用报警故障总数异常", "0xFE", "异常值", "报警数据"),
    RULE_CODE_181("181", "通用报警故障总数无效", "0xFF", "无效值", "报警数据"),
    RULE_CODE_182("182", "通用报警故障总数越界", "不在[0,252]范围内", "数据越界", "报警数据"),
    RULE_CODE_183("183", "通用报警故障总数不匹配", "通用报警故障总数≠通用报警故障等级列表中通用报警故障等级个数", "字段间逻辑异常", "报警数据"),
    RULE_CODE_184("184", "超级电容管理系统号异常", "0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_185("185", "超级电容管理系统号无效", "0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_186("186", "超级电容管理系统号越界", "不在[1,250]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_187("187", "超级电容总电压异常", "0xFF,0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_188("188", "超级电容总电压无效", "0xFF,0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_189("189", "超级电容总电压越界", "不在[0,10000]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_190("190", "超级电容总电流异常", "0xFF,0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_191("191", "超级电容总电流无效", "0xFF,0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_192("192", "超级电容总电流越界", "不在[0,60000]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_193("193", "超级电容单体总数异常", "0xFF,0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_194("194", "超级电容单体总数无效", "0xFF,0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_195("195", "超级电容单体总数越界", "不在[1,65531]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_196("196", "超级电容单体总数不匹配", "超级电容单体总数≠超级电容单体电压个数", "字段间逻辑异常", "超级电容器数据"),
    RULE_CODE_197("197", "超级电容单体电压异常", "0xFF,0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_198("198", "超级电容单体电压无效", "0xFF,0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_199("199", "超级电容单体电压越界", "不在[0,60000]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_200("200", "超级电容温度探针总数异常", "0xFF,0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_201("201", "超级电容温度探针总数无效", "0xFF,0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_202("202", "超级电容温度探针总数越界", "不在[1,65531]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_203("203", "超级电容温度探针总数不匹配", "超级电容温度探针总数≠探针温度值个数", "字段间逻辑异常", "超级电容器数据"),
    RULE_CODE_204("204", "探针温度值异常", "0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_205("205", "探针温度值无效", "0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_206("206", "探针温度值越界", "不在[0,250]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_207("207", "最高电压管理系统号异常", "0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_208("208", "最高电压管理系统号无效", "0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_209("209", "最高电压管理系统号越界", "不在[1,250]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_210("210", "最高电压超级电容单体代号越界", "不在[1,65531]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_211("211", "超级电容单体电压最高值异常", "0xFF,0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_212("212", "超级电容单体电压最高值无效", "0xFF,0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_213("213", "超级电容单体电压最高值越界", "不在[0,60000]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_214("214", "超级电容单体电压最高值不匹配", "超级电容单体电压最高值≠超级电容单体电压列表中电压最高值", "字段间逻辑异常", "超级电容器数据"),
    RULE_CODE_215("215", "最低电压管理系统号异常", "0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_216("216", "最低电压管理系统号无效", "0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_217("217", "最低电压管理系统号越界", "不在[1,250]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_218("218", "最低电压超级电容单体代号越界", "不在[1,65531]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_219("219", "超级电容单体电压最低值异常", "0xFF,0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_220("220", "超级电容单体电压最低值无效", "0xFF,0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_221("221", "超级电容单体电压最低值越界", "不在[0,60000]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_222("222", "超级电容单体电压最低值不匹配", "超级电容单体电压最低值≠超级电容单体电压列表中电压最低值", "字段间逻辑异常", "超级电容器数据"),
    RULE_CODE_223("223", "最高温度管理系统号异常", "0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_224("224", "最高温度管理系统号无效", "0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_225("225", "最高温度管理系统号越界", "不在[1,250]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_226("226", "最高温度探针代号越界", "不在[1,65531]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_227("227", "最高温度值异常", "0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_228("228", "最高温度值无效", "0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_229("229", "最高温度值越界", "不在[0,250]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_230("230", "最高温度值不匹配", "最高温度值≠探针温度值列表中温度最高值", "字段间逻辑异常", "超级电容器数据"),
    RULE_CODE_231("231", "最低温度管理系统号异常", "0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_232("232", "最低温度管理系统号无效", "0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_233("233", "最低温度管理系统号越界", "不在[1,250]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_234("234", "最低温度探针代号越界", "不在[1,65531]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_235("235", "最低温度值异常", "0xFE", "异常值", "超级电容器数据"),
    RULE_CODE_236("236", "最低温度值无效", "0xFF", "无效值", "超级电容器数据"),
    RULE_CODE_237("237", "最低温度值越界", "不在[0,250]范围内", "数据越界", "超级电容器数据"),
    RULE_CODE_238("238", "最低温度值不匹配", "最低温度值≠探针温度值列表中温度最低值", "字段间逻辑异常", "超级电容器数据"),
    RULE_CODE_239("239", "未上传整车数据", "未上传整车数据", "缺失", "整车数据"),
    RULE_CODE_240("240", "未上传动力蓄电池最小并联单元电压数据", "未上传动力蓄电池最小并联单元电压数据", "缺失", "动力蓄电池数据"),
    RULE_CODE_241("241", "未上传动力蓄电池温度数据", "未上传动力蓄电池温度数据", "缺失", "动力蓄电池数据"),
    RULE_CODE_242("242", "未上传驱动电机数据", "未上传驱动电机数据", "缺失", "驱动电机数据"),
    RULE_CODE_243("243", "未上传燃料电池发动机及车载氢系统数据", "燃料电池汽车未上传燃料电池发动机及车载氢系统数据", "缺失", "燃料电池数据"),
    RULE_CODE_244("244", "未上传燃料电池电堆数据", "燃料电池汽车未上传燃料电池电堆数据", "缺失", "燃料电池数据"),
    RULE_CODE_245("245", "未上传发动机数据", "未上传发动机数据", "缺失", "发动机数据"),
    RULE_CODE_246("246", "未上传车辆位置数据", "未上传车辆位置数据", "缺失", "车辆位置数据"),
    RULE_CODE_247("247", "未上传超级电容器数据", "超级电容汽车未上传超级电容器数据", "缺失", "超级电容器数据"),
    RULE_CODE_248("248", "未上传超级电容器极值数据", "超级电容汽车未上传超级电容器极值数据", "缺失", "超级电容器数据"),
    RULE_CODE_250("250", "车辆状态为熄火车速不为 0", "车辆熄火, 但车速>5km/h", "字段间逻辑异常", "整车数据"),
    RULE_CODE_251("251", "坐标系异常", "0xFE", "异常值", "车辆位置数据"),
    RULE_CODE_252("252", "坐标系无效", "0xFF", "无效值", "车辆位置数据"),
    RULE_CODE_253("253", "坐标系无定义", "不在[0x01、0x02、0x03]范围内", "数据越界", "车辆位置数据"),
    RULE_CODE_254("254", "档位异常", "车速大于 0 档位为 P 档", "字段间逻辑异常", "整车数据"),
    RULE_CODE_255("255", "曲轴转速异常", "纯电模式下曲轴转速>0", "字段间逻辑异常", "发动机数据"),
    RULE_CODE_256("256", "总电压不匹配", "总电压≠最小并联单元电压之和(偏差超出 5V)", "字段间逻辑异常", "动力蓄电池数据"),
    RULE_CODE_257("257", "车辆行驶中能量回收显示停车充电", "纯电动车速>0 电流为负数 充电状态 1 同时出现", "字段间逻辑异常", "整车数据"),
    RULE_CODE_258("258", "未上传报警数据", "未上传报警数据", "缺失", "报警数据"),
    RULE_CODE_259("259", "车辆状态非 1 启动且非 2 熄火", "车辆状态既不是启动也不是熄火", "无效值", "整车数据"),
    RULE_CODE_260("260", "高压下电监测报文缺失", "车辆下高压未上报 59min30s 至 1h 监测报文", "传输规则", "整车数据"),
    RULE_CODE_261("261", "三/四级报警报文未持续至报警标志位消失", "触发 3|4 级报警后, 报文未持续到报警标志位消失", "传输规则", "报警数据"),
    RULE_CODE_262("262", "熄火切换到启动状态里程值变化", "熄火最后一帧到启动第一帧里程变化超过 4km", "异常值", "整车数据"),
    RULE_CODE_263("263", "车辆登入报文延时>180s", "车辆登入报文数据采集时间与服务端平台服务器接收时间相差超过 180秒", "延时", "车辆登入"),
    RULE_CODE_264("264", "车辆数据报文延时>180s", "车辆数据报文数据采集时间与服务端平台服务器接收时间相差超过 180秒", "延时", "传输规则数据"),
    RULE_CODE_265("265", "车辆登出报文延时>180s", "车辆登出报文数据采集时间与服务端平台服务器接收时间相差超过 180秒", "延时", "车辆登出"),
    RULE_CODE_266("266", "平台登入报文延时>180s", "平台登入报文时间与服务端平台服务器接收时间相差超过 180 秒", "延时", "平台登入"),
    RULE_CODE_267("267", "平台登出报文延时>180s", "平台登出报文时间与服务端平台服务器接收时间相差超过 180 秒", "延时", "平台登出"),
    RULE_CODE_268("268", "最小并联单元电压精确度不足", "电压精度不满足 0.001V", "数据精度不足", "动力蓄电池数据"),
    RULE_CODE_269("269", "四级报警与通用报警故障等级列表不匹配", "4级报警后在通用报警故障等级列表中未有可充电储能装置热事件报警", "字段间逻辑异常", "报警数据"),
    RULE_CODE_270("270", "30 秒内里程跳变大于 4 公里", "相差 30 秒的 2 帧报文里程跳变大于 4 公里", "字段间逻辑异常", "整车数据"),
    RULE_CODE_271("271", "多条相同时间报文不一致", "多条相同时间报文整车数据中任意一项不一致", "异常值", "整车数据"),
    RULE_CODE_273("273", "车辆再次登录流水号不大于上一次登录流水号", "再次登录流水号<=上一次登录流水号", "异常值", "车辆登入"),
    RULE_CODE_274("274", "平台再次登录流水号不大于上一次登录流水号", "再次登录流水号<=上一次登录流水号", "异常值", "平台登入"),
    RULE_CODE_275("275", "最小并联单元前后报文数目不一致", "报文前后 2帧上传的最小并联单元数目不一致", "异常值", "整车数据"),

    ;

    String code;

    String name;

    String desc;

    String ruleSubType;

    String group;

    static Map<String, SignalEnum> ruleSignalRelationMap = new HashMap<>();

    NewGbRuleCodeEnum(String code, String name, String desc, String ruleSubType, String group) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.ruleSubType = ruleSubType;
        this.group = group;
    }

    public static NewGbRuleCodeEnum getByCode(String ruleCode) {
        for (NewGbRuleCodeEnum value : NewGbRuleCodeEnum.values()) {
            if (value.code.equals(ruleCode)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getRuleSubType() {
        return ruleSubType;
    }

    public String getGroup() {
        return group;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isReturn() {
        return RuleConfigFactory.isReturn(code);
    }

    public List<RuleDetailBO> getCondition() {
        return RuleConfigFactory.getCondition(code);
    }

    public static List<NewGbRuleCodeEnum> getByRuleSubTypes(List<String> ruleSubTypes) {
        List<NewGbRuleCodeEnum> ruleCodeEnums = new ArrayList<>();
        if (CollectionUtils.isEmpty(ruleSubTypes)) {
            return ruleCodeEnums;
        }
        for (NewGbRuleCodeEnum value : NewGbRuleCodeEnum.values()) {
            if (ruleSubTypes.contains(value.ruleSubType)) {
                ruleCodeEnums.add(value);
            }
        }
        return ruleCodeEnums;
    }

    public static List<NewGbRuleCodeEnum> getByGroup(String group) {
        List<NewGbRuleCodeEnum> ruleSubTypes = new ArrayList<>();
        for (NewGbRuleCodeEnum value : NewGbRuleCodeEnum.values()) {
            if (value.group.equals(group)) {
                ruleSubTypes.add(value);
            }
        }
        return ruleSubTypes;
    }

    public static SignalEnum getSignalByRuleCode(String code) {
        initSignalMap();
        return ruleSignalRelationMap.get(code);
    }

    private static void initSignalMap() {
        if (ruleSignalRelationMap.size() > 0) {
            return;
        }
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_1.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_3.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_5.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_6.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_9.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_11.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_13.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_15.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_17.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_18.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_20.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_22.getCode(), SignalEnum.SIGNAL_220C);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_23.getCode(), SignalEnum.SIGNAL_220C);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_24.getCode(), SignalEnum.SIGNAL_220C);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_25.getCode(), SignalEnum.SIGNAL_21AA);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_26.getCode(), SignalEnum.SIGNAL_21AA);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_27.getCode(), SignalEnum.SIGNAL_21AA);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_28.getCode(), SignalEnum.SIGNAL_21AB);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_29.getCode(), SignalEnum.SIGNAL_21AB);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_30.getCode(), SignalEnum.SIGNAL_21AB);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_31.getCode(), SignalEnum.SIGNAL_2001);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_32.getCode(), SignalEnum.SIGNAL_2001);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_33.getCode(), SignalEnum.SIGNAL_2001);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_34.getCode(), SignalEnum.SIGNAL_2009);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_35.getCode(), SignalEnum.SIGNAL_2009);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_36.getCode(), SignalEnum.SIGNAL_2009);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_37.getCode(), SignalEnum.SIGNAL_215C);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_38.getCode(), SignalEnum.SIGNAL_215C);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_39.getCode(), SignalEnum.SIGNAL_215C);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_40.getCode(), SignalEnum.SIGNAL_215D);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_41.getCode(), SignalEnum.SIGNAL_215D);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_42.getCode(), SignalEnum.SIGNAL_215D);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_43.getCode(), SignalEnum.SIGNAL_2232);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_44.getCode(), SignalEnum.SIGNAL_2232);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_45.getCode(), SignalEnum.SIGNAL_2232);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_46.getCode(), SignalEnum.SIGNAL_2187);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_47.getCode(), SignalEnum.SIGNAL_2187);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_48.getCode(), SignalEnum.SIGNAL_2187);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_49.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_50.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_51.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_52.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_53.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_55.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_56.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_57.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_58.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_59.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_60.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_61.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_62.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_63.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_64.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_65.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_66.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_67.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_68.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_69.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_71.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_72.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_73.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_74.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_75.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_76.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_77.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_79.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_80.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_81.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_82.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_83.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_84.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_85.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_86.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_87.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_88.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_89.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_90.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_91.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_92.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_93.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_94.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_95.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_96.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_97.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_98.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_99.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_100.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_101.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_102.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_103.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_104.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_105.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_106.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_107.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_108.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_109.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_110.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_111.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_112.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_113.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_114.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_115.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_116.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_117.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_118.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_119.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_120.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_121.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_122.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_123.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_124.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_126.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_127.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_128.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_129.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_130.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_131.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_132.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_133.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_134.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_135.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_136.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_137.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_138.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_139.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_140.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_141.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_142.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_143.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_144.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_145.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_146.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_147.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_148.getCode(), SignalEnum.SIGNAL_219F);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_149.getCode(), SignalEnum.SIGNAL_219F);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_150.getCode(), SignalEnum.SIGNAL_219F);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_151.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_154.getCode(), SignalEnum.SIGNAL_207A);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_155.getCode(), SignalEnum.SIGNAL_207B);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_159.getCode(), SignalEnum.SIGNAL_220B);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_160.getCode(), SignalEnum.SIGNAL_220B);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_161.getCode(), SignalEnum.SIGNAL_220B);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_164.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_165.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_166.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_168.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_169.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_170.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_172.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_173.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_174.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_176.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_177.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_178.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_180.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_181.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_182.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_184.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_185.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_186.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_187.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_188.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_189.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_190.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_191.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_192.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_193.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_194.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_195.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_197.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_198.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_199.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_200.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_201.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_202.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_204.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_205.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_206.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_207.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_208.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_209.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_210.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_211.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_212.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_213.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_215.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_216.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_217.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_218.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_219.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_220.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_221.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_223.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_224.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_225.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_226.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_227.getCode(), SignalEnum.SIGNAL_2143);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_228.getCode(), SignalEnum.SIGNAL_2143);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_229.getCode(), SignalEnum.SIGNAL_2143);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_231.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_232.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_233.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_234.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_235.getCode(), SignalEnum.SIGNAL_2145);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_236.getCode(), SignalEnum.SIGNAL_2145);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_237.getCode(), SignalEnum.SIGNAL_2145);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_239.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_240.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_241.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_242.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_243.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_244.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_245.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_246.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_247.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_248.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_251.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_252.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_253.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_258.getCode(), null);
        ruleSignalRelationMap.put(NewGbRuleCodeEnum.RULE_CODE_259.getCode(), null);
    }

}
