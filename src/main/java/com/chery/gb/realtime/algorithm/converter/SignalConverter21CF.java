package com.chery.gb.realtime.algorithm.converter;


import com.chery.gb.realtime.algorithm.bo.ConvertBO;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
public class SignalConverter21CF extends SignalBaseConverter {
    @Override
    public Object convert(Object signal21CFObj) {
        //驱动电机控制器温度 0x21CF offset: 40
        ConvertBO convertBO = ConvertBO.builder()
                .offset(40)
                .errorValue("254")
                .invalidValue("255")
                .build();
        return convert(signal21CFObj, convertBO);
    }

}
