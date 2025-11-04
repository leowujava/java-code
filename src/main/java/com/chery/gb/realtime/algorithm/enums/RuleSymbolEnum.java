package com.chery.gb.realtime.algorithm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 规则符号
 * @auth: zhonghua
 * @date: 2025/3/28
 **/
@Getter
@AllArgsConstructor
public enum RuleSymbolEnum {

    LT("<"),
    GT(">"),
    EQ("="),
    GE( ">="),
    LE("<="),
    NE("!=");

    private String desc;

    public static String getDesc(String name){
        RuleSymbolEnum symbolEnum = RuleSymbolEnum.valueOf(name);
        if(symbolEnum != null) {
            return symbolEnum.getDesc();
        }
        return null;
    }
}
