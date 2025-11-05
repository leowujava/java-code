package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_2143)
public class SignalConverter2143 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal2143Obj) {
        //最高温度值 0x2143 offset: 40
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .offset(40)
                .errorValue("254")
                .invalidValue("255")
                .build();
        return convert(signal2143Obj, signalConfigBO);
    }

}
