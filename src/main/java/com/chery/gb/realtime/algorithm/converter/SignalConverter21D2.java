package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_21D2)
public class SignalConverter21D2 extends BaseSignalConverter {
    @Override
    public Object convert(Object signal21D2Obj) {
        //驱动电机温度 0x21D2 offset: 40
        if(Objects.nonNull(signal21D2Obj) && StrUtil.isNotBlank(signal21D2Obj.toString()) && !Objects.equals("254", signal21D2Obj.toString()) && !Objects.equals("255", signal21D2Obj.toString())){
            int signal21D2 = getOffset(signal21D2Obj.toString(),40).intValue();
            //System.out.println("信号：21D2 offset: 40 转换前："+signal21D2Obj.toString()+" 转换后："+signal21D2);
            return signal21D2;
        }
        return signal21D2Obj;
    }

}
