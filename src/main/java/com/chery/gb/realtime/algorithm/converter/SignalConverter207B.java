package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_207B)
public class SignalConverter207B extends BaseSignalConverter {
    @Override
    public Object convert(Object signal207BObj) {
        //GPS纬度 0x207B scale: 1000000 offset: 90
        if(Objects.nonNull(signal207BObj) && StrUtil.isNotBlank(signal207BObj.toString())){
            Double signal207BValue = divide(signal207BObj.toString(),1000000,6);
            Double signal207B = getOffset(signal207BValue.toString(),90);
            //System.out.println("信号：207B scale: 1000000 offset: 90 转换前："+signal207BObj.toString()+" 转换后："+signal207B);
            return signal207B;
        }
        return signal207BObj;
    }

}
