package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

/**
 * @author wugaoyang
 * @date 2025/11/5 星期三
 *
 */
@SignalConfig(SignalEnum.SIGNAL_220C)
public class SignalConfig_220C extends  BaseSignalConfig{

    public SignalConfig_220C() {
        SignalConfigBO signalConfigBO1 = SignalConfigBO.builder()
                .errorValue("254")
                .invalidValue("255")
                .build();
        setSignalConfigBO(signalConfigBO1);
    }
}
