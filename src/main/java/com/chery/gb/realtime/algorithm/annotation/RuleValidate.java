package com.chery.gb.realtime.algorithm.annotation;


import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RuleValidate {
    NewGbRuleCodeEnum rule();
}
