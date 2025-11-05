package com.chery.gb.realtime.algorithm.signal.converter;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_207A)
public class SignalConverter207A extends BaseSignalConverter {
    @Override
    public Object convert(Object signal207AObj) {
        //GPS经度 0x207A scale: 1000000 offset: 180
        if(Objects.nonNull(signal207AObj) && StrUtil.isNotBlank(signal207AObj.toString())){
            Double signal207AValue = divide(signal207AObj.toString(),1000000,6);
            Double signal207A = getOffset(signal207AValue.toString(),180);
            //System.out.println("信号：207A scale: 1000000 offset: 180 转换前："+signal207AObj.toString()+" 转换后："+signal207A);
            return signal207A;
        }
        return signal207AObj;
    }

}
