package com.chery.gb.realtime.algorithm.signal.factory;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.rule.factory.RuleValidateFactory;
import com.chery.gb.realtime.algorithm.signal.config.BaseSignalConfig;
import com.chery.gb.realtime.algorithm.signal.converter.BaseSignalConverter;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 规则配置工厂
 *
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public class SignalConfigFactory {

    private static volatile Map<String, BaseSignalConfig> signalConfigMap = null;

    private SignalConfigFactory() {
    }

    private static void init() {
        signalConfigMap = new HashMap();
        Reflections reflections = new Reflections(BaseSignalConfig.class.getPackage().getName(), Scanners.TypesAnnotated);

        // 找出所有带有 @RulePreCondition 注解的类
        Class<SignalConfig> annotation = SignalConfig.class;
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotation);

        // 输出结果
        if (annotatedClasses.isEmpty()) {
            System.out.println("未找到带有 @RuleConfig 的类");
        } else {
//            System.out.println("找到以下类：");
            annotatedClasses.forEach(clazz -> {
//                System.out.println(" - " + clazz.getName());
                try {
                    signalConfigMap.put(clazz.getDeclaredAnnotation(annotation).value().getCode(), (BaseSignalConfig) clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static BaseSignalConfig getByCode(String code) {
        if (signalConfigMap == null) {
            synchronized (SignalConfigFactory.class) {
                if (signalConfigMap == null) {
                    init();
                }
            }
        }
        return signalConfigMap.get(code);
    }
}
