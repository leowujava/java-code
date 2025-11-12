import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.bo.SignalBO;
import com.chery.gb.realtime.algorithm.util.JsonFileReader;
import com.chery.gb.realtime.algorithm.util.SignalUtil;
import com.chery.gb.realtime.algorithm.util.check.NewGBRuleDataCheckUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wugaoyang
 * @date 2025/10/20 星期一
 *
 */
public class RuleValidateTest {

    /**
     * 车辆登入时间越界
     * “年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5
     */
    @Test
    public void test_1() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆登入报文延时>30s
     * 车辆登入报文数据采集时间与服务端平台服务器接收时间相差超过 30秒
     */
    @Test
    public void test_2() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆登入流水号越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_3() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * ICCID 异常
     * ICCID 非 20 位数字码
     */
    @Test
    public void test_4() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 电池管理系统数越界
     * 不在[0,20]范围内
     */
    @Test
    public void test_5() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 电池管理系统对应动力蓄电池包个数越界
     * 不在[0,50]范围内
     */
    @Test
    public void test_6() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包编码异常
     * 动力蓄电池包编码非 24 位
     */
    @Test
    public void test_7() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包总编码个数不匹配
     * 动力蓄电池包总编码个数≠各电池管理系统对应动力蓄电池包个数之和
     */
    @Test
    public void test_8() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆数据采集时间越界
     * “年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5
     */
    @Test
    public void test_9() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆数据报文延时>30s
     * 车辆数据报文数据采集时间与服务端平台服务器接收时间相差超过 30秒
     */
    @Test
    public void test_10() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆登出时间越界
     * “年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5
     */
    @Test
    public void test_11() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆登出报文延时>30s
     * 车辆登出报文数据采集时间与服务端平台服务器接收时间相差超过 30秒
     */
    @Test
    public void test_12() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆登出流水号越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_13() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 当次车辆登入流水号与登出流水号不一致
     * 当次车辆登入流水号≠登出流水号
     */
    @Test
    public void test_14() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 平台登入时间越界
     * “年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5
     */
    @Test
    public void test_15() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 平台登入报文延时>30s
     * 平台登入报文时间与服务端平台服务器接收时间相差超过 30 秒
     */
    @Test
    public void test_16() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 平台登入流水号越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_17() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 平台登出时间越界
     * “年-月-日-时-分-秒”任意一个或多个字节不在有效值范围内,见GB/T32960.3-2024 表 5
     */
    @Test
    public void test_18() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 平台登出报文延时>30s
     * 平台登出报文时间与服务端平台服务器接收时间相差超过 30 秒
     */
    @Test
    public void test_19() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 平台登出流水号越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_20() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 当次平台登入流水号与登出流水号不一致
     * 当次平台登入流水号≠登出流水号
     */
    @Test
    public void test_21() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆状态异常
     * 0xFE
     */
    @Test
    public void test_22() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆状态无效
     * 0xFF
     */
    @Test
    public void test_23() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆状态无定义
     * 不在[0x01、0x02、0x03]范围内
     */
    @Test
    public void test_24() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 充电状态异常
     * 0xFE
     */
    @Test
    public void test_25() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 充电状态无效
     * 0xFF
     */
    @Test
    public void test_26() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 充电状态无定义
     * 不在[0x01、0x02、0x03、0x04]范围内
     */
    @Test
    public void test_27() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 运行模式异常
     * 0xFE
     */
    @Test
    public void test_28() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 运行模式无效
     * 0xFF
     */
    @Test
    public void test_29() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 运行模式无定义
     * 不在[0x01、0x02、0x03]范围内
     */
    @Test
    public void test_30() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车速异常
     * 0xFF,0xFE
     */
    @Test
    public void test_31() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车速无效
     * 0xFF,0xFF
     */
    @Test
    public void test_32() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车速越界
     * 不在[0,3000]范围内
     */
    @Test
    public void test_33() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 累计里程异常
     * 0xFF,0xFF,0xFF,0xFE
     */
    @Test
    public void test_34() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 累计里程无效
     * 0xFF,0xFF,0xFF,0xFF
     */
    @Test
    public void test_35() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 累计里程越界
     * 不在[0,9999999]范围内
     */
    @Test
    public void test_36() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 总电压异常
     * 0xFF,0xFE
     */
    @Test
    public void test_37() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 总电压无效
     * 0xFF,0xFF
     */
    @Test
    public void test_38() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 总电压越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_39() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 总电流异常
     * 0xFF,0xFE
     */
    @Test
    public void test_40() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 总电流无效
     * 0xFF,0xFF
     */
    @Test
    public void test_41() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 总电流越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_42() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * SOC 异常
     * 0xFE
     */
    @Test
    public void test_43() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * SOC 无效
     * 0xFF
     */
    @Test
    public void test_44() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * SOC 越界
     * 不在[0,100]范围内
     */
    @Test
    public void test_45() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * DC-DC 状态异常
     * 0xFE
     */
    @Test
    public void test_46() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * DC-DC 状态无效
     * 0xFF
     */
    @Test
    public void test_47() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * DC-DC 状态无定义
     * 不在[0x01、0x02]范围内
     */
    @Test
    public void test_48() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 档位状态位未定义
     * 不符合 GB/T32960.3-2024 附录 A.1
     */
    @Test
    public void test_49() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 高压对地绝缘电阻越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_50() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包个数异常
     * 0xFE
     */
    @Test
    public void test_51() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包个数无效
     * 0xFF
     */
    @Test
    public void test_52() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包个数越界
     * 不在[0,50]范围内
     */
    @Test
    public void test_53() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包数量不匹配
     * 动力蓄电池包个数≠动力蓄电池最小并联单元电压信息列表中动力蓄电池信息个数
     */
    @Test
    public void test_54() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包号异常
     * 0xFE
     */
    @Test
    public void test_55() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包号无效
     * 0xFF
     */
    @Test
    public void test_56() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包号越界
     * 不在[1,50]范围内
     */
    @Test
    public void test_57() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包电压异常
     * 0xFF,0xFE
     */
    @Test
    public void test_58() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包电压无效
     * 0xFF,0xFF
     */
    @Test
    public void test_59() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包电压越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_60() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包电流异常
     * 0xFF,0xFE
     */
    @Test
    public void test_61() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包电流无效
     * 0xFF,0xFF
     */
    @Test
    public void test_62() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包电流越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_63() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最小并联单元总数异常
     * 0xFF,0xFE
     */
    @Test
    public void test_64() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最小并联单元总数无效
     * 0xFF,0xFF
     */
    @Test
    public void test_65() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最小并联单元总数越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_66() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 本帧最小并联单元电压异常
     * 0xFF,0xFE
     */
    @Test
    public void test_67() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 本帧最小并联单元电压无效
     * 0xFF,0xFF
     */
    @Test
    public void test_68() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 本帧最小并联单元电压越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_69() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 本帧最小并联单元数量不匹配
     * 最小并联单元总数≠最小并联单元电压个数
     */
    @Test
    public void test_70() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包温度探针个数异常
     * 0xFF,0xFE
     */
    @Test
    public void test_71() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包温度探针个数无效
     * 0xFF,0xFF
     */
    @Test
    public void test_72() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 动力蓄电池包温度探针个数越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_73() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 温度探针检测到的温度值异常
     * 0xFE
     */
    @Test
    public void test_74() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 温度探针检测到的温度值无效
     * 0xFF
     */
    @Test
    public void test_75() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 温度探针检测到的温度值越界
     * 不在[0,250]范围内
     */
    @Test
    public void test_76() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机个数越界
     * 不在[1,253]范围内
     */
    @Test
    public void test_77() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机数量不匹配
     * 驱动电机个数≠驱动电机总成信息列表中电机信息个数
     */
    @Test
    public void test_78() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机序号越界
     * 不在[1,253]范围内
     */
    @Test
    public void test_79() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机状态异常
     * 0xFE
     */
    @Test
    public void test_80() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机状态无效
     * 0xFF
     */
    @Test
    public void test_81() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机状态无定义
     * 不在[0x01、0x02、0x03、0x04]范围内
     */
    @Test
    public void test_82() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机控制器温度异常
     * 0xFE
     */
    @Test
    public void test_83() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机控制器温度无效
     * 0xFF
     */
    @Test
    public void test_84() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机控制器温度越界
     * 不在[0,250]范围内
     */
    @Test
    public void test_85() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机转速异常
     * 0xFF,0xFE
     */
    @Test
    public void test_86() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机转速无效
     * 0xFF,0xFF
     */
    @Test
    public void test_87() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机转速越界
     * 不在[0,65531]范围内
     */
    @Test
    public void test_88() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机转矩异常
     * 0xFF,0xFE
     */
    @Test
    public void test_89() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机转矩无效
     * 0xFF,0xFF
     */
    @Test
    public void test_90() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机转矩越界
     * 不在[0,400000]范围内
     */
    @Test
    public void test_91() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机温度异常
     * 0xFE
     */
    @Test
    public void test_92() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机温度无效
     * 0xFF
     */
    @Test
    public void test_93() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机温度越界
     * 不在[0,250]范围内
     */
    @Test
    public void test_94() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车载氢系统中最高温度异常
     * 0xFF,0xFE
     */
    @Test
    public void test_95() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车载氢系统中最高温度无效
     * 0xFF,0xFF
     */
    @Test
    public void test_96() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车载氢系统中最高温度越界
     * 不在[0,2500]范围内
     */
    @Test
    public void test_97() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车载氢系统中最高温度探针代号异常
     * 0xFE
     */
    @Test
    public void test_98() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车载氢系统中最高温度探针代号无效
     * 0xFF
     */
    @Test
    public void test_99() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车载氢系统中最高温度探针代号越界
     * 不在[1,252]范围内
     */
    @Test
    public void test_100() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高浓度异常
     * 0xFF,0xFE
     */
    @Test
    public void test_101() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高浓度无效
     * 0xFF,0xFF
     */
    @Test
    public void test_102() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高浓度越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_103() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高浓度传感器代号异常
     * 0xFE
     */
    @Test
    public void test_104() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高浓度传感器代号无效
     * 0xFF
     */
    @Test
    public void test_105() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高浓度传感器代号越界
     * 不在[1,252]范围内
     */
    @Test
    public void test_106() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高压力异常
     * 0xFF,0xFE
     */
    @Test
    public void test_107() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高压力无效
     * 0xFF,0xFF
     */
    @Test
    public void test_108() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高压力越界
     * 不在[0,1000]范围内
     */
    @Test
    public void test_109() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高压力传感器代号异常
     * 0xFE
     */
    @Test
    public void test_110() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高压力传感器代号无效
     * 0xFF
     */
    @Test
    public void test_111() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气最高压力传感器代号越界
     * 不在[1,252]范围内
     */
    @Test
    public void test_112() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 高压 DC/DC 状态异常
     * 0xFE
     */
    @Test
    public void test_113() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 高压 DC/DC 状态无效
     * 0xFF
     */
    @Test
    public void test_114() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 高压 DC/DC 状态无定义
     * 不在[0x01、0x02]范围内
     */
    @Test
    public void test_115() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 剩余氢量百分比异常
     * 0xFE
     */
    @Test
    public void test_116() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 剩余氢量百分比无效
     * 0xFF
     */
    @Test
    public void test_117() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 剩余氢量百分比越界
     * 不在[0,100]范围内
     */
    @Test
    public void test_118() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 高压 DC/DC 控制器温度异常
     * 0xFE
     */
    @Test
    public void test_119() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 高压 DC/DC 控制器温度无效
     * 0xFF
     */
    @Test
    public void test_120() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 高压 DC/DC 控制器温度越界
     * 不在[0,250]范围内
     */
    @Test
    public void test_121() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆个数异常
     * 0xFE
     */
    @Test
    public void test_122() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆个数无效
     * 0xFF
     */
    @Test
    public void test_123() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆个数越界
     * 不在[1,250]范围内
     */
    @Test
    public void test_124() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆数量不匹配
     * 燃料电池电堆个数≠燃料电池电堆信息表中燃料电池电堆信息个数
     */
    @Test
    public void test_125() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆序号异常
     * 0xFE
     */
    @Test
    public void test_126() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆序号无效
     * 0xFF
     */
    @Test
    public void test_127() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆序号越界
     * 不在[1,250]范围内
     */
    @Test
    public void test_128() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆电压异常
     * 0xFF,0xFE
     */
    @Test
    public void test_129() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆电压无效
     * 0xFF,0xFF
     */
    @Test
    public void test_130() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆电压越界
     * 不在[0,20000]范围内
     */
    @Test
    public void test_131() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆电流异常
     * 0xFF,0xFE
     */
    @Test
    public void test_132() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆电流无效
     * 0xFF,0xFF
     */
    @Test
    public void test_133() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 燃料电池电堆电流越界
     * 不在[0,20000]范围内
     */
    @Test
    public void test_134() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气入口压力异常
     * 0xFF,0xFE
     */
    @Test
    public void test_135() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气入口压力无效
     * 0xFF,0xFF
     */
    @Test
    public void test_136() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 氢气入口压力越界
     * 不在[0,5000]范围内
     */
    @Test
    public void test_137() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 空气入口压力异常
     * 0xFF,0xFE
     */
    @Test
    public void test_138() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 空气入口压力无效
     * 0xFF,0xFF
     */
    @Test
    public void test_139() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 空气入口压力越界
     * 不在[0,5000]范围内
     */
    @Test
    public void test_140() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 空气入口温度异常
     * 0xFE
     */
    @Test
    public void test_141() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 空气入口温度无效
     * 0xFF
     */
    @Test
    public void test_142() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 空气入口温度越界
     * 不在[0,250]范围内
     */
    @Test
    public void test_143() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 冷却水出水口温度探针总数异常
     * 0xFF,0xFE
     */
    @Test
    public void test_144() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 冷却水出水口温度探针总数无效
     * 0xFF,0xFF
     */
    @Test
    public void test_145() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 冷却水出水口温度探针总数越界
     * 不在[0,65531]范围内
     */
    @Test
    public void test_146() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 冷却水出水口温度越界
     * 不在[0,250]范围内
     */
    @Test
    public void test_147() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 曲轴转速异常
     * 0xFF,0xFE
     */
    @Test
    public void test_148() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 曲轴转速无效
     * 0xFF,0xFF
     */
    @Test
    public void test_149() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 曲轴转速越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_150() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 定位状态未定义
     * 不符合 GB/T32960.3-2024 表 22
     */
    @Test
    public void test_151() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 经度精确度不足
     * 精确度不足百万分之一度
     */
    @Test
    public void test_152() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 纬度精确度不足
     * 精确度不足百万分之一度
     */
    @Test
    public void test_153() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 经度越界
     * 不在[0,180000000]范围内
     */
    @Test
    public void test_154() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 纬度越界
     * 不在[0,90000000]范围内
     */
    @Test
    public void test_155() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 三/四级报警报文采集频率异常
     * 触发三/四级报警的车辆数据报文,采集频率不足 1s 一帧
     */
    @Test
    public void test_156() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 三/四级报警报文上报帧数不足
     * 触发三/四级报警的车辆数据报文,补发报警前数据帧数不足 30 帧
     */
    @Test
    public void test_157() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 下高压后三/四级报警报文上报帧数不足
     * 车辆下高压后触发三/四级报警的车辆数据报文,上报帧数不足 30 帧
     */
    @Test
    public void test_158() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高报警等级异常
     * 0xFE
     */
    @Test
    public void test_159() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高报警等级无效
     * 0xFF
     */
    @Test
    public void test_160() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高报警等级无定义
     * 不在[0、1、2、3、4]范围内
     */
    @Test
    public void test_161() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高报警等级不匹配
     * 最高报警等级≠通用报警故障等级列表中的最高通用报警故障等级
     */
    @Test
    public void test_162() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 通用报警标志不匹配
     * 最高报警等级为 1/2/3/4 时,通用报警标志字段中无报警位(指 4 个字节的 32 位均为 0)
     */
    @Test
    public void test_163() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 可充电储能装置故障总数 N1异常
     * 0xFE
     */
    @Test
    public void test_164() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 可充电储能装置故障总数 N1无效
     * 0xFF
     */
    @Test
    public void test_165() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 可充电储能装置故障总数 N1越界
     * 不在[0,252]范围内
     */
    @Test
    public void test_166() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 可充电储能装置故障总数 N1不匹配
     * 可充电储能装置故障总数 N1≠可充电储能装置故障代码列表中可充电储能装置故障信息个数
     */
    @Test
    public void test_167() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机故障总数 N2 异常
     * 0xFE
     */
    @Test
    public void test_168() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机故障总数 N2 无效
     * 0xFF
     */
    @Test
    public void test_169() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机故障总数 N2 越界
     * 不在[0,252]范围内
     */
    @Test
    public void test_170() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 驱动电机故障总数 N2 不匹配
     * 驱动电机故障总数 N2≠驱动电机故障代码列表中驱动电机故障信息个数
     */
    @Test
    public void test_171() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 发动机故障总数 N3 异常
     * 0xFE
     */
    @Test
    public void test_172() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 发动机故障总数 N3 无效
     * 0xFF
     */
    @Test
    public void test_173() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 发动机故障总数 N3 越界
     * 不在[0,252]范围内
     */
    @Test
    public void test_174() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 发动机故障总数 N3 不匹配
     * 发动机故障总数 N3≠发动机故障代码列表中发动机故障信息个数
     */
    @Test
    public void test_175() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 其他故障总数 N4 异常
     * 0xFE
     */
    @Test
    public void test_176() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 其他故障总数 N4 无效
     * 0xFF
     */
    @Test
    public void test_177() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 其他故障总数 N4 越界
     * 不在[0,252]范围内
     */
    @Test
    public void test_178() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 其他故障总数 N4 不匹配
     * 其他故障总数 N4≠其他故障代码列表中其他故障信息个数
     */
    @Test
    public void test_179() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 通用报警故障总数异常
     * 0xFE
     */
    @Test
    public void test_180() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 通用报警故障总数无效
     * 0xFF
     */
    @Test
    public void test_181() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 通用报警故障总数越界
     * 不在[0,252]范围内
     */
    @Test
    public void test_182() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 通用报警故障总数不匹配
     * 通用报警故障总数≠通用报警故障等级列表中通用报警故障等级个数
     */
    @Test
    public void test_183() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容管理系统号异常
     * 0xFE
     */
    @Test
    public void test_184() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容管理系统号无效
     * 0xFF
     */
    @Test
    public void test_185() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容管理系统号越界
     * 不在[1,250]范围内
     */
    @Test
    public void test_186() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容总电压异常
     * 0xFF,0xFE
     */
    @Test
    public void test_187() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容总电压无效
     * 0xFF,0xFF
     */
    @Test
    public void test_188() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容总电压越界
     * 不在[0,10000]范围内
     */
    @Test
    public void test_189() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容总电流异常
     * 0xFF,0xFE
     */
    @Test
    public void test_190() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容总电流无效
     * 0xFF,0xFF
     */
    @Test
    public void test_191() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容总电流越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_192() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体总数异常
     * 0xFF,0xFE
     */
    @Test
    public void test_193() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体总数无效
     * 0xFF,0xFF
     */
    @Test
    public void test_194() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体总数越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_195() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体总数不匹配
     * 超级电容单体总数≠超级电容单体电压个数
     */
    @Test
    public void test_196() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压异常
     * 0xFF,0xFE
     */
    @Test
    public void test_197() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压无效
     * 0xFF,0xFF
     */
    @Test
    public void test_198() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_199() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容温度探针总数异常
     * 0xFF,0xFE
     */
    @Test
    public void test_200() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容温度探针总数无效
     * 0xFF,0xFF
     */
    @Test
    public void test_201() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容温度探针总数越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_202() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容温度探针总数不匹配
     * 超级电容温度探针总数≠探针温度值个数
     */
    @Test
    public void test_203() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 探针温度值异常
     * 0xFE
     */
    @Test
    public void test_204() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 探针温度值无效
     * 0xFF
     */
    @Test
    public void test_205() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 探针温度值越界
     * 不在[0,250]范围内
     */
    @Test
    public void test_206() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高电压管理系统号异常
     * 0xFE
     */
    @Test
    public void test_207() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高电压管理系统号无效
     * 0xFF
     */
    @Test
    public void test_208() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高电压管理系统号越界
     * 不在[1,250]范围内
     */
    @Test
    public void test_209() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高电压超级电容单体代号越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_210() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压最高值异常
     * 0xFF,0xFE
     */
    @Test
    public void test_211() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压最高值无效
     * 0xFF,0xFF
     */
    @Test
    public void test_212() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压最高值越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_213() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压最高值不匹配
     * 超级电容单体电压最高值≠超级电容单体电压列表中电压最高值
     */
    @Test
    public void test_214() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低电压管理系统号异常
     * 0xFE
     */
    @Test
    public void test_215() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低电压管理系统号无效
     * 0xFF
     */
    @Test
    public void test_216() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低电压管理系统号越界
     * 不在[1,250]范围内
     */
    @Test
    public void test_217() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低电压超级电容单体代号越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_218() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压最低值异常
     * 0xFF,0xFE
     */
    @Test
    public void test_219() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压最低值无效
     * 0xFF,0xFF
     */
    @Test
    public void test_220() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压最低值越界
     * 不在[0,60000]范围内
     */
    @Test
    public void test_221() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 超级电容单体电压最低值不匹配
     * 超级电容单体电压最低值≠超级电容单体电压列表中电压最低值
     */
    @Test
    public void test_222() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高温度管理系统号异常
     * 0xFE
     */
    @Test
    public void test_223() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高温度管理系统号无效
     * 0xFF
     */
    @Test
    public void test_224() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高温度管理系统号越界
     * 不在[1,250]范围内
     */
    @Test
    public void test_225() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高温度探针代号越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_226() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高温度值异常
     * 0xFE
     */
    @Test
    public void test_227() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高温度值无效
     * 0xFF
     */
    @Test
    public void test_228() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高温度值越界
     * 不在[0,250]范围内
     */
    @Test
    public void test_229() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最高温度值不匹配
     * 最高温度值≠探针温度值列表中温度最高值
     */
    @Test
    public void test_230() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低温度管理系统号异常
     * 0xFE
     */
    @Test
    public void test_231() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低温度管理系统号无效
     * 0xFF
     */
    @Test
    public void test_232() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低温度管理系统号越界
     * 不在[1,250]范围内
     */
    @Test
    public void test_233() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低温度探针代号越界
     * 不在[1,65531]范围内
     */
    @Test
    public void test_234() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低温度值异常
     * 0xFE
     */
    @Test
    public void test_235() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低温度值无效
     * 0xFF
     */
    @Test
    public void test_236() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低温度值越界
     * 不在[0,250]范围内
     */
    @Test
    public void test_237() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最低温度值不匹配
     * 最低温度值≠探针温度值列表中温度最低值
     */
    @Test
    public void test_238() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传整车数据
     * 未上传整车数据
     */
    @Test
    public void test_239() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传动力蓄电池最小并联单元电压数据
     * 未上传动力蓄电池最小并联单元电压数据
     */
    @Test
    public void test_240() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传动力蓄电池温度数据
     * 未上传动力蓄电池温度数据
     */
    @Test
    public void test_241() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传驱动电机数据
     * 未上传驱动电机数据
     */
    @Test
    public void test_242() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传燃料电池发动机及车载氢系统数据
     * 燃料电池汽车未上传燃料电池发动机及车载氢系统数据
     */
    @Test
    public void test_243() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传燃料电池电堆数据
     * 燃料电池汽车未上传燃料电池电堆数据
     */
    @Test
    public void test_244() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传发动机数据
     * 未上传发动机数据
     */
    @Test
    public void test_245() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传车辆位置数据
     * 未上传车辆位置数据
     */
    @Test
    public void test_246() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传超级电容器数据
     * 超级电容汽车未上传超级电容器数据
     */
    @Test
    public void test_247() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传超级电容器极值数据
     * 超级电容汽车未上传超级电容器极值数据
     */
    @Test
    public void test_248() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆状态为熄火车速不为 0
     * 车辆熄火, 但车速>5km/h
     */
    @Test
    public void test_250() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 坐标系异常
     * 0xFE
     */
    @Test
    public void test_251() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 坐标系无效
     * 0xFF
     */
    @Test
    public void test_252() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 坐标系无定义
     * 不在[0x01、0x02、0x03]范围内
     */
    @Test
    public void test_253() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 档位异常
     * 车速大于 0 档位为 P 档
     */
    @Test
    public void test_254() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 曲轴转速异常
     * 纯电模式下曲轴转速>0
     */
    @Test
    public void test_255() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 总电压不匹配
     * 总电压≠最小并联单元电压之和(偏差超出 5V)
     */
    @Test
    public void test_256() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆行驶中能量回收显示停车充电
     * 纯电动车速>0 电流为负数 充电状态 1 同时出现
     */
    @Test
    public void test_257() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 未上传报警数据
     * 未上传报警数据
     */
    @Test
    public void test_258() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆状态非 1 启动且非 2 熄火
     * 车辆状态既不是启动也不是熄火
     */
    @Test
    public void test_259() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 高压下电监测报文缺失
     * 车辆下高压未上报 59min30s 至 1h 监测报文
     */
    @Test
    public void test_260() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 三/四级报警报文未持续至报警标志位消失
     * 触发 3|4 级报警后, 报文未持续到报警标志位消失
     */
    @Test
    public void test_261() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 熄火切换到启动状态里程值变化
     * 熄火最后一帧到启动第一帧里程变化超过 4km
     */
    @Test
    public void test_262() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆登入报文延时>180s
     * 车辆登入报文数据采集时间与服务端平台服务器接收时间相差超过 180秒
     */
    @Test
    public void test_263() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆数据报文延时>180s
     * 车辆数据报文数据采集时间与服务端平台服务器接收时间相差超过 180秒
     */
    @Test
    public void test_264() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆登出报文延时>180s
     * 车辆登出报文数据采集时间与服务端平台服务器接收时间相差超过 180秒
     */
    @Test
    public void test_265() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 平台登入报文延时>180s
     * 平台登入报文时间与服务端平台服务器接收时间相差超过 180 秒
     */
    @Test
    public void test_266() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 平台登出报文延时>180s
     * 平台登出报文时间与服务端平台服务器接收时间相差超过 180 秒
     */
    @Test
    public void test_267() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最小并联单元电压精确度不足
     * 电压精度不满足 0.001V
     */
    @Test
    public void test_268() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 四级报警与通用报警故障等级列表不匹配
     * 4级报警后在通用报警故障等级列表中未有可充电储能装置热事件报警
     */
    @Test
    public void test_269() throws IOException {
        SignalBO tmpl = getTmpl();
        tmpl.set_220B(4);
        validate(tmpl);
    }

