package com.chery.gb.realtime.algorithm.signal.converter;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_21D4)
public class SignalConverter21D4 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal21D4Obj) {
        //电机控制器直流母线电流 0x21D4 offset: 1000 scale: 10
        if(Objects.nonNull(signal21D4Obj) && StrUtil.isNotBlank(signal21D4Obj.toString()) && !Objects.equals("65534", signal21D4Obj.toString()) && !Objects.equals("65535", signal21D4Obj.toString())){
            Double signal21D4Value = divide(signal21D4Obj.toString(),10,1);
            Double signal21D4 = getOffset(signal21D4Value.toString(),1000);
            //System.out.println("信号：21D4 offset: 1000 scale: 10 转换前："+signal21D4Obj.toString()+" 转换后："+signal21D4);
            return signal21D4;
        }
        return signal21D4Obj;
    }

}
