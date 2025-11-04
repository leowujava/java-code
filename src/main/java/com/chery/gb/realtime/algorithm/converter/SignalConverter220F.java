package com.chery.gb.realtime.algorithm.converter;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wugaoyang
 * @date 2025/7/14 星期一
 */
public class SignalConverter220F extends SignalBaseConverter {
    @Override
    public Object convert(Object signal220FObj) {
        //可充电储能子系统各温度探针检测到的温度值 0x220F offset: 40
        if(Objects.nonNull(signal220FObj) && StrUtil.isNotBlank(signal220FObj.toString())){
            List<Integer> dataArr = JSON.parseArray(signal220FObj.toString(), Integer.class);
            List<Integer> datas = new ArrayList<>();
            for (Integer data : dataArr) {
                int dataValue = getOffset(data.toString(), 40).intValue();
                datas.add(dataValue);
            }
            //System.out.println("信号：220F offset: 40 转换前："+JSON.toJSONString(dataArr)+" 转换后："+JSON.toJSONString(datas));
            return datas;
        }
        return signal220FObj;
    }

}
