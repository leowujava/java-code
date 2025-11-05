package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.chery.gb.realtime.algorithm.anotation.SignalConverter;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
@SignalConverter(SignalEnum.SIGNAL_220E)
public class SignalConverter220E extends BaseSignalConverter {
    @Override
    public Object convert(Object signal220EObj) {
        //单体电池电压 0x220E scale: 1000
        if(Objects.nonNull(signal220EObj) && StrUtil.isNotBlank(signal220EObj.toString())){
            List<Integer> dataArr = JSON.parseArray(signal220EObj.toString(), Integer.class);
            List<Double> datas = new ArrayList<>();
            for (Integer data : dataArr) {
                if(Objects.isNull(data) || data == 65535){
                    datas.add(Double.valueOf(data));
                    continue;
                }
                Double dataValue = divide(data.toString(),1000,3);
                datas.add(dataValue);
            }
            //System.out.println("信号：220E scale: 1000 转换前："+JSON.toJSONString(dataArr)+" 转换后："+JSON.toJSONString(datas));
            return datas;
        }
        return signal220EObj;
    }

}
