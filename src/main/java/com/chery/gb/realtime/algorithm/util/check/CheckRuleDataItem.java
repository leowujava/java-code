package com.chery.gb.realtime.algorithm.util.check;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.chery.gb.realtime.algorithm.signal.converter.SignalConverter207A;
import com.chery.gb.realtime.algorithm.signal.converter.SignalConverter207B;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.enums.SignalGroupEnum;
import com.chery.gb.realtime.algorithm.util.CommonDataUtil;
import com.chery.gb.realtime.algorithm.util.RetDataUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhonghua
 * @date 2025/6/17
 */
public class CheckRuleDataItem {

    /**
     * ruleCode: 93 最低温度子系统号大于实际上传的可充电温度电池包总数
     */
    public static void check93(Map<String, Object> signalMap, JSONArray retData) {
        try {
            //最低温度子系统号
            Object s20FB = signalMap.get("20FB");
            //可充电储能装置温度数据集合长度
            Object s1BC3 = signalMap.get("1BC3");

            if (Objects.isNull(s1BC3) || Objects.isNull(s20FB)) {
                return;
            }

            int v1BC3 = ((List) s1BC3).size();
            int v20FB = Integer.parseInt(s20FB.toString());
            if (v20FB > v1BC3) {
                Map<String, Object> gbValueMap = new HashMap<>();
                gbValueMap.put("20FB", v20FB);
                gbValueMap.put("1BC3", v1BC3);
                retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("93", gbValueMap))));
            }
        } catch (NumberFormatException e) {
            System.out.println("规则code: 93 ,异常:" + e);
        }
    }

    /**
     * ruleCode: 98 电池单体最低温度值与实际包内单体温度值不相等
     */
    public static void check98(Map<String, Object> signalMap, JSONArray retData) {
        String ruleCode = "98";
        try {
            //最低电压电池单体代号
            String signal2144 = SignalEnum.SIGNAL_2144.getCode();
            Object V2144 = signalMap.get(signal2144);
            String signal2145 = SignalEnum.SIGNAL_2145.getCode();
            Object V2145 = signalMap.get(signal2145);
            if (Objects.isNull(V2144) || Objects.isNull(V2145) || StrUtil.isBlank(V2144.toString()) || StrUtil.isBlank(V2145.toString())) {
                return;
            }

            String signal220F = SignalEnum.SIGNAL_220F.getCode();
            String signal2101 = SignalEnum.SIGNAL_2101.getCode();

            //可充电储能子系统各温度探针检测到的温度值的长度
            Object o = signalMap.get(SignalEnum.SIGNAL_1BC3.getCode());
            if (Objects.isNull(o) || StrUtil.isBlank(o.toString())) {
                Map<String, Object> gbValueMap = new HashMap<>();
                gbValueMap.put(signal2144, V2144);
                gbValueMap.put(signal2145, V2145);
                gbValueMap.put(signal220F, "");
                retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap(ruleCode, gbValueMap))));
            } else {
                if (o instanceof List) {
                    Map<String, Object> gbValueMap = new HashMap<>();
                    for (Object item : ((List<?>) o)) {
                        boolean flag = false;
                        JSONObject jsonObject = (JSONObject) item;
                        Object o1 = jsonObject.get(signal220F);
                        Object o2 = jsonObject.get(signal2101);
                        Double min = null;
                        if (Objects.isNull(o1) || StrUtil.isBlank(o1.toString()) || Objects.isNull(o2) || StrUtil.isBlank(o2.toString())) {
                            flag = true;
                        } else {
                            if (o1 instanceof List) {
                                List list = (List) o1;
                                int index = 0;
                                for (Object object : list) {
                                    Double d = Double.parseDouble(object.toString());
                                    if (min == null || d < min) {
                                        min = d;
                                    }
                                    index++;
                                }
                                Double v = Double.parseDouble(V2145.toString());
                                Double v1 = Double.parseDouble(o2.toString()) + index;
                                Double v2 = Double.parseDouble(V2144.toString());
                                flag = !v.equals(min) || !v1.equals(v2);
                            }
                        }
                        if (flag) {
                            gbValueMap.put(signal2144, V2144);
                            gbValueMap.put(signal2145, V2145);
                            gbValueMap.put(signal220F, o1);
                            gbValueMap.put(signal2101, o2);
                            gbValueMap.put("min", min);
                        }
                    }
                    if (CollectionUtil.isNotEmpty(gbValueMap)) {
                        retData.add(RetDataUtil.buildRetMap(ruleCode, gbValueMap));
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("规则code: " + ruleCode + " ,异常:" + e);
            e.printStackTrace();
        }
    }

    /**
     * ruleCode: 99 最低温度探针单体代号大于实际上传的探针长度
     */
    public static void check99(Map<String, Object> signalMap, JSONArray retData) {
        try {
            //最低温度探针子系统代号
            Object s2144 = signalMap.get("2144");
            if (Objects.isNull(s2144) || StrUtil.isBlank(s2144.toString())) {
                return;
            }
            int v2144 = Integer.parseInt(s2144.toString());
//            System.out.println("s2144 :" + s2144);
            Object o = signalMap.get("1BC3");
//            System.out.println("1BC3:" + o);
            if (!Objects.isNull(o) && StrUtil.isNotBlank(o.toString())) {
                if (o instanceof List) {
                    for (Object o1 : ((List<?>) o)) {
                        //可充电储能子系统各温度探针检测到的温度值的长度
                        Object s220F = ((JSONObject) o1).get("220F");
//                        System.out.println("220F :" + s220F);
                        boolean flag = false;
                        Map<String, Object> gbValueMap = new HashMap<>();
                        if (!Objects.isNull(s220F) && !Objects.isNull(s2144)) {
                            if (s220F instanceof List) {
                                List dataArr = (List) s220F;
                                flag = v2144 > dataArr.size();
                                if (flag) {
                                    gbValueMap.put("2144", v2144);
                                    gbValueMap.put("220F", dataArr.size());
                                }
                            }
                        }
                        if (flag) {
                            retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("99", gbValueMap))));
                        }
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("规则code: 99 ,异常:" + e);
        }
    }

    /**
     * code:113 实时数据时间与服务器时间相差超过 30 秒
     */
    public static void check113(Map<String, Object> signalMap, JSONArray retData) {
        try {
            Object st = signalMap.get("st");
            Object ct = signalMap.get("ct");
            Long timeDiff = Math.abs(Long.parseLong(st.toString()) - Long.parseLong(ct.toString()));
            if (timeDiff > 30000) {
                Map<String, Object> gbValueMap = new HashMap<>();
                gbValueMap.put("st", st);
                gbValueMap.put("ct", ct);
                gbValueMap.put("timeDiff", timeDiff);
                retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("113", gbValueMap))));
            }
        } catch (NumberFormatException e) {
            System.out.println("规则code: 13 ,异常:" + e);
        }
    }

    /**
     * code:121 单体电压精度不满足 0.001V
     */
    public static void check121(Map<String, Object> signalMap, JSONArray retData) {
        try {
            //单体电池电压
            Object o = signalMap.get(SignalEnum.SIGNAL_1BC2.getCode());
            if (Objects.isNull(o) || StrUtil.isBlank(o.toString())) {
                return;
            }
            if (o instanceof List) {
                boolean bool = false;
                double value = 0.00;
                for (Object o1 : (List<?>) o) {
                    SignalEnum signal220e = SignalEnum.SIGNAL_220E;
                    Object s220E = ((JSONObject) o1).get(signal220e.getCode());
                    if (Objects.isNull(s220E)) {
                        continue;
                    }
                    if (s220E instanceof List) {
                        List dataArr = (List) s220E;
                        for (Object data : dataArr) {
                            int vData = getDecimalPlaces(Double.parseDouble(data.toString()));
                            if (vData < 3) {
                                bool = true;
                                value = vData;
                                break;
                            }
                        }
                    }
                }
                if (bool) {
                    Map<String, Object> gbValueMap = new HashMap<>();
                    gbValueMap.put("220E", value);
                    retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("121", gbValueMap))));
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("规则code: 121 ,异常:" + e);
        }
    }

    /**
     * code:126 最高报警等级≠通用报警故障等级列表中的最高通用报警故障等级
     */
    public static void check126(Map<String, Object> signalMap, JSONArray retData) {
        //最高报警等级
        String code = SignalEnum.SIGNAL_220B.getCode();
        Object s220B = signalMap.get(code);
        if (Objects.isNull(s220B)) {
            return;
        }
        Integer maxAlert = Integer.parseInt(s220B.toString());
        if (maxAlert == 1 || maxAlert == 2 || maxAlert == 3) {
            Object o = signalMap.get(SignalEnum.SIGNAL_PUBLICALARMCODE.getCode());
            if (Objects.isNull(o) || StrUtil.isBlank(o.toString())) {
                return;
            }
            if (Objects.equals("00000000", o)) {
                Map<String, Object> gbValueMap = new HashMap<>();
                gbValueMap.put("publicAlarmCode", o);
                retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("126", gbValueMap))));
            }
        }

    }

    /**
     * code:269 实时数据时间与服务器时间相差超过 180 秒
     */
    public static void check269(Map<String, Object> signalMap, JSONArray retData) {
        Object st = signalMap.get("st");
        Object ct = signalMap.get("ct");
        Long timeDiff = Math.abs(Long.parseLong(st.toString()) - Long.parseLong(ct.toString()));
        if (timeDiff > 180000) {
            Map<String, Object> gbValueMap = new HashMap<>();
            gbValueMap.put("st", st);
            gbValueMap.put("ct", ct);
            gbValueMap.put("timeDiff", timeDiff);
            retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("269", gbValueMap))));

        }
    }

    /**
     * code:279 燃油模式驱动电机列表不为空
     */
    public static void check279(Map<String, Object> signalMap, JSONArray retData) {
        Object s1BC1 = signalMap.get("1BC1");
        if (s1BC1 == null && StrUtil.isBlank(s1BC1.toString()) || ((List) s1BC1).size() == 0) {
            return;
        }
        Map<String, Object> gbValueMap = new HashMap<>();
        gbValueMap.put("1BC1", s1BC1);
        retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("279", gbValueMap))));
    }

    /**
     * code:281 经度精确度不足,精确度不足百万分之一度
     */
    public static void check281(Map<String, Object> signalMap, JSONArray retData) {
        Object s207A = signalMap.get("207A");
        if (Objects.isNull(s207A) || StrUtil.isBlank(s207A.toString())) {
            return;
        }
        Object convert = new SignalConverter207A().convert(s207A);
        double number = Double.parseDouble(convert.toString());
        int v207A = getDecimalPlaces(number);
        if (v207A < 5) {
            Map<String, Object> gbValueMap = new HashMap<>();
            gbValueMap.put("207A", convert);
            retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("281", gbValueMap))));
        }
    }

    /**
     * code:282 纬度精确度不足,精确度不足百万分之一度
     */
    public static void check282(Map<String, Object> signalMap, JSONArray retData) {
        try {
            Object s207B = signalMap.get("207B");
            if (Objects.isNull(s207B) || StrUtil.isBlank(s207B.toString())) {
                return;
            }
            Object convert = new SignalConverter207B().convert(s207B);
            int v207B = getDecimalPlaces(Double.parseDouble(convert.toString()));
            if (v207B < 5) {
                Map<String, Object> gbValueMap = new HashMap<>();
                gbValueMap.put("207B", convert);
                retData.add(JSONObject.parseObject(JSON.toJSONString(RetDataUtil.buildRetMap("282", gbValueMap))));
            }
        } catch (NumberFormatException e) {
            System.out.println("规则code: 282 ,异常:" + e);
            e.printStackTrace();
        }
    }

    /**
     * 检测整车数据 0x01
     */
    public static boolean check287(Map<String, Object> signalMap) {
        //车辆状态
        Object engineStatus = signalMap.get("220C");
        //充电状态
        Object chargingStatus = signalMap.get("21AA");
        //车速
        Object speed = signalMap.get("2001");
        //公里里程（总里程）
        Object mileage = signalMap.get("2009");
        //总电压
        Object voltage = signalMap.get("215C");
        //总电流
        Object current = signalMap.get("215D");
        //soc
        Object soc = signalMap.get("2232");
        //DC-DC状态
        Object dcStatus = signalMap.get("2187");
        //档位
        Object gear = signalMap.get("2076");
        //是否有驱动力
        Object hasDriveForce = signalMap.get("219E");
        //是否有制动力
        Object hasBrakingForce = signalMap.get("219D");
        //绝缘电阻
        Object insulationResistance = signalMap.get("2160");
        //加速踏板行程值
        Object accelerationPedalTravel = signalMap.get("21B1");
        //制动踏板状态
        Object brakePedalState = signalMap.get("21B2");

        boolean signal220C = engineStatus != null && StrUtil.isNotBlank(engineStatus.toString());
        boolean signal21AA = chargingStatus != null && StrUtil.isNotBlank(chargingStatus.toString());
        boolean signal2001 = speed != null && StrUtil.isNotBlank(speed.toString());
        boolean signal2009 = mileage != null && StrUtil.isNotBlank(mileage.toString());
        boolean signal215C = voltage != null && StrUtil.isNotBlank(voltage.toString());
        boolean signal215D = current != null && StrUtil.isNotBlank(current.toString());
        boolean signal2232 = soc != null && StrUtil.isNotBlank(soc.toString());
        boolean signal2187 = dcStatus != null && StrUtil.isNotBlank(dcStatus.toString());
        boolean signal2076 = gear != null && StrUtil.isNotBlank(gear.toString());
        boolean signal219E = hasDriveForce != null && StrUtil.isNotBlank(hasDriveForce.toString());
        boolean signal219D = hasBrakingForce != null && StrUtil.isNotBlank(hasBrakingForce.toString());
        boolean signal2160 = insulationResistance != null && StrUtil.isNotBlank(insulationResistance.toString());
        boolean signal21B1 = accelerationPedalTravel != null && StrUtil.isNotBlank(accelerationPedalTravel.toString());
        boolean signal21B2 = brakePedalState != null && StrUtil.isNotBlank(brakePedalState.toString());

        return signal220C || signal21AA || signal2001 || signal2009 ||
                signal215C || signal215D || signal2232 || signal2187 ||
                signal2076 || signal219E || signal219D || signal2160 ||
                signal21B1 || signal21B2;
    }

    /**
     * 检测车辆位置 0x05
     */
    public static boolean check288(Map<String, Object> signalMap) {
        //GPS状态,位置有效性 0有效定位；1无效定位
        Object gpsState = signalMap.get("207F");
        //定位状态经度
        Object latState = signalMap.get("2278");
        //定位状态纬度
        Object lngState = signalMap.get("2277");
        //经度
        Object longitude = signalMap.get("207A");
        //纬度
        Object latitude = signalMap.get("207B");

        boolean signal207F = gpsState != null && StrUtil.isNotBlank(gpsState.toString());
        boolean signal2278 = latState != null && StrUtil.isNotBlank(latState.toString());
        boolean signal2277 = lngState != null && StrUtil.isNotBlank(lngState.toString());
        boolean signal207A = longitude != null && StrUtil.isNotBlank(longitude.toString());
        boolean signal207B = latitude != null && StrUtil.isNotBlank(latitude.toString());
        return signal207F || signal2278 || signal2277 || signal207A || signal207B;
    }

    /**
     * 检测极值数据 0x06
     */
    public static boolean check289(Map<String, Object> signalMap) {
        //最高电压电池子系统号
        Object signal_20FC = signalMap.get("20FC");
        //最高电压电池单体代号
        Object signal_2146 = signalMap.get("2146");
        //电池单体电压最高值
        Object signal_2147 = signalMap.get("2147");
        //最低电压电池子系统号
        Object signal_20FD = signalMap.get("20FD");
        //最低电压电池单体代号
        Object signal_2148 = signalMap.get("2148");
        //电池单体电压最低值
        Object signal_2149 = signalMap.get("2149");
        //最高温度子系统号
        Object signal_20FA = signalMap.get("20FA");
        //最高温度探针单体代号
        Object signal_2142 = signalMap.get("2142");
        //最高温度值
        Object signal_2143 = signalMap.get("2143");
        //最低温度子系统号
        Object signal_20FB = signalMap.get("20FB");
        //最低温度探针子系统代号
        Object signal_2144 = signalMap.get("2144");
        //最低温度值
        Object signal_2145 = signalMap.get("2145");

        boolean signal20FC = signal_20FC != null && StrUtil.isNotBlank(signal_20FC.toString());
        boolean signal2146 = signal_2146 != null && StrUtil.isNotBlank(signal_2146.toString());
        boolean signal2147 = signal_2147 != null && StrUtil.isNotBlank(signal_2147.toString());
        boolean signal20FD = signal_20FD != null && StrUtil.isNotBlank(signal_20FD.toString());
        boolean signal2148 = signal_2148 != null && StrUtil.isNotBlank(signal_2148.toString());
        boolean signal2149 = signal_2149 != null && StrUtil.isNotBlank(signal_2149.toString());
        boolean signal20FA = signal_20FA != null && StrUtil.isNotBlank(signal_20FA.toString());
        boolean signal2142 = signal_2142 != null && StrUtil.isNotBlank(signal_2142.toString());
        boolean signal2143 = signal_2143 != null && StrUtil.isNotBlank(signal_2143.toString());
        boolean signal20FB = signal_20FB != null && StrUtil.isNotBlank(signal_20FB.toString());
        boolean signal2144 = signal_2144 != null && StrUtil.isNotBlank(signal_2144.toString());
        boolean signal2145 = signal_2145 != null && StrUtil.isNotBlank(signal_2145.toString());

        return signal20FC || signal2146 || signal2147 || signal20FD
                || signal2148 || signal2149 || signal20FA || signal2142
                || signal2143 || signal20FB || signal2144 || signal2145;
    }

    /**
     * 检测告警数据 0x07
     */
    public static boolean check290(Map<String, Object> signalMap) {
        //最高报警等级
        Object signal_220B = signalMap.get("220B");
        //车载储能装置类型过充
        Object signal_2157 = signalMap.get("2157");
        //驱动电机温度报警
        Object signal_2209 = signalMap.get("2209");
        //高压互锁状态报警
        Object signal_2156 = signalMap.get("2156");

        //驱动电机控制器温度报警
        Object signal_2208 = signalMap.get("2208");
        //DC-DC状态报警
        Object signal_2189 = signalMap.get("2189");
        //制动系统报警
        Object signal_21C2 = signalMap.get("21C2");
        //DC-DC温度报警
        Object signal_2188 = signalMap.get("2188");

        //绝缘报警
        Object signal_2155 = signalMap.get("2155");
        //电池单体一致性差报警
        Object signal_2154 = signalMap.get("2154");
        //可充电储能系统不匹配报警
        Object signal_2153 = signalMap.get("2153");
        //SOC跳变报警
        Object signal_2152 = signalMap.get("2152");

        //SOC过高报警
        Object signal_2151 = signalMap.get("2151");
        //单体电池欠压报警
        Object signal_2150 = signalMap.get("2150");
        //单体电池过压报警
        Object signal_214F = signalMap.get("214F");
        //SOC低报警
        Object signal_214E = signalMap.get("214E");

        //车载储能装置类型欠压报警
        Object signal_214D = signalMap.get("214D");
        //车载储能装置类型过压报警
        Object signal_214C = signalMap.get("214C");
        //电池高温报警
        Object signal_214B = signalMap.get("214B");
        //温度差异报警
        Object signal_214A = signalMap.get("214A");

        boolean signal220B = signal_220B != null && StrUtil.isNotBlank(signal_220B.toString());
        boolean signal2157 = signal_2157 != null && StrUtil.isNotBlank(signal_2157.toString());
        boolean signal2209 = signal_2209 != null && StrUtil.isNotBlank(signal_2209.toString());
        boolean signal2156 = signal_2156 != null && StrUtil.isNotBlank(signal_2156.toString());
        boolean signal2208 = signal_2208 != null && StrUtil.isNotBlank(signal_2208.toString());
        boolean signal2189 = signal_2189 != null && StrUtil.isNotBlank(signal_2189.toString());
        boolean signal21C2 = signal_21C2 != null && StrUtil.isNotBlank(signal_21C2.toString());
        boolean signal2188 = signal_2188 != null && StrUtil.isNotBlank(signal_2188.toString());
        boolean signal2155 = signal_2155 != null && StrUtil.isNotBlank(signal_2155.toString());
        boolean signal2154 = signal_2154 != null && StrUtil.isNotBlank(signal_2154.toString());
        boolean signal2153 = signal_2153 != null && StrUtil.isNotBlank(signal_2153.toString());
        boolean signal2152 = signal_2152 != null && StrUtil.isNotBlank(signal_2152.toString());
        boolean signal2151 = signal_2151 != null && StrUtil.isNotBlank(signal_2151.toString());
        boolean signal2150 = signal_2150 != null && StrUtil.isNotBlank(signal_2150.toString());
        boolean signal214F = signal_214F != null && StrUtil.isNotBlank(signal_214F.toString());
        boolean signal214E = signal_214E != null && StrUtil.isNotBlank(signal_214E.toString());
        boolean signal214D = signal_214D != null && StrUtil.isNotBlank(signal_214D.toString());
        boolean signal214C = signal_214C != null && StrUtil.isNotBlank(signal_214C.toString());
        boolean signal214B = signal_214B != null && StrUtil.isNotBlank(signal_214B.toString());
        boolean signal214A = signal_214A != null && StrUtil.isNotBlank(signal_214A.toString());

        return signal220B || signal2157 || signal2209 || signal2156
                || signal2208 || signal2189 || signal21C2 || signal2188
                || signal2155 || signal2154 || signal2153 || signal2152
                || signal2151 || signal2150 || signal214F || signal214E
                || signal214D || signal214C || signal214B || signal214A;
    }

    /**
     * 获取小数点位数
     */
    public static int getDecimalPlaces(double number) {
        String str = Double.toString(number);
        if (str.contains(".")) {
            return str.split("\\.")[1].length();
        }
        return 0;
    }

    public static boolean checkByGroup(Map<String, Object> signalMap, SignalGroupEnum group) {
        List<SignalEnum> alertSignal = SignalEnum.getByGroup(group);
        boolean flag = false;
        for (SignalEnum alert : alertSignal) {
            Object o = signalMap.get(alert.getCode());
            flag = flag || (o != null && StrUtil.isNotBlank(o.toString()));
            if (flag) {
                break;
            }
        }
        return flag;
    }

    //检查车辆状态是否为空
    public static boolean check220C(Map<String, Object> signalMap, JSONObject result) {
        Object o = signalMap.get("220C");
        boolean flag = Objects.isNull(o) || StrUtil.isBlank(o.toString());
        if (flag) {
            return true;
        }

        return flag;
    }

    public static void checkCode2(Map<String, Object> signalMap, JSONArray retData) {
        String key = "2076";
        Object o = signalMap.get(key);
        boolean flag = Objects.isNull(o) || StrUtil.isBlank(o.toString());
        boolean flag2 = false;
        if (!flag) {
            double v = Double.parseDouble(o.toString());
            flag2 = v >= 33 && v <= 255;
        }
        if (flag || flag2) {
            JSONObject resultDetailData = new JSONObject();
            resultDetailData.put("ruleCode", 2);
            JSONObject gbValue = new JSONObject();
            gbValue.put(key, o);
            resultDetailData.put("gbValue", gbValue);
            retData.add(resultDetailData);
        }
    }

    public static void checkCode266(Map<String, Object> signalMap, JSONArray retData) {
        //判断是否为熄火
        Object o = signalMap.get("220C");
        if (Objects.isNull(o) || StrUtil.isBlank(o.toString())) {
            return;
        }
        Object o1 = signalMap.get("2009");
        if (Objects.isNull(o1) || StrUtil.isBlank(o1.toString())) {
            return;
        }
        String vin = signalMap.get("vin").toString();
        double value = Double.parseDouble(o1.toString());
        if (Integer.parseInt(o.toString()) == 2) {
            CommonDataUtil.totalMileageMap.put(vin, value);
        } else if (Integer.parseInt(o.toString()) == 1) {
            Double v = CommonDataUtil.totalMileageMap.get(vin);
            CommonDataUtil.totalMileageMap.remove(vin);
            if (v == null) {
                return;
            }
            if (value - v > 4) {
                JSONObject resultDetailData = new JSONObject();
                resultDetailData.put("ruleCode", 266);
                JSONObject gbValue = new JSONObject();
                gbValue.put("2009-1", value);
                gbValue.put("2009-2", v);
                resultDetailData.put("gbValue", gbValue);
                retData.add(resultDetailData);
            }
        }

    }

    public static void check66(Map<String, Object> signalMap, JSONArray retData) {
        String signal_2146Code = SignalEnum.SIGNAL_2146.getCode();
        Object o = signalMap.get(signal_2146Code);
        if (Objects.isNull(o) || StrUtil.isBlank(o.toString())) {
            return;
        }
        Integer signal_2146 = Integer.parseInt(o.toString());
        SignalEnum signal2141 = SignalEnum.SIGNAL_2141;
        SignalEnum signal1bc2 = SignalEnum.SIGNAL_1BC2;
        Object o1 = signalMap.get(signal1bc2.getCode());
        int count = 0;
        if (!Objects.isNull(o1) && StrUtil.isNotBlank(o1.toString())) {
            if (o1 instanceof List) {
                for (Object o2 : ((List<?>) o1)) {
                    Object o3 = ((JSONObject) o2).get(signal2141.getCode());
                    if (Objects.isNull(o3) || StrUtil.isBlank(o3.toString())) {
                        return;
                    }
                    Integer v = Integer.parseInt(o3.toString());
                    count += v;
                }
            }
        }
        if (signal_2146 != count) {
            JSONObject resultDetailData = new JSONObject();
            resultDetailData.put("ruleCode", 66);
            JSONObject gbValue = new JSONObject();
            gbValue.put(signal_2146Code, signal_2146);
            gbValue.put("2141", count);
            resultDetailData.put("gbValue", gbValue);
            retData.add(resultDetailData);
        }
    }

    public static void checkCode135(Map<String, Object> signalMap, JSONArray retData) {
        String vin = signalMap.get("vin").toString();
        Object ct = signalMap.get("ct");
        if (Objects.isNull(ct) || StrUtil.isBlank(ct.toString())) {
            return;
        }
        Map<String, Object> signalMap2 = CommonDataUtil.preData.get(vin);
        CommonDataUtil.preData.put(vin, signalMap);
        if (signalMap2 == null || StrUtil.isBlank(signalMap2.toString())) {
            return;
        }
        Object o1 = signalMap2.get("ct");
        long i1 = Long.parseLong(ct.toString());
        long i2 = Long.parseLong(o1.toString());
        if (i1 - i2 > 30 * 1000) {
            return;
        }
        Object mile1 = signalMap.get("2009");
        Object mile2 = signalMap2.get("2009");
        if (Objects.isNull(mile1) || Objects.isNull(mile2) || StrUtil.isBlank(mile1.toString()) || StrUtil.isBlank(mile2.toString())) {
            return;
        }
        Long m1 = Long.parseLong(mile1.toString());
        Long m2 = Long.parseLong(mile2.toString());
        if (m1 - m2 > 2) {
            JSONObject resultDetailData = new JSONObject();
            resultDetailData.put("ruleCode", "135");
            JSONObject gbValue = new JSONObject();
            gbValue.put("2009-1", m1);
            gbValue.put("2009-2", m2);
            resultDetailData.put("gbValue", gbValue);
            retData.add(resultDetailData);
        }
    }

    public static void check10(Map<String, Object> signalMap, JSONArray retData) {
        String signal21B1Code = SignalEnum.SIGNAL_21B1.getCode();
        Object o = signalMap.get(signal21B1Code);
        if (Objects.isNull(o) || StrUtil.isBlank(o.toString())) {
            return;
        }
        int i = Integer.parseInt(o.toString());
        if (i <= 0 || i >= 100) {
            return;
        }
        String signal219ECode = SignalEnum.SIGNAL_219E.getCode();
        Object o1 = signalMap.get(signal219ECode);
        boolean flag = false;
        if (Objects.isNull(o1) || StrUtil.isBlank(o1.toString())) {
            flag = true;
        } else if (Integer.parseInt(o1.toString()) == 0) {
            flag = true;
        }
        if (flag) {
            JSONObject resultDetailData = new JSONObject();
            resultDetailData.put("ruleCode", "10");
            JSONObject gbValue = new JSONObject();
            gbValue.put(signal21B1Code, o);
            gbValue.put(signal219ECode, o1);
            resultDetailData.put("gbValue", gbValue);
            retData.add(resultDetailData);
        }
    }

    public static void check13(Map<String, Object> signalMap, JSONArray retData) {
        String signal21B2Code = SignalEnum.SIGNAL_21B2.getCode();
        Object o = signalMap.get(signal21B2Code);
        if (Objects.isNull(o) || StrUtil.isBlank(o.toString())) {
            return;
        }
        int i = Integer.parseInt(o.toString());
        if (i <= 0 || i >= 100) {
            return;
        }
        String signal219DCode = SignalEnum.SIGNAL_219D.getCode();
        Object o1 = signalMap.get(signal219DCode);
        boolean flag = false;
        if (Objects.isNull(o1) || StrUtil.isBlank(o1.toString())) {
            flag = true;
        } else if (Integer.parseInt(o1.toString()) == 0) {
            flag = true;
        }
        if (flag) {
            JSONObject resultDetailData = new JSONObject();
            resultDetailData.put("ruleCode", "13");
            JSONObject gbValue = new JSONObject();
            gbValue.put(signal21B2Code, o);
            gbValue.put(signal219DCode, o1);
            resultDetailData.put("gbValue", gbValue);
            retData.add(resultDetailData);
        }

    }

    public static void check87(Map<String, Object> signalMap, JSONArray retData) {
        String ruleCode = "87";
        try {
            String signal21D2 = SignalEnum.SIGNAL_21D2.getCode();
            //可充电储能子系统各温度探针检测到的温度值的长度
            Object o = signalMap.get(SignalEnum.SIGNAL_1BC1.getCode());
            if (!Objects.isNull(o) && StrUtil.isNotBlank(o.toString())) {
                if (o instanceof List) {
                    Map<String, Object> gbValueMap = new HashMap<>();
                    for (Object item : ((List<?>) o)) {
                        Object o1 = ((JSONObject) item).get(signal21D2);
                        if (Objects.isNull(o1) || StrUtil.isBlank(o1.toString())) {
                            gbValueMap.put(signal21D2, o1);
                        }
                    }
                    if (CollectionUtil.isNotEmpty(gbValueMap)) {
                        retData.add(RetDataUtil.buildRetMap(ruleCode, gbValueMap));
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("规则code: " + ruleCode + " ,异常:" + e);
        }
    }

    public static void check57(Map<String, Object> signalMap, JSONArray retData) {
        String ruleCode = "57";
        try {
            //可充电储能子系统各温度探针检测到的温度值的长度
            Object o = signalMap.get(SignalEnum.SIGNAL_1BC1.getCode());
            boolean flag = false;
            if (!Objects.isNull(o) && StrUtil.isNotBlank(o.toString())) {
                if (o instanceof List) {
                    flag = ((List<?>) o).size() == 0;
                }
            } else {
                flag = true;
            }
            if (flag) {
                Map<String, Object> gbValueMap = new HashMap<>();
                gbValueMap.put(SignalEnum.SIGNAL_1BC1.getCode(), o);
                retData.add(RetDataUtil.buildRetMap(ruleCode, gbValueMap));
            }
        } catch (NumberFormatException e) {
            System.out.println("规则code: " + ruleCode + " ,异常:" + e);
        }
    }

    public static void check50(Map<String, Object> signalMap, JSONArray retData) {
        String ruleCode = "50";
        try {
            //可充电储能子系统各温度探针检测到的温度值的长度
            Object o1 = signalMap.get(SignalEnum.SIGNAL_21AA.getCode());
            Object o2 = signalMap.get(SignalEnum.SIGNAL_220C.getCode());
            Object o3 = signalMap.get(SignalEnum.SIGNAL_21AB.getCode());
            Object o4 = signalMap.get(SignalEnum.SIGNAL_2001.getCode());
            Object o5 = signalMap.get(SignalEnum.SIGNAL_215D.getCode());
            if (Objects.isNull(o1) || StrUtil.isBlank(o1.toString())
                    || Objects.isNull(o2) || StrUtil.isBlank(o2.toString())
                    || Objects.isNull(o3) || StrUtil.isBlank(o3.toString())
                    || Objects.isNull(o4) || StrUtil.isBlank(o4.toString())
                    || Objects.isNull(o5) || StrUtil.isBlank(o5.toString())
            ) {
                return;
            }
            boolean flag = false;
            if (Integer.parseInt(o1.toString()) != 1 && Integer.parseInt(o2.toString()) != 2 && Integer.parseInt(o3.toString()) == 1 && Integer.parseInt(o4.toString()) > 0) {
                double v = Double.parseDouble(o5.toString());
                flag = (v < 0) || Integer.parseInt(o1.toString()) == 1 || Integer.parseInt(o1.toString()) == 2;
            }
            if (flag) {
                Map<String, Object> gbValueMap = new HashMap<>();
                gbValueMap.put(SignalEnum.SIGNAL_21AA.getCode(), o1);
                gbValueMap.put(SignalEnum.SIGNAL_220C.getCode(), o2);
                gbValueMap.put(SignalEnum.SIGNAL_21AB.getCode(), o3);
                gbValueMap.put(SignalEnum.SIGNAL_2001.getCode(), o4);
                gbValueMap.put(SignalEnum.SIGNAL_215D.getCode(), o5);
                retData.add(RetDataUtil.buildRetMap(ruleCode, gbValueMap));
            }
        } catch (NumberFormatException e) {
            System.out.println("规则code: " + ruleCode + " ,异常:" + e);
        }
    }

    public static void check51(Map<String, Object> signalMap, JSONArray retData) {
//        String ruleCode = "51";
//        try {
//            //可充电储能子系统各温度探针检测到的温度值的长度
//            Object o1 = signalMap.get(SignalEnum.SIGNAL_21AA.getCode());
//            Object o2 = signalMap.get(SignalEnum.SIGNAL_220C.getCode());
//            Object o3 = signalMap.get(SignalEnum.SIGNAL_21AB.getCode());
//            Object o4 = signalMap.get(SignalEnum.SIGNAL_2001.getCode());
//            Object o5 = signalMap.get(SignalEnum.SIGNAL_215D.getCode());
//            if (Objects.isNull(o1) || StrUtil.isBlank(o1.toString())
//                    || Objects.isNull(o2) || StrUtil.isBlank(o2.toString())
//                    || Objects.isNull(o3) || StrUtil.isBlank(o3.toString())
//                    || Objects.isNull(o4) || StrUtil.isBlank(o4.toString())
//                    || Objects.isNull(o5) || StrUtil.isBlank(o5.toString())
//            ) {
//                return;
//            }
//            boolean flag = false;
//            if (Integer.parseInt(o1.toString()) != 1 && Integer.parseInt(o2.toString()) != 2 && Integer.parseInt(o3.toString()) == 1 && Integer.parseInt(o4.toString()) > 0) {
//                double v = Double.parseDouble(o5.toString());
//                flag = (v < 0) || Integer.parseInt(o1.toString()) == 1;
//            }
//            if (flag) {
//                Map<String, Object> gbValueMap = new HashMap<>();
//                gbValueMap.put(SignalEnum.SIGNAL_21AA.getCode(), o1);
//                gbValueMap.put(SignalEnum.SIGNAL_220C.getCode(), o2);
//                gbValueMap.put(SignalEnum.SIGNAL_21AB.getCode(), o3);
//                gbValueMap.put(SignalEnum.SIGNAL_2001.getCode(), o4);
//                gbValueMap.put(SignalEnum.SIGNAL_215D.getCode(), o5);
//                retData.add(RetDataUtil.buildRetMap(ruleCode, gbValueMap));
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("规则code: " + ruleCode + " ,异常:" + e);
//        }
    }

    public static void check25(Map<String, Object> signalMap, JSONArray retData) {
        String ruleCode = "25";
        try {
            boolean flag = false;
            Object o = signalMap.get(SignalEnum.SIGNAL_1BC1.getCode());
            if (Objects.isNull(o) || StrUtil.isBlank(o.toString())) {
                flag = true;
            } else {
                if (o instanceof List) {
                    for (Object object : ((List) o)) {
                        Object o1 = ((JSONObject) object).get(SignalEnum.SIGNAL_21CD.getCode());
                        Object o2 = ((JSONObject) object).get(SignalEnum.SIGNAL_21CF.getCode());
                        Object o3 = ((JSONObject) object).get(SignalEnum.SIGNAL_21D0.getCode());
                        Object o4 = ((JSONObject) object).get(SignalEnum.SIGNAL_21D1.getCode());
                        Object o5 = ((JSONObject) object).get(SignalEnum.SIGNAL_21D2.getCode());
                        Object o6 = ((JSONObject) object).get(SignalEnum.SIGNAL_21D3.getCode());
                        Object o7 = ((JSONObject) object).get(SignalEnum.SIGNAL_21D4.getCode());
                        if (Objects.isNull(o1) || StrUtil.isBlank(o1.toString())
                                || Objects.isNull(o2) || StrUtil.isBlank(o2.toString())
                                || Objects.isNull(o3) || StrUtil.isBlank(o3.toString())
                                || Objects.isNull(o4) || StrUtil.isBlank(o4.toString())
                                || Objects.isNull(o5) || StrUtil.isBlank(o5.toString())
                                || Objects.isNull(o6) || StrUtil.isBlank(o6.toString())
                                || Objects.isNull(o7) || StrUtil.isBlank(o7.toString())
                        ) {
                            flag = true;
                        }
                    }
                }
            }
            if (flag) {
                Map<String, Object> gbValueMap = new HashMap<>();
                gbValueMap.put(SignalEnum.SIGNAL_1BC1.getCode(), o);
                retData.add(RetDataUtil.buildRetMap(ruleCode, gbValueMap));
            }
        } catch (NumberFormatException e) {
            System.out.println("规则code: " + ruleCode + " ,异常:" + e);
        }
    }

    public static void check58(Map<String, Object> signalMap, JSONArray retData) {
        String ruleCode = "58";
        try {
            //可充电储能子系统各温度探针检测到的温度值的长度
            Object o = signalMap.get(SignalEnum.SIGNAL_1BC1.getCode());
            boolean flag = false;
            if (!Objects.isNull(o) && StrUtil.isNotBlank(o.toString())) {
                if (o instanceof List) {
                    flag = ((List<?>) o).size() == 0;
                }
            } else {
                flag = true;
            }
            if (flag) {
                Map<String, Object> gbValueMap = new HashMap<>();
                gbValueMap.put(SignalEnum.SIGNAL_1BC1.getCode(), o);
                retData.add(RetDataUtil.buildRetMap(ruleCode, gbValueMap));
            }
        } catch (NumberFormatException e) {
            System.out.println("规则code: " + ruleCode + " ,异常:" + e);
        }
    }

    public static void check64(Map<String, Object> signalMap, JSONArray retData) {
        String signal20FCCode = SignalEnum.SIGNAL_20FC.getCode();
        Object o = signalMap.get(signal20FCCode);
        if (Objects.isNull(o) || StrUtil.isBlank(o.toString())) {
            return;
        }
        Integer signal_2146 = Integer.parseInt(o.toString());
        SignalEnum signal2141 = SignalEnum.SIGNAL_2141;
        SignalEnum signal1bc2 = SignalEnum.SIGNAL_1BC2;
        Object o1 = signalMap.get(signal1bc2.getCode());
        int count = 0;
        if (!Objects.isNull(o1) && StrUtil.isNotBlank(o1.toString())) {
            if (o1 instanceof List) {
                for (Object o2 : ((List<?>) o1)) {
                    Object o3 = ((JSONObject) o2).get(signal2141.getCode());
                    if (Objects.isNull(o3) || StrUtil.isBlank(o3.toString())) {
                        return;
                    }
                    Integer v = Integer.parseInt(o3.toString());
                    count += v;
                }
            }
        }
        if (signal_2146 > count) {
            JSONObject resultDetailData = new JSONObject();
            resultDetailData.put("ruleCode", 64);
            JSONObject gbValue = new JSONObject();
            gbValue.put(signal20FCCode, signal_2146);
            gbValue.put("2141", count);
            resultDetailData.put("gbValue", gbValue);
            retData.add(resultDetailData);
        }
    }

    public static void check73(Map<String, Object> signalMap, JSONArray retData) {
        String signal20FDCode = SignalEnum.SIGNAL_20FD.getCode();
        Object o = signalMap.get(signal20FDCode);
        if (Objects.isNull(o) || StrUtil.isBlank(o.toString())) {
            return;
        }
        Integer signal_2146 = Integer.parseInt(o.toString());
        SignalEnum signal2141 = SignalEnum.SIGNAL_2141;
        SignalEnum signal1bc2 = SignalEnum.SIGNAL_1BC2;
        Object o1 = signalMap.get(signal1bc2.getCode());
        int count = 0;
        if (!Objects.isNull(o1) && StrUtil.isNotBlank(o1.toString())) {
            if (o1 instanceof List) {
                for (Object o2 : ((List<?>) o1)) {
                    Object o3 = ((JSONObject) o2).get(signal2141.getCode());
                    if (Objects.isNull(o3) || StrUtil.isBlank(o3.toString())) {
                        return;
                    }
                    Integer v = Integer.parseInt(o3.toString());
                    count += v;
                }
            }
        }
        if (signal_2146 > count) {
            JSONObject resultDetailData = new JSONObject();
            resultDetailData.put("ruleCode", 73);
            JSONObject gbValue = new JSONObject();
            gbValue.put(signal20FDCode, signal_2146);
            gbValue.put("2141", count);
            resultDetailData.put("gbValue", gbValue);
            retData.add(resultDetailData);
        }
    }
}
