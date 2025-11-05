package com.chery.gb.realtime.algorithm.converter;


import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.ConvertBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_21D1)
public class SignalConverter21D1 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal21D1Obj) {
        //驱动电机转矩 0x21D1 offset: 20000 scale: 10
        ConvertBO convertBO = ConvertBO.builder()
                .scale(10)
                .point(1)
                .offset(2000)
                .errorValue("65534")
                .invalidValue("65535")
                .build();
        return convert(signal21D1Obj, convertBO);
    }

}
