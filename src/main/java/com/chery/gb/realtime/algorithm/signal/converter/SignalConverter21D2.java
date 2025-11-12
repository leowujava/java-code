package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.annotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_21D2)
public class SignalConverter21D2 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal21D2Obj) {
        //驱动电机温度 0x21D2 offset: 40
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .offset(40)
                .errorValue("254")
                .invalidValue("255")
                .build();
        return convert(signal21D2Obj, signalConfigBO);
    }

}
