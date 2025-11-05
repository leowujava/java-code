package com.chery.gb.realtime.algorithm.enums;

import com.chery.gb.realtime.algorithm.signal.converter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 信号枚举
 */
public enum SignalEnum {
    SIGNAL_2187("2187", "DC-DC状态", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_21AA("21AA", "充电状态", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_2232("2232", "soc", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_2076("2076", "档位", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_2001("2001", "车速", SignalGroupEnum.HOLE_VEHICLE, new SignalConverter2001(), null),
    SIGNAL_2160("2160", "绝缘电阻", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_21B2("21B2", "制动踏板状态", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_215D("215D", "总电流", SignalGroupEnum.HOLE_VEHICLE, new SignalConverter215D(), null),
    SIGNAL_21B1("21B1", "加速踏板行程值", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_215C("215C", "总电压", SignalGroupEnum.HOLE_VEHICLE, new SignalConverter215C(), null),
    SIGNAL_219E("219E", "驱动力", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_219D("219D", "制动力", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_220C("220C", "车辆状态", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_2009("2009", "公里里程（总里程）", SignalGroupEnum.HOLE_VEHICLE, null, null),
    SIGNAL_2278("2278", "定位状态经度", SignalGroupEnum.VEHICLE_POS, null, null),
    SIGNAL_2277("2277", "定位状态纬度", SignalGroupEnum.VEHICLE_POS, null, null),
    SIGNAL_207F("207F", "GPS状态,位置有效性 0有效定位；1无效定位", SignalGroupEnum.VEHICLE_POS, null, null),
    SIGNAL_207B("207B", "纬度", SignalGroupEnum.VEHICLE_POS, null, null),
    SIGNAL_207A("207A", "经度", SignalGroupEnum.VEHICLE_POS, null, null),
    SIGNAL_2149("2149", "电池单体电压最低值", SignalGroupEnum.ENGINE, new SignalConverter2149(), null),
    SIGNAL_2148("2148", "最低电压电池单体代号", SignalGroupEnum.ENGINE, null, null),
    SIGNAL_2147("2147", "电池单体电压最高值", SignalGroupEnum.ENGINE, new SignalConverter2147(), null),
    SIGNAL_2146("2146", "最高电压电池单体代号", SignalGroupEnum.ENGINE, null, null),
    SIGNAL_2145("2145", "最低温度值", SignalGroupEnum.ENGINE, new SignalConverter2145(), null),
    SIGNAL_2144("2144", "最低温度探针子系统代号", SignalGroupEnum.ENGINE, null, null),
    SIGNAL_2143("2143", "最高温度值", SignalGroupEnum.ENGINE, new SignalConverter2143(), null),
    SIGNAL_2142("2142", "最高温度探针单体代号", SignalGroupEnum.ENGINE, null, null),
    SIGNAL_20FD("20FD", "最低电压电池子系统号", SignalGroupEnum.ENGINE, null, null),
    SIGNAL_20FC("20FC", "最高电压电池子系统号", SignalGroupEnum.ENGINE, null, null),
    SIGNAL_20FB("20FB", "最低温度子系统号", SignalGroupEnum.ENGINE, null, null),
    SIGNAL_20FA("20FA", "最高温度子系统号", SignalGroupEnum.ENGINE, null, null),
    SIGNAL_2188("2188", "DC-DC温度报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2156("2156", "高压互锁状态报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2157("2157", "车载储能装置类型过充", SignalGroupEnum.ALERT, null, null),
    SIGNAL_214F("214F", "单体电池过压报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_214E("214E", "SOC低报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2155("2155", "绝缘报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2154("2154", "电池单体一致性差报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2153("2153", "可充电储能系统不匹配报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2152("2152", "SOC跳变报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2151("2151", "SOC过高报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2150("2150", "单体电池欠压报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_214D("214D", "车载储能装置类型欠压报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_214C("214C", "车载储能装置类型过压报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_214B("214B", "电池高温报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_214A("214A", "温度差异报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_220B("220B", "最高报警等级", SignalGroupEnum.ALERT, null, null),
    SIGNAL_21C2("21C2", "制动系统报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2209("2209", "驱动电机温度报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2208("2208", "驱动电机控制器温度报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_2189("2189", "DC-DC状态报警", SignalGroupEnum.ALERT, null, null),
    SIGNAL_21CF("21CF", "驱动电机控制器温度", null, new SignalConverter21CF(), "1BC1"),
    SIGNAL_21CE("21CE", "驱动电机状态", null, null, "1BC1"),
    SIGNAL_21CD("21CD", "驱动电机序号", null, null, "1BC1"),
    SIGNAL_21D4("21D4", "电机控制器直流母线电流", null, new SignalConverter21D4(), "1BC1"),
    SIGNAL_21D3("21D3", "电机控制器输入电压", null, new SignalConverter21D3(), "1BC1"),
    SIGNAL_21D2("21D2", "驱动电机温度", null, new SignalConverter21D2(), "1BC1"),
    SIGNAL_21D1("21D1", "驱动电机转矩", null, new SignalConverter21D1(), "1BC1"),
    SIGNAL_21D0("21D0", "驱动电机转速", null, new SignalConverter21D0(), "1BC1"),
    SIGNAL_2100("2100", "可充电储能子系统号", null, null, "1BC2"),
    SIGNAL_2141("2141", "单体电池总数", null, null, "1BC2"),
    SIGNAL_22CA("22CA", "本帧起始电池序号", null, null, "1BC2"),
    SIGNAL_220E("220E", "单体电池电压", null, new SignalConverter220E(), "1BC2"),
    SIGNAL_24C9("24C9", "可充电储能温度探针个数", null, null, "1BC3"),
    SIGNAL_2101("2101", "可充电储能子系统号", null, null, "1BC3"),
    SIGNAL_220F("220F", "可充电储能子系统各温度探针检测到的温度值", null, new SignalConverter220F(), "1BC3"),
    SIGNAL_21AB("21AB", "运行模式", null, null, null),
    SIGNAL_219F("219F", "曲轴转速", null, null, null),
    SIGNAL_2159("2159", "可充电储能装置电流", null, null, null),
    SIGNAL_2158("2158", "可充电储能装置电压", null, null, null),
    SIGNAL_200F("200F", "发动机状态", null, null, null),
    SIGNAL_220D("220D", "燃料消耗率", null, new SignalConverter220D(), null),
    SIGNAL_1BC1("1BC1", "驱动电机列表", null, null, null),
    SIGNAL_1BC2("1BC2", "可充电储能装置", null, null, null),
    SIGNAL_1BC3("1BC3", "可充电储能装置温度数据集合", null, null, null),
    SIGNAL_PUBLICALARMCODE("publicAlarmCode", "通用报警标识", null, null, null),

    ;
    private String code;
    private String name;
    private SignalGroupEnum group;
    private BaseSignalConverter converter;
    private String parent;

    SignalEnum(String code, String name, SignalGroupEnum group, BaseSignalConverter converter, String parent) {
        this.code = code;
        this.name = name;
        this.group = group;
        this.converter = converter;
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public SignalGroupEnum getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public static List<SignalEnum> getByGroup(SignalGroupEnum group) {
        List<SignalEnum> signalGroups = new ArrayList();
        for (SignalEnum value : SignalEnum.values()) {
            if (Objects.equals(value.getGroup(), group)) {
                signalGroups.add(value);
            }
        }
        return signalGroups;
    }

    public static SignalEnum getByCode(String code) {
        for (SignalEnum value : SignalEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }

    public BaseSignalConverter getConverter() {
        return converter;
    }

    public String getParent() {
        return parent;
    }
}
