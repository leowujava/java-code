package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_21D3)
public class SignalConverter21D3 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal21D3Obj) {
        //电机控制器输入电压 0x21D3 scale: 10
        if(Objects.nonNull(signal21D3Obj) && StrUtil.isNotBlank(signal21D3Obj.toString()) && !Objects.equals("65534", signal21D3Obj.toString()) && !Objects.equals("65535", signal21D3Obj.toString())){
            Double signal21D3 = divide(signal21D3Obj.toString(),10,1);
            //System.out.println("信号：21D3 scale: 10 转换前："+signal21D3Obj.toString()+" 转换后："+signal21D3);
            return signal21D3;
        }
        return signal21D3Obj;
    }

}
