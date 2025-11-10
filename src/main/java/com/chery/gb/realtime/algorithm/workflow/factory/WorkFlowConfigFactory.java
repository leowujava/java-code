package com.chery.gb.realtime.algorithm.workflow.factory;


import com.chery.gb.realtime.algorithm.anotation.WorkFlowConfig;
import com.chery.gb.realtime.algorithm.signal.config.BaseSignalConfig;
import com.chery.gb.realtime.algorithm.workflow.config.BaseWorkFlowConfig;
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
public class WorkFlowConfigFactory {

    private static volatile Map<String, BaseWorkFlowConfig> signalConfigMap = null;

    private WorkFlowConfigFactory() {
    }

    private static void init() {
        signalConfigMap = new HashMap();
        Reflections reflections = new Reflections(BaseWorkFlowConfig.class.getPackage().getName(), Scanners.TypesAnnotated);

        // 找出所有带有 @WorkFlowConfig 注解的类
        Class<WorkFlowConfig> annotation = WorkFlowConfig.class;
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotation);

        // 输出结果
        if (annotatedClasses.isEmpty()) {
            System.out.println("未找到带有 @WorkFlowConfig 的类");
        } else {
//            System.out.println("找到以下类：");
            annotatedClasses.forEach(clazz -> {
//                System.out.println(" - " + clazz.getName());
                try {
                    signalConfigMap.put(clazz.getDeclaredAnnotation(annotation).value().getCode(), (BaseWorkFlowConfig) clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static BaseWorkFlowConfig getByCode(String code) {
        if (signalConfigMap == null) {
            synchronized (WorkFlowConfigFactory.class) {
                if (signalConfigMap == null) {
                    init();
                }
            }
        }
        return signalConfigMap.get(code);
    }
}
