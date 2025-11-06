package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;


/**
 * 207A
 * 经度
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@SignalConfig(SignalEnum.SIGNAL_207A)
public class SignalConfig_207A extends BaseSignalConfig {

    public SignalConfig_207A() {
        signalConfigBO = SignalConfigBO.builder()
                .scale(1000000)
                .point(6)
                .offset(180)
                .min(0)
                .max(180000000)
                .build();
    }

}
