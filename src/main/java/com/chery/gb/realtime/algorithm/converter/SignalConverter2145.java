package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
public class SignalConverter2145 extends SignalBaseConverter {
    @Override
    public Object convert(Object signal2145Obj) {
        //最低温度值 0x2145 offset: 40
        if(Objects.nonNull(signal2145Obj) && StrUtil.isNotBlank(signal2145Obj.toString())&& !Objects.equals("254", signal2145Obj.toString()) && !Objects.equals("255", signal2145Obj.toString())){
            int signal2145 = getOffset(signal2145Obj.toString(),40).intValue();
            //System.out.println("信号：2145 offset: 40 转换前："+signal2145Obj.toString()+" 转换后："+signal2145);
            return signal2145;
        }
        return signal2145Obj;
    }

}
