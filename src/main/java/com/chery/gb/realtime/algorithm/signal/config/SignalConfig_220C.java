package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;


/**
 * 220C
 * 车辆状态
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@SignalConfig(SignalEnum.SIGNAL_220C)
public class SignalConfig_220C extends BaseSignalConfig {

    public SignalConfig_220C() {
         signalConfigBO = SignalConfigBO.builder()
                .scale(null)
                .offset(null)
                .range(null)
                .min(1)
                .max(3)
                .errorValue("254")
                .invalidValue("155")
                .build();
    }

}
