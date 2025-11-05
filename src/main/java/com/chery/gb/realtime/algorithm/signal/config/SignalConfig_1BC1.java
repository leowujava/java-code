package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;


/**
 * 1BC1
 * 驱动电机列表
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@SignalConfig(SignalEnum.SIGNAL_1BC1)
public class SignalConfig_1BC1 extends BaseSignalConfig {

    public SignalConfig_1BC1() {
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