    /**
     * 30 秒内里程跳变大于 4 公里
     * 相差 30 秒的 2 帧报文里程跳变大于 4 公里
     */
    @Test
    public void test_270() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 多条相同时间报文不一致
     * 多条相同时间报文整车数据中任意一项不一致
     */
    @Test
    public void test_271() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 车辆再次登录流水号不大于上一次登录流水号
     * 再次登录流水号<=上一次登录流水号
     */
    @Test
    public void test_273() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 平台再次登录流水号不大于上一次登录流水号
     * 再次登录流水号<=上一次登录流水号
     */
    @Test
    public void test_274() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }

    /**
     * 最小并联单元前后报文数目不一致
     * 报文前后 2帧上传的最小并联单元数目不一致
     */
    @Test
    public void test_275() throws IOException {
        SignalBO tmpl = getTmpl();
        validate(tmpl);
    }


    public static SignalBO getTmpl() throws IOException {

        String filePath = "./data/data-tmpl.json"; // 你的JSON文件路径
//        String path = new File(filePath).getPath();
        String value = JsonFileReader.readJsonFileAsString(filePath);

        SignalBO signalBO = JSON.parseObject(value, SignalBO.class);
        System.out.println(JSON.toJSONString(signalBO));
        return signalBO;
    }

    private static void validate(SignalBO tmpl) {
        JSONArray retData = new JSONArray();
        HashMap<String, Object> signalMap = JSON.parseObject(JSON.toJSONString(tmpl), HashMap.class);
        SignalUtil.convert(signalMap);
        System.out.println("处理后的数据：" + JSON.toJSONString(signalMap));
        HashMap<String, Map<String, List<RuleDetailBO>>> ruleMap = new HashMap<>();
        NewGBRuleDataCheckUtil.checkDataFromWorkFlow(signalMap, ruleMap, retData);
        System.out.println(retData);
    }

}
