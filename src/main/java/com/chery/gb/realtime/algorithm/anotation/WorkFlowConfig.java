package com.chery.gb.realtime.algorithm.anotation;


import com.chery.gb.realtime.algorithm.workflow.WorkFlowEnum;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @author S00003829
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WorkFlowConfig {
    WorkFlowEnum value();
}
