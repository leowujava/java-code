package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
public class SignalConverter215C extends SignalBaseConverter {
    @Override
    public Object convert(Object signal215CObj) {
        //总电压 0x215C scale: 10
        if (Objects.nonNull(signal215CObj) && StrUtil.isNotBlank(signal215CObj.toString()) && !Objects.equals("65534", signal215CObj.toString()) && !Objects.equals("65535", signal215CObj.toString())) {
            double signal215C = divide(signal215CObj.toString(), 10, 1);
            //System.out.println("信号：215C scale: 10 转换前："+signal215CObj.toString()+" 转换后："+signal215C);
            return signal215C;
        }
        return signal215CObj;
    }

}
