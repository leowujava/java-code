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
@SignalConverter(SignalEnum.SIGNAL_215C)
public class SignalConverter215C extends BaseSignalConverter {
    @Override
    public Object convert(Object signal215CObj) {
        //总电压 0x215C scale: 10
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .scale(10)
                .point(1)
                .errorValue("65534")
                .invalidValue("65535")
                .build();
        return convert(signal215CObj, signalConfigBO);
    }

}
