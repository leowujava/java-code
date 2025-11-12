package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.annotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_2147)
public class SignalConverter2147 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal2147Obj) {
        //电池单体电压最高值 0x2147 scale: 1000
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .scale(1000)
                .point(3)
                .errorValue("65534")
                .invalidValue("65535")
                .build();
        return convert(signal2147Obj, signalConfigBO);
    }

}
