import com.alibaba.fastjson2.JSON;
import com.chery.gb.realtime.algorithm.bo.SignalBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.signal.config.BaseSignalConfig;
import com.chery.gb.realtime.algorithm.signal.factory.SignalConfigFactory;
import com.chery.gb.realtime.algorithm.util.SignalUtil;
import data.BaseDataTest;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author wugaoyang
 * @date 2025/11/5 星期三
 *
 */
public class SignalConfigTest {

    @Test
    public void test() throws IOException {
        SignalBO tmpl = BaseDataTest.getTmpl();
        tmpl.set_220C(254);
        tmpl.set_2001(60);
        tmpl.set_21AA(254);
        HashMap<String, Object> signalMap = JSON.parseObject(JSON.toJSONString(tmpl), HashMap.class);
        SignalUtil.convert(signalMap);
        System.out.println("处理后的数据：" + JSON.toJSONString(signalMap));
        BaseSignalConfig signalConfig = SignalConfigFactory.getByCode(SignalEnum.SIGNAL_220C.getCode());
        boolean b = signalConfig.validateError(signalMap);
        System.out.println(b);
    }

    @Test
    public void test2() throws IOException {
        SignalBO tmpl = BaseDataTest.getTmpl();
        tmpl.set_220C(255);
        tmpl.set_2001(60);
        tmpl.set_21AA(254);
        HashMap<String, Object> signalMap = JSON.parseObject(JSON.toJSONString(tmpl), HashMap.class);
        SignalUtil.convert(signalMap);
        System.out.println("处理后的数据：" + JSON.toJSONString(signalMap));
        BaseSignalConfig signalConfig = SignalConfigFactory.getByCode(SignalEnum.SIGNAL_220C.getCode());
        boolean b = signalConfig.validateInvalid(signalMap);
        System.out.println(b);
    }

    @Test
    public void test3() throws IOException {
        SignalBO tmpl = BaseDataTest.getTmpl();
        tmpl.set_220C(1);
        tmpl.set_2001(60);
        tmpl.set_21AA(254);
        HashMap<String, Object> signalMap = JSON.parseObject(JSON.toJSONString(tmpl), HashMap.class);
        SignalUtil.convert(signalMap);
        System.out.println("处理后的数据：" + JSON.toJSONString(signalMap));
        BaseSignalConfig signalConfig = SignalConfigFactory.getByCode(SignalEnum.SIGNAL_220C.getCode());
        boolean b = signalConfig.validateNull(signalMap);
        System.out.println(b);
    }
}
