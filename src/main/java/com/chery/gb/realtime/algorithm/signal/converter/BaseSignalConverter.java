package com.chery.gb.realtime.algorithm.signal.converter;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
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

    public Object convert(Object value, SignalConfigBO bo) {
        if (Objects.isNull(value)) {
            return null;
        }
        if (value instanceof JSONArray) {
            List<Object> dataArr = JSON.parseArray(value.toString());
            List<Object> datas = new ArrayList<>();
            for (Object data : dataArr) {
                Object dataValue = convert(data, bo);
                datas.add(dataValue);
            }
            return datas;
        } else {
            if (Objects.nonNull(value)
                    && StrUtil.isNotBlank(value.toString())
                    && !Objects.equals(bo.getInvalidValue(), value.toString())
                    && !Objects.equals(bo.getErrorValue(), value.toString())) {
                Double divide = divide(value.toString(), bo.getScale(), bo.getPoint());
                return getOffset(divide.toString(), bo.getOffset());
            }
        }
        return value;
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
