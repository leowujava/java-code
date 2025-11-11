package com.chery.gb.realtime.algorithm.util;


import com.chery.gb.realtime.algorithm.bo.KafkaConfigBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @author wugaoyang
 * @date 2025/10/20 星期一
 *
 */
public class KafkaProducerUtil {

    public static void sendMsgToLocal(String value){
        sendMsg(value,"local");
    }

    public static void sendMsg(String value, String env) {
        String configFile = "application.yml";
        if (StringUtils.isNotEmpty(env)) {
            configFile = "application-" + env + ".yml";
        }
        KafkaConfigBO kafkaConfigBO = AppConfigReader.readKafkaConfigBO(configFile);
        // 1. 配置生产者属性
        Properties props = new Properties();
        String servers = kafkaConfigBO.getServers();//本地环境
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("group.id", kafkaConfigBO.getGroupId());
        // 2. 创建生产者实例
        Producer<String, String> producer = new KafkaProducer<>(props);
        try {
            // 3. 创建ProducerRecord
            ProducerRecord<String, String> record = new ProducerRecord<>(kafkaConfigBO.getConsumerTopic(), value);
            for (int i = 0; i < 1; i++) {
                // 4. 发送消息（异步）
                producer.send(record, (metadata, e) -> {
                    if (e != null) {
                        System.err.println("发送消息失败: " + e.getMessage());
                    } else {
                        System.out.printf("消息发送成功! topic=%s, partition=%d, offset=%d",
                                metadata.topic(), metadata.partition(), metadata.offset());
                    }
                });
            }
        } finally {
            // 5. 关闭生产者
            producer.close();
        }
    }
}
