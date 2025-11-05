package com.chery.gb.realtime.algorithm.converter;


import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.ConvertBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_21D0)
public class SignalConverter21D0 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal21D0Obj) {
        //驱动电机转速 0x21D0 offset: 20000
        ConvertBO convertBO = ConvertBO.builder()
                .offset(20000)
                .errorValue("65534")
                .invalidValue("65535")
                .build();
        return convert(signal21D0Obj, convertBO);
    }

}
