package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_220E)
public class SignalConverter220E extends BaseSignalConverter {
    @Override
    public Object convert(Object signal220EObj) {
        //单体电池电压 0x220E scale: 1000
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .scale(1000)
                .point(3)
                .offset(2000)
                .invalidValue("65535")
                .build();
        return convert(signal220EObj, signalConfigBO);
    }

}
