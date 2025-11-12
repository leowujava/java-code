package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.annotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_21D3)
public class SignalConverter21D3 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal21D3Obj) {
        //电机控制器输入电压 0x21D3 scale: 10
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .scale(10)
                .point(1)
                .errorValue("65534")
                .invalidValue("65535")
                .build();
        return convert(signal21D3Obj, signalConfigBO);
    }

}
