package com.chery.gb.realtime.algorithm.rule.factory;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.rule.validator.BaseRuleValidator;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 规则校验工厂
 *
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public class RuleValidatorFactory {

    private static volatile Map<String, BaseRuleValidator> ruleValidateMap = null;

    private RuleValidatorFactory() {
    }

    private static void init() {
        ruleValidateMap = new HashMap();
        Reflections reflections = new Reflections(BaseRuleValidator.class.getPackage().getName(), Scanners.TypesAnnotated);

        // 找出所有带有 @RuleValidate 注解的类
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(RuleValidate.class);

        // 输出结果
        if (annotatedClasses.isEmpty()) {
            System.out.println("未找到带有 @RuleValidate 的类");
        } else {
//            System.out.println("找到以下类：");
            annotatedClasses.forEach(clazz -> {
//                System.out.println(" - " + clazz.getName());
                try {
                    ruleValidateMap.put(clazz.getDeclaredAnnotation(RuleValidate.class).rule().getCode(), (BaseRuleValidator) clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public static BaseRuleValidator getRuleValidate(String ruleCode) {
        if (ruleValidateMap == null) {
            synchronized (RuleValidatorFactory.class) {
                if (ruleValidateMap == null) {
                    init();
                }
            }
        }
        BaseRuleValidator baseRuleValidator = ruleValidateMap.get(ruleCode);
        if (baseRuleValidator == null && NewGbRuleCodeEnum.getByCode(ruleCode) != null) {
            baseRuleValidator = new BaseRuleValidator();
            baseRuleValidator.setRuleCode(ruleCode);
            ruleValidateMap.put(ruleCode, baseRuleValidator);
        }
        return baseRuleValidator;
    }
}
