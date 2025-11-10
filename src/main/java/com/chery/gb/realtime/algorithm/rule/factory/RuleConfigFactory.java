package com.chery.gb.realtime.algorithm.rule.factory;


import com.chery.gb.realtime.algorithm.anotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.rule.config.BaseConfig;
import com.chery.gb.realtime.algorithm.rule.config.RuleConfig_VehicleStateIsNull;
import org.apache.commons.collections.CollectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.*;

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
//            System.out.println("找到以下类：");
            annotatedClasses.forEach(clazz -> {
//                System.out.println(" - " + clazz.getName());
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

    public static BaseConfig getConfig(String ruleCode, Map<String, Map<String, List<RuleDetailBO>>> ruleDetailMap) {
        if (ruleConfigMap == null) {
            synchronized (RuleConfigFactory.class) {
                if (ruleConfigMap == null) {
                    init();
                }
            }
        }
        BaseConfig baseConfig = ruleConfigMap.get(ruleCode);
        RuleConfigBO configBO = RuleConfigFactory.getRuleConfigBO(ruleDetailMap, ruleCode);
        if (baseConfig == null) {
            baseConfig = new BaseConfig() {
                @Override
                public RuleConfigBO getRuleConfigBO() {
                    RuleConditionBO condition = configBO.getCondition();
                    RuleConditionBO ruleConditionBO = buildCondition(NewGbRuleCodeEnum.getByCode(ruleCode));
                    if (ruleConditionBO != null) {
                        if (condition == null || CollectionUtils.isEmpty(condition.getConditions())) {
                            configBO.setCondition(ruleConditionBO);
                        } else {
                            List<RuleDetailBO> conditions = condition.getConditions();
                            if (CollectionUtils.isEmpty(conditions)) {
                                conditions = new ArrayList<>();
                                condition.setConditions(conditions);
                            }
                            List<RuleDetailBO> conditions1 = ruleConditionBO.getConditions();
                            if (CollectionUtils.isNotEmpty(conditions1)) {
                                conditions.addAll(conditions1);
                            }
                        }
                    }
                    return configBO;
                }
            };
            ruleConfigMap.put(ruleCode, baseConfig);
        } else {
            if (baseConfig.getCondition() == null) {
                RuleConfigBO ruleConfigBO = configBO;
                baseConfig.getRuleConfigBO().setCondition(ruleConfigBO.getCondition());
            }
        }
        return baseConfig;
    }

    private static RuleConfigBO getRuleConfigBO(Map<String, Map<String, List<RuleDetailBO>>> ruleDetailMap, String ruleCode) {
        RuleConfigBO ruleConfigBO = new RuleConfigBO();
        if (ruleDetailMap != null) {
            Map<String, List<RuleDetailBO>> stringListMap = ruleDetailMap.get(ruleCode);
            if (stringListMap != null) {
                ArrayList<RuleDetailBO> conditions = new ArrayList<>();
                for (List<RuleDetailBO> value : stringListMap.values()) {
                    conditions.addAll(value);
                }
                ruleConfigBO.setCondition(RuleConditionBO.builder().conditions(conditions).build());
            }
        }
        return ruleConfigBO;
    }
}
