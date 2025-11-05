package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;


/**
 * 2001
 * 车速
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@SignalConfig(SignalEnum.SIGNAL_2001)
public class SignalConfig_2001 extends BaseSignalConfig {

    public SignalConfig_2001() {
         signalConfigBO = SignalConfigBO.builder()
                .scale(null)
                .offset(null)
                .range(null)
                .min(null)
                .max(null)
                .errorValue(null)
                .invalidValue(null)
                .build();
    }

}
