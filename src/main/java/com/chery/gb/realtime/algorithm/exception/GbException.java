package com.chery.gb.realtime.algorithm.exception;


import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

/**
 * 国标异常
 *
 * @author wugaoyang
 * @date 2025/10/31 星期五
 *
 */
public class GbException extends RuntimeException {

    public GbException() {
        super();
    }

    public GbException(String message) {
        super(message);
    }

    public GbException(NewGbRuleCodeEnum ruleCodeEnum) {
        super(ruleCodeEnum.getCode() + ":" + ruleCodeEnum.getName());
    }
}
