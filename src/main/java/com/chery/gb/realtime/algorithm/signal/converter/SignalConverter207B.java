package com.chery.gb.realtime.algorithm.signal.converter;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_207B)
public class SignalConverter207B extends BaseSignalConverter {
    @Override
    public Object convert(Object signal207BObj) {
        //GPS纬度 0x207B scale: 1000000 offset: 90
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .scale(1000000)
                .point(6)
                .offset(90)
                .build();
        return convert(signal207BObj, signalConfigBO);
    }

}
