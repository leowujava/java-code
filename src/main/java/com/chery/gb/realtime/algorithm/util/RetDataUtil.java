package com.chery.gb.realtime.algorithm.util;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhonghua
 * @date 2025/6/17
 */
public class RetDataUtil {
    /**
     * 构建返回数据结构
     */
    public static JSONObject buildResult(String code, JSONObject result) {
        JSONArray arrayData=new JSONArray();
        JSONObject resultDetailData = new JSONObject();
        resultDetailData.put("ruleCode", code);
        arrayData.add(resultDetailData);
        result.put("data",arrayData);
        return result;
    }

    /**
     * 构建数据Map
     */
    public static JSONObject buildRetMap(String ruleCode,Map<String,Object> gbValueMap){
        JSONObject retMap = new JSONObject();
        retMap.put("ruleType","");
        retMap.put("ruleCode",ruleCode);
        retMap.put("gbValue",gbValueMap);
        return retMap;
    }
}
