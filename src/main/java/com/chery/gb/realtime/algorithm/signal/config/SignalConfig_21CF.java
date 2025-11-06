package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;


/**
 * 21CF
 * 驱动电机控制器温度
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@SignalConfig(SignalEnum.SIGNAL_21CF)
public class SignalConfig_21CF extends BaseSignalConfig {

    public SignalConfig_21CF() {
         signalConfigBO = SignalConfigBO.builder()
                .offset(40)
                .errorValue("254")
                .invalidValue("255")
                .build();
    }

}
