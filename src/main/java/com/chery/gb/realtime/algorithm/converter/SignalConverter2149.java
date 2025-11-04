package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
public class SignalConverter2149 extends SignalBaseConverter {
    @Override
    public Object convert(Object signal2149Obj) {
        //电池单体电压最低值 0x2149 scale: 1000
        if (Objects.nonNull(signal2149Obj) && StrUtil.isNotBlank(signal2149Obj.toString()) && !Objects.equals("65534", signal2149Obj.toString()) && !Objects.equals("65535", signal2149Obj.toString())) {
            double signal2149 = divide(signal2149Obj.toString(), 1000, 3);
            //System.out.println("信号：2149 scale: 1000 转换前："+signal2149Obj.toString()+" 转换后："+signal2149);
            return signal2149;
        }

        return signal2149Obj;
    }

}
