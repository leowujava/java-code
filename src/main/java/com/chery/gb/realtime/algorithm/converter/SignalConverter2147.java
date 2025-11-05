package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_2147)
public class SignalConverter2147 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal2147Obj) {
        //电池单体电压最高值 0x2147 scale: 1000
        if(Objects.nonNull(signal2147Obj) && StrUtil.isNotBlank(signal2147Obj.toString())&& !Objects.equals("65534", signal2147Obj.toString()) && !Objects.equals("65535", signal2147Obj.toString())){
            double signal2147 = divide(signal2147Obj.toString(),1000,3);
            //System.out.println("信号：2147 scale: 1000 转换前："+signal2147Obj.toString()+" 转换后："+signal2147);
            return signal2147;
        }
        return signal2147Obj;
    }

}
