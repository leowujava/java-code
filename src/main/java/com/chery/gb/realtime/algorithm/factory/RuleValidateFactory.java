package com.chery.gb.realtime.algorithm.factory;


import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.validate.rule.BaseRuleValidate;
import com.chery.gb.realtime.algorithm.validate.rule.RuleValidate_1;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 规则校验工厂
 *
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public class RuleValidateFactory {

    private static volatile Map<String, BaseRuleValidate> ruleValidateMap = null;

    private RuleValidateFactory() {
    }

    private static void init() {
        ruleValidateMap = new HashMap();
        Reflections reflections = new Reflections(RuleValidate_1.class.getPackage().getName(), Scanners.TypesAnnotated);

        // 找出所有带有 @RuleValidate 注解的类
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(RuleValidate.class);

        // 输出结果
        if (annotatedClasses.isEmpty()) {
            System.out.println("未找到带有 @RuleValidate 的类");
        } else {
            System.out.println("找到以下类：");
            annotatedClasses.forEach(clazz -> {
                System.out.println(" - " + clazz.getName());
                try {
                    ruleValidateMap.put(clazz.getDeclaredAnnotation(RuleValidate.class).rule().getCode(), (BaseRuleValidate) clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public static BaseRuleValidate getRuleValidate(String ruleCode) {
        if (ruleValidateMap == null) {
            synchronized (RuleValidateFactory.class) {
                if (ruleValidateMap == null) {
                    init();
                }
            }
        }
        return ruleValidateMap.get(ruleCode);
    }
}
