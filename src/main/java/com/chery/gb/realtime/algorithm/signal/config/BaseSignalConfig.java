package com.chery.gb.realtime.algorithm.signal.config;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.annotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.util.SignalUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/11/5 星期三
 *
 */
@Data
public class BaseSignalConfig {

    protected SignalConfigBO signalConfigBO;

    /**
     * 检验空值
     *
     * @param signalMap
     * @return
     */
    public boolean validateNull(Map<String, Object> signalMap) {
        String signalCode = getSignalCode();
        Object signalValue = SignalUtil.getSignalValue(signalMap, signalCode);
        return signalValue == null || StrUtil.isBlank(signalValue.toString());
    }

    /**
     * 校验异常值
     *
     * @param signalMap
     * @return
     */
    public boolean validateError(Map<String, Object> signalMap) {
        String signalCode = getSignalCode();
        Object signalValue = SignalUtil.getSignalValue(signalMap, signalCode);
        if (signalValue == null) {
            return false;
        }
        if (signalConfigBO == null) {
            return false;
        }
        String errorValue = signalConfigBO.getErrorValue();
        return Objects.equals(String.valueOf(signalValue), errorValue);
    }

    /**
     * 校验无效值
     *
     * @param signalMap
     * @return
     */
    public boolean validateInvalid(Map<String, Object> signalMap) {
        String signalCode = getSignalCode();
        Object signalValue = SignalUtil.getSignalValue(signalMap, signalCode);
        if (signalValue == null) {
            return false;
        }
        if (signalConfigBO == null) {
            return false;
        }
        String errorValue = signalConfigBO.getInvalidValue();
        return Objects.equals(String.valueOf(signalValue), errorValue);
    }

    /**
     * 校验有效范围
     *
     * @param signalMap
     * @return
     */
    public boolean validateRange(Map<String, Object> signalMap) {
        String signalCode = getSignalCode();
        Object signalValue = SignalUtil.getSignalValue(signalMap, signalCode);
        if (signalValue == null) {
            return false;
        }
        if (signalConfigBO == null || signalConfigBO.getMin() == null || signalConfigBO.getMax() == null) {
            return false;
        }
        BigDecimal bigDecimal = new BigDecimal(signalValue.toString());
        if (bigDecimal.compareTo(new BigDecimal(signalConfigBO.getMin())) <= 0) {

        }
        return false;
    }

    public String getSignalCode() {
        SignalConfig signalConfig = getClass().getDeclaredAnnotation(SignalConfig.class);
        if (signalConfig != null && signalConfig.value() != null) {
            return signalConfig.value().getCode();
        }
        return null;
    }

}
