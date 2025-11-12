package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.annotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_2001)
public class SignalConverter2001 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal2001Obj) {
        //仪表车速 0x2001 scale: 10
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .scale(10)
                .point(1)
                .errorValue("65534")
                .invalidValue("65535")
                .build();
        return convert(signal2001Obj, signalConfigBO);
    }

}
