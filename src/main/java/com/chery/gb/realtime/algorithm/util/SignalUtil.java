package com.chery.gb.realtime.algorithm.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.chery.gb.realtime.algorithm.converter.SignalBaseConverter;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 信号偏移量转换
 *
 * @author zhonghua
 * @date 2025/6/12
 */
public class SignalUtil {

    public static void process(Map<String, Object> tboxDataMap) {
        //仪表车速 0x2001 scale: 10
        Object signal2001Obj = tboxDataMap.get("2001");
        if (Objects.nonNull(signal2001Obj) && StrUtil.isNotBlank(signal2001Obj.toString())) {
            double signal2001 = getScale(signal2001Obj.toString(), 10, 1).intValue();
            tboxDataMap.put("2001", signal2001);
            //System.out.println("信号：2001 scale: 10 转换前："+signal2001Obj.toString()+" 转换后："+signal2001);
        }

        //总电压 0x215C scale: 10
        Object signal215CObj = tboxDataMap.get("215C");
        if (Objects.nonNull(signal215CObj) && StrUtil.isNotBlank(signal215CObj.toString())) {
            double signal215C = getScale(signal215CObj.toString(), 10, 1);
            tboxDataMap.put("215C", signal215C);
            //System.out.println("信号：215C scale: 10 转换前："+signal215CObj.toString()+" 转换后："+signal215C);
        }

        //总电流 0x215D offset:1000 scale: 10
        Object signal215DObj = tboxDataMap.get("215D");
        if (Objects.nonNull(signal215DObj) && StrUtil.isNotBlank(signal215DObj.toString())) {
            double signal215DValue = getScale(signal215DObj.toString(), 10, 1);
            Double signal215D = new BigDecimal(signal215DValue).subtract(new BigDecimal(1000)).doubleValue();
            tboxDataMap.put("215D", signal215D);
//            System.out.println("信号：215D offset:1000 scale: 10 转换前："+signal215DObj.toString()+" 转换后："+signal215D);
        }

        //燃料消耗率 220D scale: 100
        Object signal220DObj = tboxDataMap.get("220D");
        if (Objects.nonNull(signal220DObj) && StrUtil.isNotBlank(signal220DObj.toString())) {
            double signal220D = getScale(signal220DObj.toString(), 100, 2);
            tboxDataMap.put("220D", signal220D);
            //System.out.println("信号：220D scale: 100 转换前："+signal220DObj.toString()+" 转换后："+signal220D);
        }

        //电池单体电压最高值 0x2147 scale: 1000
        Object signal2147Obj = tboxDataMap.get("2147");
        if (Objects.nonNull(signal2147Obj) && StrUtil.isNotBlank(signal2147Obj.toString())) {
            double signal2147 = getScale(signal2147Obj.toString(), 1000, 3);
            tboxDataMap.put("2147", signal2147);
            //System.out.println("信号：2147 scale: 1000 转换前："+signal2147Obj.toString()+" 转换后："+signal2147);
        }

        //电池单体电压最低值 0x2149 scale: 1000
        Object signal2149Obj = tboxDataMap.get("2149");
        if (Objects.nonNull(signal2149Obj) && StrUtil.isNotBlank(signal2149Obj.toString())) {
            double signal2149 = getScale(signal2149Obj.toString(), 1000, 3);
            tboxDataMap.put("2149", signal2149);
            //System.out.println("信号：2149 scale: 1000 转换前："+signal2149Obj.toString()+" 转换后："+signal2149);
        }

        //最高温度值 0x2143 offset: 40
        Object signal2143Obj = tboxDataMap.get("2143");
        if (Objects.nonNull(signal2143Obj) && StrUtil.isNotBlank(signal2143Obj.toString())) {
            int signal2143 = getOffset(signal2143Obj.toString(), 40).intValue();
            tboxDataMap.put("2143", signal2143);
            //System.out.println("信号：2143 offset: 40 转换前："+signal2143Obj.toString()+" 转换后："+signal2143);
        }

        //最低温度值 0x2145 offset: 40
        Object signal2145Obj = tboxDataMap.get("2145");
        if (Objects.nonNull(signal2145Obj) && StrUtil.isNotBlank(signal2145Obj.toString())) {
            int signal2145 = getOffset(signal2145Obj.toString(), 40).intValue();
            tboxDataMap.put("2145", signal2145);
            //System.out.println("信号：2145 offset: 40 转换前："+signal2145Obj.toString()+" 转换后："+signal2145);
        }

        //GPS经度 0x207A scale: 1000000 offset: 180
        Object signal207AObj = tboxDataMap.get("207A");
        if (Objects.nonNull(signal207AObj) && StrUtil.isNotBlank(signal207AObj.toString())) {
            Double signal207AValue = getScale(signal207AObj.toString(), 1000000, 6);
            Double signal207A = getOffset(signal207AValue.toString(), 180);
            tboxDataMap.put("207A", signal207A);
            //System.out.println("信号：207A scale: 1000000 offset: 180 转换前："+signal207AObj.toString()+" 转换后："+signal207A);
        }

        //GPS纬度 0x207B scale: 1000000 offset: 90
        Object signal207BObj = tboxDataMap.get("207B");
        if (Objects.nonNull(signal207BObj) && StrUtil.isNotBlank(signal207BObj.toString())) {
            Double signal207BValue = getScale(signal207BObj.toString(), 1000000, 6);
            Double signal207B = getOffset(signal207BValue.toString(), 90);
            tboxDataMap.put("207B", signal207B);
            //System.out.println("信号：207B scale: 1000000 offset: 90 转换前："+signal207BObj.toString()+" 转换后："+signal207B);
        }

        //驱动电机控制器温度 0x21CF offset: 40
        Object signal21CFObj = tboxDataMap.get("21CF");
        if (Objects.nonNull(signal21CFObj) && StrUtil.isNotBlank(signal21CFObj.toString())) {
            int signal21CF = getOffset(signal21CFObj.toString(), 40).intValue();
            tboxDataMap.put("21CF", signal21CF);
            //System.out.println("信号：21CF offset: 40 转换前："+signal21CFObj.toString()+" 转换后："+signal21CF);
        }

        //驱动电机转速 0x21D0 offset: 20000
        Object signal21D0Obj = tboxDataMap.get("21D0");
        if (Objects.nonNull(signal21D0Obj) && StrUtil.isNotBlank(signal21D0Obj.toString())) {
            int signal21D0 = getOffset(signal21D0Obj.toString(), 20000).intValue();
            tboxDataMap.put("21D0", signal21D0);
            //System.out.println("信号：21D0 offset: 20000 转换前："+signal21D0Obj.toString()+" 转换后："+signal21D0);
        }

        //驱动电机转矩 0x21D1 offset: 20000 scale: 10
        Object signal21D1Obj = tboxDataMap.get("21D1");
        if (Objects.nonNull(signal21D1Obj) && StrUtil.isNotBlank(signal21D1Obj.toString())) {
            Double signal21D1Value = getScale(signal21D1Obj.toString(), 10, 1);
            Double signal21D1 = getOffset(signal21D1Value.toString(), 20000);
            tboxDataMap.put("21D1", signal21D1);
            //System.out.println("信号：21D1 offset: 20000 scale: 10 转换前："+signal21D1Obj.toString()+" 转换后："+signal21D1);
        }

        //驱动电机温度 0x21D2 offset: 40
        Object signal21D2Obj = tboxDataMap.get("21D2");
        if (Objects.nonNull(signal21D2Obj) && StrUtil.isNotBlank(signal21D2Obj.toString())) {
            int signal21D2 = getOffset(signal21D2Obj.toString(), 40).intValue();
            tboxDataMap.put("21D2", signal21D2);
            //System.out.println("信号：21D2 offset: 40 转换前："+signal21D2Obj.toString()+" 转换后："+signal21D2);
        }

        //电机控制器输入电压 0x21D3 scale: 10
        Object signal21D3Obj = tboxDataMap.get("21D3");
        if (Objects.nonNull(signal21D3Obj) && StrUtil.isNotBlank(signal21D3Obj.toString())) {
            Double signal21D3 = getScale(signal21D3Obj.toString(), 10, 1);
            tboxDataMap.put("21D3", signal21D3);
            //System.out.println("信号：21D3 scale: 10 转换前："+signal21D3Obj.toString()+" 转换后："+signal21D3);
        }

        //电机控制器直流母线电流 0x21D4 offset: 1000 scale: 10
        Object signal21D4Obj = tboxDataMap.get("21D4");
        if (Objects.nonNull(signal21D4Obj) && StrUtil.isNotBlank(signal21D4Obj.toString())) {
            Double signal21D4Value = getScale(signal21D4Obj.toString(), 10, 1);
            Double signal21D4 = getOffset(signal21D4Value.toString(), 1000);
            tboxDataMap.put("21D4", signal21D4);
            //System.out.println("信号：21D4 offset: 1000 scale: 10 转换前："+signal21D4Obj.toString()+" 转换后："+signal21D4);
        }

        //单体电池电压 0x220E scale: 1000
        Object signal220EObj = tboxDataMap.get("220E");
        if (Objects.nonNull(signal220EObj) && StrUtil.isNotBlank(signal220EObj.toString())) {
            List<Integer> dataArr = JSON.parseArray(signal220EObj.toString(), Integer.class);
            List<Double> datas = new ArrayList<>();
            for (Integer data : dataArr) {
                Double dataValue = getScale(data.toString(), 1000, 3);
                datas.add(dataValue);
            }
            tboxDataMap.put("220E", datas);
            //System.out.println("信号：220E scale: 1000 转换前："+JSON.toJSONString(dataArr)+" 转换后："+JSON.toJSONString(datas));
        }

        //可充电储能子系统各温度探针检测到的温度值 0x220F offset: 40
        Object signal220FObj = tboxDataMap.get("220F");
        if (Objects.nonNull(signal220FObj) && StrUtil.isNotBlank(signal220FObj.toString())) {
            List<Integer> dataArr = JSON.parseArray(signal220FObj.toString(), Integer.class);
            List<Integer> datas = new ArrayList<>();
            for (Integer data : dataArr) {
                int dataValue = getOffset(data.toString(), 40).intValue();
                datas.add(dataValue);
            }
            tboxDataMap.put("220F", datas);
            //System.out.println("信号：220F offset: 40 转换前："+JSON.toJSONString(dataArr)+" 转换后："+JSON.toJSONString(datas));
        }
    }


