package com.chery.gb.realtime.algorithm.signal.config;


import com.chery.gb.realtime.algorithm.anotation.SignalConfig;
import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;


/**
 * 20FC
 * 最高电压电池子系统号
 * 
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
@SignalConfig(SignalEnum.SIGNAL_20FC)
public class SignalConfig_20FC extends BaseSignalConfig {

    public SignalConfig_20FC() {
         signalConfigBO = SignalConfigBO.builder()
                .scale(null)
                .offset(null)
                .range(null)
                .max(null)
                .min(null)
                .errorValue(null)
                .invalidValue(null)
                .build();
    }

}
