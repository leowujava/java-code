import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.chery.gb.realtime.algorithm.bo.SignalBO;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.signal.config.BaseSignalConfig;
import com.chery.gb.realtime.algorithm.signal.factory.SignalConfigFactory;
import com.chery.gb.realtime.algorithm.util.SignalUtil;
import data.BaseDataTest;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

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

    @Test
    public void test4() throws IOException {
        for (SignalEnum signalEnum : SignalEnum.values()) {
            genConfigFile(signalEnum);
        }
    }

    private void genConfigFile(SignalEnum signalEnum) throws IOException {
        if (StrUtil.isNotBlank(signalEnum.getParent())) {
            return;
        }

        String name = signalEnum.getCode();
        String desc = signalEnum.getName();
        String code = signalEnum.getCode();
        String content = "package com.chery.gb.realtime.algorithm.signal.config;\n" +
                "\n" +
                "\n" +
                "import com.chery.gb.realtime.algorithm.anotation.SignalConfig;\n" +
                "import com.chery.gb.realtime.algorithm.bo.SignalConfigBO;\n" +
                "import com.chery.gb.realtime.algorithm.enums.SignalEnum;\n\n" +
                "\n" +
                "/**\n" +
                " * " + name + "\n" +
                " * " + desc + "\n" +
                " * \n" +
                " * @author wugaoyang\n" +
                " * @date 2025/11/4 星期二\n" +
                " *\n" +
                " */\n" +
                "@SignalConfig(SignalEnum.SIGNAL_" + code + ")\n" +
                "public class SignalConfig_" + code + " extends BaseSignalConfig {\n" +
                "\n" +
                "    public SignalConfig_" + code + "() {\n" +
                "         signalConfigBO = SignalConfigBO.builder()\n" +
                "                .scale(null)\n" +
                "                .offset(null)\n" +
                "                .range(null)\n" +
                "                .min(null)\n" +
                "                .max(null)\n" +
                "                .errorValue(null)\n" +
                "                .invalidValue(null)\n" +
                "                .build();\n" +
                "    }\n" +
                "\n" +
                "}\n";

        File file = new File("./config/signal", "SignalConfig_" + code + ".java");
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }
}
