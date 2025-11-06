package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;


/**
 * 215C
 * 总电压
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@SignalConfig(SignalEnum.SIGNAL_215C)
public class SignalConfig_215C extends BaseSignalConfig {

    public SignalConfig_215C() {
        signalConfigBO = SignalConfigBO.builder()
                .scale(10)
                .point(1)
                .errorValue("65534")
                .invalidValue("65535")
                .build();
    }

}
