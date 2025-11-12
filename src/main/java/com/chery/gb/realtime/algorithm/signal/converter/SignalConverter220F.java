package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.annotation.SignalConverter;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_220F)
public class SignalConverter220F extends BaseSignalConverter {
    @Override
    public Object convert(Object signal220FObj) {
        //可充电储能子系统各温度探针检测到的温度值 0x220F offset: 40
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .offset(40)
                .build();
        return convert(signal220FObj, signalConfigBO);
    }

}
