package com.chery.gb.realtime.algorithm.annotation;


import com.chery.gb.realtime.algorithm.workflow.GbWorkFlowEnum;

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
    GbWorkFlowEnum value();
}
