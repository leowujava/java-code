package com.chery.gb.realtime.algorithm.signal.converter;


import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.signal.factory.SignalConfigFactory;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
//@SignalConverter(SignalEnum.SIGNAL_207B)
public class SignalConverter207B extends BaseSignalConverter {
    @Override
    public Object convert(Object signal207BObj) {
        //GPS纬度 0x207B scale: 1000000 offset: 90
        SignalConfigBO configBo = SignalConfigFactory.getByConfigCode(SignalEnum.SIGNAL_207B.getCode());
        return convert(signal207BObj, configBo);
    }

}
