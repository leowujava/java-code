package com.chery.gb.realtime.algorithm.util.check;


import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.exception.GbException;

import java.util.List;
import java.util.Map;

/**
 * 运行模式检测
 *
 * @author wugaoyang
 * @date 2025/10/31 星期五
 *
 */
public class RunModeCheckUtil {

    public static void checkRunModeData(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {

        //检测前置数据
        checkPreData(signalMap, retData);

        //检测运行模式
        checkMode(signalMap, ruleMap, retData);

    }

    private static void checkMode(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {
        //充电模式（21AA）!="1" & 车辆状态（220C）!="2"

        //检测运行模式（21AB）1
        checkMode1(signalMap, ruleMap, retData);

        //检测运行模式（21AB）2
        checkMode2(signalMap, ruleMap, retData);

        //检测运行模式（21AB）3
        checkMode3(signalMap, ruleMap, retData);
    }

    private static void checkPreData(Map<String, Object> signalMap, JSONArray retData) {

    }

    private static void checkMode1(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {

    }

    private static void checkMode2(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {

    }

    private static void checkMode3(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {

    }
}
