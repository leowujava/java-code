package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;


/**
 * 207B
 * 纬度
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@SignalConfig(SignalEnum.SIGNAL_207B)
public class SignalConfig_207B extends BaseSignalConfig {

    public SignalConfig_207B() {
        signalConfigBO = SignalConfigBO.builder()
                .scale(1000000)
                .point(6)
                .offset(90)
                .min(0)
                .max(90000000)
                .build();
    }

}
