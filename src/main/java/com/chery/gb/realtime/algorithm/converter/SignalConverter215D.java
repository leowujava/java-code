package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
public class SignalConverter215D extends SignalBaseConverter {
    @Override
    public Object convert(Object signal215DObj) {
        //总电流 0x215D offset:1000 scale: 10
        if(Objects.nonNull(signal215DObj) && StrUtil.isNotBlank(signal215DObj.toString()) && !Objects.equals("65534", signal215DObj.toString()) && !Objects.equals("65535", signal215DObj.toString())){
            double signal215DValue = divide(signal215DObj.toString(),10,1);
            Double signal215D = new BigDecimal(signal215DValue).subtract(new BigDecimal(1000)).doubleValue();
//            System.out.println("信号：215D offset:1000 scale: 10 转换前："+signal215DObj.toString()+" 转换后："+signal215D);
            return signal215D;
        }
        return signal215DObj;
    }

}
