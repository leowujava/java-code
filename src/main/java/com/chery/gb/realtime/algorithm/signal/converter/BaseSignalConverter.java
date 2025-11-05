package com.chery.gb.realtime.algorithm.signal.converter;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
public abstract class BaseSignalConverter {

    /**
     * 偏移量转换
     *
     * @param param
     * @return
     */
    public abstract Object convert(Object param);

    public Object convert(Object param, SignalConfigBO bo) {
        if (Objects.nonNull(param) && StrUtil.isNotBlank(param.toString()) && !Objects.equals(bo.getInvalidValue(), param.toString()) && !Objects.equals(bo.getErrorValue(), param.toString())) {
            Double divide = divide(param.toString(), bo.getScale(), bo.getPoint());
            return getOffset(divide.toString(), bo.getOffset());
        }
        return param;
    }

    static Double divide(String value, Integer scale, Integer point) {
        if (scale == null) {
            return Double.valueOf(value);
        }
        return new BigDecimal(value).divide(new BigDecimal(scale), point, RoundingMode.HALF_DOWN).doubleValue();
    }

    static Double getOffset(String value, Integer offset) {
        if (offset == null) {
            return Double.valueOf(value);
        }
        return new BigDecimal(value).subtract(new BigDecimal(offset)).doubleValue();
    }

}
