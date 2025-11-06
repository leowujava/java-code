package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;


/**
 * 2145
 * 最低温度值
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@SignalConfig(SignalEnum.SIGNAL_2145)
public class SignalConfig_2145 extends BaseSignalConfig {

    public SignalConfig_2145() {
         signalConfigBO = SignalConfigBO.builder()
                .scale(null)
                .offset(null)
                .range(null)
                .min(0)
                .max(250)
                .errorValue("254")
                .invalidValue("255")
                .build();
    }

}
