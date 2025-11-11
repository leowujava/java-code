package com.chery.gb.realtime.algorithm.rule.validator;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.chery.gb.realtime.algorithm.anotation.RuleValidate;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.enums.SignalGroupEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.rule.factory.RuleConfigFactory;
import com.chery.gb.realtime.algorithm.util.RetDataUtil;
import com.chery.gb.realtime.algorithm.util.check.GbRuleCheckUtil;
import com.chery.gb.realtime.algorithm.rule.config.BaseRuleConfig;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
@Data
public class BaseRuleValidator {

    private String ruleCode;

    public boolean validate(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {
        NewGbRuleCodeEnum gbRuleCodeEnum = NewGbRuleCodeEnum.getByCode(getRuleCode());
        String desc = getRuleCode();
        if (gbRuleCodeEnum != null) {
            desc += ":" + gbRuleCodeEnum.getName();
        }
        System.out.println("校验器：" + desc);
        BaseRuleConfig config = RuleConfigFactory.getConfig(getRuleCode(), ruleMap);
        List<RuleDetailBO> condition = config.getCondition();
        if (CollectionUtils.isNotEmpty(condition)) {
            boolean flag = GbRuleCheckUtil.checkByCondition(signalMap, condition, retData, getRuleCode());
            checkReturn(flag, (String) signalMap.get("vin"));
            return flag;
        }
        return false;
    }

    public void checkReturn(boolean flag, String vin) {
        BaseRuleConfig config = RuleConfigFactory.getConfig(getRuleCode(), null);
        String desc = "";
        RuleValidate declaredAnnotation = getClass().getDeclaredAnnotation(RuleValidate.class);
        NewGbRuleCodeEnum rule = NewGbRuleCodeEnum.getByCode(getRuleCode());
        if (declaredAnnotation != null) {
            desc = rule.getName() + ":" + rule.getDesc();
        } else if (config != null) {
            desc = config.getDesc();
        }
        if (StrUtil.isNotBlank(desc) && flag) {
            System.out.println(getClassName() + ":" + desc + "; vin:" + vin);
        }
        if ((config != null && config.isReturn() && flag) || (config == null && flag)) {
            if (declaredAnnotation != null) {
                throw new GbException(rule);
            }
            throw new GbException();
        }
    }

    public String getRuleCode() {
        RuleValidate ruleValidate = getClass().getDeclaredAnnotation(RuleValidate.class);
        if (ruleValidate != null && ruleValidate.rule() != null) {
            return ruleValidate.rule().getCode();
        }
        return ruleCode;
    }

    /**
     * 根据分组校验信号是否为Null
     *
     */
    protected static boolean checkNullByGroup(Map<String, Object> signalMap, SignalGroupEnum signalGroupEnum) {
        List<SignalEnum> byGroup = SignalEnum.getByGroup(signalGroupEnum);
        boolean flag = CollectionUtils.isNotEmpty(byGroup);
        for (SignalEnum signalEnum : byGroup) {
            Object o = signalMap.get(signalEnum.getCode());
            flag = Objects.equals(o, null) || Objects.equals("", o);
            if (!flag) {
                break;
            }
        }
        return flag;
    }

    /**
     * 封装异常数据
     *
     * @param retData
     * @param flag
     */
    protected void wrapErrorData(JSONArray retData, boolean flag) {
        if (flag) {
            Map<String, Object> gbValueMap = new HashMap<>();
            retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap(getRuleCode(), gbValueMap))));
        }
    }

    /**
     * 获取配置
     *
     * @param ruleMap
     * @return
     */
    protected BaseRuleConfig getConfig(Map<String, Map<String, List<RuleDetailBO>>> ruleMap) {
        BaseRuleConfig config = RuleConfigFactory.getConfig(getRuleCode(), ruleMap);
        return config;
    }

    public String getClassName() {
        return getClass().getPackage().getName() + ".RuleValidator_" + getRuleCode();
    }
}
