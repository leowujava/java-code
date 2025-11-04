package com.chery.gb.realtime.algorithm.enums;

import lombok.Getter;

/**
 * 规则关系
 * @author zhonghua
 * @date 2025/3/28
 */
@Getter
public enum RuleRelationEnum {

    OR("或"),
    AND("且");
    private String desc;

    RuleRelationEnum(String desc){
        this.desc = desc;
    }

}
