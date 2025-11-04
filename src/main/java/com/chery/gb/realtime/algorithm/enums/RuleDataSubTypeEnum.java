package com.chery.gb.realtime.algorithm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据类规则子类:全部/传输规则/缺失/数据精度不足/数据越界/无效值/异常值/逻辑异常
 * @auth: zhonghua
 * @date: 2025/3/28
 **/
@Getter
@AllArgsConstructor
public enum RuleDataSubTypeEnum {

    TRANSMISSION_RULE("传输规则"),
    MISSING("缺失"),
    DATA_PRECISION( "数据精度不足"),
    DATA_OUT_OF_BOUNDS("数据越界"),
    INVALID("无效值"),
    EXCEPTION_VALUE("异常值"),
    LOGIC_EXCEPTION("逻辑异常");

    private String desc;
}