    private static Double getOffset(String value, Integer offset) {
        return new BigDecimal(value).subtract(new BigDecimal(offset)).doubleValue();
    }

    private static Double getScale(String value, Integer scale, Integer point) {
        return new BigDecimal(value).divide(new BigDecimal(scale), point, RoundingMode.HALF_DOWN).doubleValue();
    }

    /**
     * 转换偏移量
     *
     * @param tboxDataMap
     */
    public static void convert(Map<String, Object> tboxDataMap) {
        for (SignalEnum value : SignalEnum.values()) {
            SignalBaseConverter converter = value.getConverter();
            if (converter != null) {
                String code = value.getCode();
                Object o = tboxDataMap.get(code);
                if (Objects.nonNull(o)) {
                    tboxDataMap.put(code, converter.convert(o));
                } else {
                    tboxDataMap.entrySet().forEach(entry -> {
                        Object value1 = entry.getValue();
                        if (Objects.nonNull(value1) && value1 instanceof JSONArray) {
                            for (Object object : (JSONArray) value1) {
                                if (object instanceof JSONObject) {
                                    JSONObject object1 = (JSONObject) object;
                                    for (String key : object1.keySet()) {
                                        if (key.equals(code)) {
                                            object1.put(key, converter.convert(object1.get(key)));
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }

}
