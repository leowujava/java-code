package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
public class SignalConverter220D extends SignalBaseConverter {
    @Override
    public Object convert(Object signal220DObj) {
        //燃料消耗率 220D scale: 100
        if(Objects.nonNull(signal220DObj) && StrUtil.isNotBlank(signal220DObj.toString())){
            double signal220D = divide(signal220DObj.toString(),100,2);
            //System.out.println("信号：220D scale: 100 转换前："+signal220DObj.toString()+" 转换后："+signal220D);
            return signal220D;
        }
        return signal220DObj;
    }

}
