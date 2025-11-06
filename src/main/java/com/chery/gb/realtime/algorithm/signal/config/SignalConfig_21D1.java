package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;


/**
 * 21C2
 * 制动系统报警
 *
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@SignalConfig(SignalEnum.SIGNAL_21D1)
public class SignalConfig_21D1 extends BaseSignalConfig {

    public SignalConfig_21D1() {
        signalConfigBO = SignalConfigBO.builder()
                .scale(10)
                .point(1)
                .offset(2000)
                .errorValue("65534")
                .invalidValue("65535")
                .build();
    }

}
