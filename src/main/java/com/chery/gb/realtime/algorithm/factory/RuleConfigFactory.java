package com.chery.gb.realtime.algorithm.factory;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.validate.config.BaseConfig;
import com.chery.gb.realtime.algorithm.validate.config.RuleConfig_VehicleStateIsNull;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 规则配置工厂
 *
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public class RuleConfigFactory {

    private static volatile Map<String, BaseConfig> ruleConfigMap = null;

    private RuleConfigFactory() {
    }

    private static void init() {
        ruleConfigMap = new HashMap();
        Reflections reflections = new Reflections(RuleConfig_VehicleStateIsNull.class.getPackage().getName(), Scanners.TypesAnnotated);

        // 找出所有带有 @RulePreCondition 注解的类
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(RuleConfig.class);

        // 输出结果
        if (annotatedClasses.isEmpty()) {
            System.out.println("未找到带有 @RuleConfig 的类");
        } else {
            System.out.println("找到以下类：");
            annotatedClasses.forEach(clazz -> {
                System.out.println(" - " + clazz.getName());
                try {
                    ruleConfigMap.put(clazz.getDeclaredAnnotation(RuleConfig.class).rule().getCode(), (BaseConfig) clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public static List<RuleDetailBO> getCondition(String ruleCode) {
        BaseConfig config = getConfig(ruleCode, null);
        if (config == null) {
            return null;
        }
        return config.getCondition();
    }

    public static List<RuleDetailBO> getPreCondition(String ruleCode) {
        BaseConfig config = getConfig(ruleCode, null);
        if (config == null) {
            return null;
        }
        return config.getPreCondition();
    }

    public static boolean isReturn(String ruleCode) {
        BaseConfig config = getConfig(ruleCode, null);
        if (config == null) {
            return false;
        }
        return config.isReturn();
    }

    public static BaseConfig getConfig(String ruleCode, Map<String, Map<String, List<String>>> ruleDetailMap) {
        if (ruleConfigMap == null) {
            synchronized (RuleConfigFactory.class) {
                if (ruleConfigMap == null) {
                    init();
                }
            }
        }
        BaseConfig baseConfig = ruleConfigMap.get(ruleCode);
        if (baseConfig == null) {
            baseConfig = new BaseConfig() {
                @Override
                public RuleConfigBO getRuleConfigBO() {
                    return new  RuleConfigBO();
                }
            };
        }
        return baseConfig;
    }
}
