package com.chery.gb.realtime.algorithm.util;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wugaoyang
 * @date 2025/9/8 星期一
 */
public class CommonDataUtil {


    public static volatile Map<String, Double> totalMileageMap = new ConcurrentHashMap<>();

    //上一条数据
    public static volatile Map<String, Map<String, Object>> preData = new ConcurrentHashMap<>();
}
