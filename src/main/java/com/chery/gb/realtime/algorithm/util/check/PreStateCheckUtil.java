package com.chery.gb.realtime.algorithm.util.check;


import com.alibaba.fastjson2.JSONArray;

import java.util.Map;

/**
 * @author wugaoyang
 * @date 2025/10/31 星期五
 *
 */
public class PreStateCheckUtil {
    /**
     * 前置状态检测
     *
     * @param signalMap
     * @param retData
     * @return
     */
    public static void checkPreState(Map<String, Object> signalMap, JSONArray retData) {
        checkVehicleState(signalMap, retData);
        checkChargingState(signalMap, retData);
    }

    private static void checkVehicleState(Map<String, Object> signalMap, JSONArray retData) {

    }

    private static void checkChargingState(Map<String, Object> signalMap, JSONArray retData) {

    }

}
