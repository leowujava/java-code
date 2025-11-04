package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
public class SignalConverter2001 extends SignalBaseConverter {
    @Override
    public Object convert(Object signal2001Obj) {
        //仪表车速 0x2001 scale: 10
        if(Objects.nonNull(signal2001Obj) && StrUtil.isNotBlank(signal2001Obj.toString()) && !Objects.equals("65534", signal2001Obj.toString()) && !Objects.equals("65535", signal2001Obj.toString())){
            return divide(signal2001Obj.toString(),10,1).intValue();
            //System.out.println("信号：2001 scale: 10 转换前："+signal2001Obj.toString()+" 转换后："+signal2001);
        }
        return signal2001Obj;
    }

}
