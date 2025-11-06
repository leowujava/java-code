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
@SignalConverter(SignalEnum.SIGNAL_220D)
public class SignalConverter220D extends BaseSignalConverter {
    @Override
    public Object convert(Object signal220DObj) {
        //燃料消耗率 220D scale: 100
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .scale(100)
                .point(2)
                .build();
        return convert(signal220DObj, signalConfigBO);
    }

}
