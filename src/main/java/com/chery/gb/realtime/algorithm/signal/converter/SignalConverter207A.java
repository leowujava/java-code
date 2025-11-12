package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
//@SignalConverter(SignalEnum.SIGNAL_207A)
public class SignalConverter207A extends BaseSignalConverter {
    @Override
    public Object convert(Object signal207AObj) {
        //GPS经度 0x207A scale: 1000000 offset: 180
        SignalConfigBO signalConfigBO = SignalConfigBO.builder()
                .scale(1000000)
                .point(6)
                .offset(180)
                .build();
        return convert(signal207AObj, signalConfigBO);
    }

}
