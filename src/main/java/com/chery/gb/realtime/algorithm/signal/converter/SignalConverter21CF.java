package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_21CF)
public class SignalConverter21CF extends BaseSignalConverter {
    @Override
    public Object convert(Object signal21CFObj) {
        //驱动电机控制器温度 0x21CF offset: 40
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .offset(40)
                .errorValue("254")
                .invalidValue("255")
                .build();
        return convert(signal21CFObj, signalConfigBO);
    }

}
