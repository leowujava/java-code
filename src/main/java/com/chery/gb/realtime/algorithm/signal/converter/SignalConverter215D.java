package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.annotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_215D)
public class SignalConverter215D extends BaseSignalConverter {
    @Override
    public Object convert(Object signal215DObj) {
        //总电流 0x215D offset:1000 scale: 10
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .scale(10)
                .point(1)
                .offset(1000)
                .errorValue("65534")
                .invalidValue("65535")
                .build();
        return convert(signal215DObj, signalConfigBO);
    }

}
