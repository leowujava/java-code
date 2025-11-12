package com.chery.gb.realtime.algorithm.rule.factory;


import com.chery.gb.realtime.algorithm.annotation.RuleConfig;
import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.rule.config.BaseRuleConfig;
import com.chery.gb.realtime.algorithm.rule.config.CommonCondition;
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

    private static volatile Map<String, BaseRuleConfig> ruleConfigMap = null;

    private RuleConfigFactory() {
    }

    private static void init() {
        ruleConfigMap = new HashMap();
        Reflections reflections = new Reflections(BaseRuleConfig.class.getPackage().getName(), Scanners.TypesAnnotated);

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
                    ruleConfigMap.put(clazz.getDeclaredAnnotation(RuleConfig.class).rule().getCode(), (BaseRuleConfig) clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public static List<RuleDetailBO> getCondition(String ruleCode) {
        BaseRuleConfig config = getConfig(ruleCode, null);
        if (config == null) {
            return null;
        }
        return config.getCondition();
    }

    public static boolean isReturn(String ruleCode) {
        BaseRuleConfig config = getConfig(ruleCode, null);
        if (config == null) {
            return false;
        }
        return config.isReturn();
    }

    public static BaseRuleConfig getConfig(String ruleCode, Map<String, Map<String, List<RuleDetailBO>>> ruleDetailMap) {
        if (ruleConfigMap == null) {
            synchronized (RuleConfigFactory.class) {
                if (ruleConfigMap == null) {
                    init();
                }
            }
        }
        BaseRuleConfig baseRuleConfig = ruleConfigMap.get(ruleCode);
        if (baseRuleConfig == null) {
            RuleConditionBO configBO = RuleConfigFactory.getRuleConfigBO(ruleDetailMap, ruleCode);
            List<RuleDetailBO> conditions = configBO.getConditions();
            baseRuleConfig = new BaseRuleConfig();
            baseRuleConfig.setConditionBO(configBO);
            RuleConditionBO condition = CommonCondition.buildCondition(NewGbRuleCodeEnum.getByCode(ruleCode));
            if (condition != null) {
                if (CollectionUtils.isNotEmpty(conditions)) {
                    condition.getConditions().addAll(conditions);
                }
                baseRuleConfig.setConditionBO(condition);
            }
            ruleConfigMap.put(ruleCode, baseRuleConfig);
        }
        return baseRuleConfig;
    }

    private static RuleConditionBO getRuleConfigBO(Map<String, Map<String, List<RuleDetailBO>>> ruleDetailMap, String ruleCode) {
        RuleConditionBO ruleConfigBO = new RuleConditionBO();
        if (ruleDetailMap != null) {
            Map<String, List<RuleDetailBO>> stringListMap = ruleDetailMap.get(ruleCode);
            if (stringListMap != null) {
                ArrayList<RuleDetailBO> conditions = new ArrayList<>();
                for (List<RuleDetailBO> value : stringListMap.values()) {
                    conditions.addAll(value);
                }
                ruleConfigBO.getConditions().addAll(conditions);
            }
        }
        return ruleConfigBO;
    }
}
