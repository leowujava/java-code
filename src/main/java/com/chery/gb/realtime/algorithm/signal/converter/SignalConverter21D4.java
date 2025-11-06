package com.chery.gb.realtime.algorithm.signal.converter;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_21D4)
public class SignalConverter21D4 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal21D4Obj) {
        //电机控制器直流母线电流 0x21D4 offset: 1000 scale: 10
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .scale(10)
                .point(1)
                .offset(1000)
                .errorValue("65534")
                .invalidValue("65535")
                .build();
        return convert(signal21D4Obj, signalConfigBO);
    }

}
