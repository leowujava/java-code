package com.chery.gb.realtime.algorithm.util;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.signal.converter.BaseSignalConverter;
import com.chery.gb.realtime.algorithm.signal.factory.SignalConverterFactory;

import java.util.Map;
import java.util.Objects;

/**
 * 信号偏移量转换
 *
 * @author zhonghua
 * @date 2025/6/12
 */
public class SignalUtil {
    /**
     * 转换偏移量
     *
     * @param tboxDataMap
     */
    public static void convert(Map<String, Object> tboxDataMap) {
        for (SignalEnum value : SignalEnum.values()) {
            BaseSignalConverter converter = SignalConverterFactory.getByCode(value.getCode());
            if (converter != null) {
                String code = value.getCode();
                Object o = tboxDataMap.get(code);
                if (Objects.nonNull(o)) {
                    tboxDataMap.put(code, converter.convert(o));
                } else {
                    tboxDataMap.entrySet().forEach(entry -> {
                        Object value1 = entry.getValue();
                        if (Objects.nonNull(value1) && value1 instanceof JSONArray) {
                            for (Object object : (JSONArray) value1) {
                                if (object instanceof JSONObject) {
                                    JSONObject object1 = (JSONObject) object;
                                    for (String key : object1.keySet()) {
                                        if (key.equals(code)) {
                                            object1.put(key, converter.convert(object1.get(key)));
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    public static Object getSignalValue(Map<String, Object> signalMap, String signalCode) {
        SignalEnum signalEnum = SignalEnum.getByCode(signalCode);
        if (signalEnum == null) {
            return null;
        }
        SignalEnum parent = SignalEnum.getByCode(signalEnum.getParent());
        if (parent != null) {
            Object signalValue = getSignalValue(signalMap, parent.getCode());
            if (signalValue == null) {
                return null;
            }else {
                if (signalValue instanceof JSONObject) {
                    return  ((JSONObject) signalValue).get(signalCode);
                }else if (signalValue instanceof JSONArray) {
                    for (Object object : ((JSONArray) signalValue)) {
                        if (object instanceof JSONObject) {
                            return  ((JSONObject) object).get(signalCode);
                        }
                    }
                }
                return signalValue;
            }
        } else {
            return signalMap.get(signalCode);
        }
    }
}
