package com.chery.gb.realtime.algorithm.factory;


import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.converter.BaseSignalConverter;
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
public class SignalConverterFactory {

    private static volatile Map<String, BaseSignalConverter> signalConverterMap = null;

    private SignalConverterFactory() {
    }

    private static void init() {
        signalConverterMap = new HashMap();
        Reflections reflections = new Reflections(BaseSignalConverter.class.getPackage().getName(), Scanners.TypesAnnotated);

        // 找出所有带有 @RulePreCondition 注解的类
        Class<SignalConverter> annotation = SignalConverter.class;
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotation);

        // 输出结果
        if (annotatedClasses.isEmpty()) {
            System.out.println("未找到带有 @RuleConfig 的类");
        } else {
//            System.out.println("找到以下类：");
            annotatedClasses.forEach(clazz -> {
//                System.out.println(" - " + clazz.getName());
                try {
                    signalConverterMap.put(clazz.getDeclaredAnnotation(annotation).value().getCode(), (BaseSignalConverter) clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static BaseSignalConverter getByCode(String code) {
        if (signalConverterMap == null) {
            synchronized (RuleValidateFactory.class) {
                if (signalConverterMap == null) {
                    init();
                }
            }
        }
        return signalConverterMap.get(code);
    }
}
