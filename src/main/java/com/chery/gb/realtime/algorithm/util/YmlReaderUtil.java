package com.chery.gb.realtime.algorithm.util;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YmlReaderUtil {


    public static Map<String, Object> readConfig(String configFile) {
        Yaml yaml = new Yaml();
        InputStream in = YmlReaderUtil.class.getClassLoader().getResourceAsStream(configFile);
        if (in == null) {
            throw new RuntimeException(configFile + " not found in classpath");
        }
        Map<String, Object> configMap = yaml.load(in);
        System.out.println("读取到的配置文件：" + configFile + ":" + configMap);
        return configMap;
    }
}
