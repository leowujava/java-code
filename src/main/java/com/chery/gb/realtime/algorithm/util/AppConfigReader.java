package com.chery.gb.realtime.algorithm.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.bo.KafkaConfigBO;
import com.chery.gb.realtime.algorithm.bo.MySqlConfigBO;

import java.util.Map;

public class AppConfigReader {

    private static KafkaConfigBO kafkaConfigBO;

    private static MySqlConfigBO mySqlConfigBO;

    private static Map<String, Object> configMap;

    public static MySqlConfigBO readMySqlConfigBO(String configFile) {
        if (mySqlConfigBO != null) {
            return mySqlConfigBO;
        }
        Map<String, Object> data = readConfig(configFile);

        mySqlConfigBO = new MySqlConfigBO();
        Object mysql = data.get("mysql");
        if (mysql != null) {
            BeanUtil.copyProperties(mysql, mySqlConfigBO);
        }
        return mySqlConfigBO;
    }

    public static KafkaConfigBO readKafkaConfigBO(String configFile) {
        if (kafkaConfigBO != null) {
            return kafkaConfigBO;
        }
        Map<String, Object> data = readConfig(configFile);

        kafkaConfigBO = new KafkaConfigBO();
        Object mysql = data.get("kafka");
        if (mysql != null) {
            BeanUtil.copyProperties(mysql, kafkaConfigBO);
        }
        return kafkaConfigBO;
    }

    private static Map<String, Object> readConfig(String configFile) {
        if (configMap != null) {
            return configMap;
        }
        if (StrUtil.isBlank(configFile)) {
            configFile = "application.yml";
        }
        configMap = YmlReaderUtil.readConfig(configFile);
        return configMap;
    }
}
