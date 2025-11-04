package com.chery.gb.realtime.algorithm.util.check;

import com.alibaba.fastjson2.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * 手动添加规则校验检测
 *
 * @author zhonghua
 * @date 2025/5/16
 */
public class CheckRuleDataByHandNewUtil {

    public static JSONArray checkData(Map<String, Object> signalMap, Map<String, Map<String, List<String>>> ruleMap, JSONArray retData) {
        ruleMap.forEach((code, rData) -> {
            GbRuleCheckUtil.checkByRuleCode(signalMap, ruleMap, retData, code);
        });
        return retData;
    }

}
