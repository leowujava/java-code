package com.chery.gb.realtime.algorithm.util.check;


import com.alibaba.fastjson2.JSONArray;

import java.util.Map;

/**
 * @author wugaoyang
 * @date 2025/10/31 星期五
 *
 */
public class PreDataCheckUtil {


    /**
     * 检测前置数据
     * <p>
     * Check code项
     * 239,240,241,242,245,246,258
     * 检测整车、
     * 动力蓄电池、
     * 车辆位置、
     * 驱动电机、
     * 发动机、
     * 报警数据信息体
     * 是否上传
     *
     * @param signalMap
     * @param retData
     */
    public static void checkPreData(Map<String, Object> signalMap, JSONArray retData) {
        checkVehicleData(signalMap, retData);
        checkPowerData(signalMap, retData);
        checkPosData(signalMap, retData);
        checkDriveMotorData(signalMap, retData);
        checkEngine(signalMap, retData);
        checkAlertData(signalMap, retData);
    }

    /**
     * 报警数据信息体
     *
     * @param signalMap
     * @param retData
     */
    private static void checkAlertData(Map<String, Object> signalMap, JSONArray retData) {

    }

    /**
     * 发动机
     *
     * @param signalMap
     * @param retData
     */
    private static void checkEngine(Map<String, Object> signalMap, JSONArray retData) {

    }

    /**
     * 检测驱动电机
     *
     * @param signalMap
     * @param retData
     */
    private static void checkDriveMotorData(Map<String, Object> signalMap, JSONArray retData) {

    }

    /**
     * 检测车辆位置
     *
     * @param signalMap
     * @param retData
     */
    private static void checkPosData(Map<String, Object> signalMap, JSONArray retData) {

    }

    /**
     * 检测动力蓄电池
     *
     * @param signalMap
     * @param retData
     */
    private static void checkPowerData(Map<String, Object> signalMap, JSONArray retData) {

    }

    /**
     * 239
     * 检测整车
     *
     * @param signalMap
     * @param retData
     */
    private static void checkVehicleData(Map<String, Object> signalMap, JSONArray retData) {

    }
}
