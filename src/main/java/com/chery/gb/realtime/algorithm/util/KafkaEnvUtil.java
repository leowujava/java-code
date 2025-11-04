package com.chery.gb.realtime.algorithm.util;


import cn.hutool.core.date.DateUtil;
import com.chery.gb.realtime.algorithm.bo.KafkaConfigBO;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author wugaoyang
 * @date 2025/9/28 星期日
 *
 */
public class KafkaEnvUtil {

    public static FlinkKafkaConsumer<String> getFlinkKafkaConsumer(String configFile) {
        KafkaConfigBO kafkaConfigBO = YmlReader.readKafkaConfigBO(configFile);
        return getFlinkKafkaConsumer(kafkaConfigBO.getServers(), kafkaConfigBO.getConsumerTopic(), kafkaConfigBO.getGroupId());
    }

    public static FlinkKafkaProducer getFlinkKafkaProducer(String configFile) {
        KafkaConfigBO kafkaConfigBO = YmlReader.readKafkaConfigBO(configFile);
        return getFlinkKafkaProducer(kafkaConfigBO.getServers(), kafkaConfigBO.getProducerTopic());
    }

    /**
     * 获取消费者
     */
    public static FlinkKafkaConsumer<String> getFlinkKafkaConsumer(String SERVERS, String CONSUMER_TOPIC, String GROUP_ID) {
        Properties kafkaConsumerProps = new Properties();
        kafkaConsumerProps.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS);
        kafkaConsumerProps.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        // 从 Kafka 读取业务数据
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<String>(
                CONSUMER_TOPIC,
                new KafkaDeserializationSchema<String>() {
                    @Override
                    public boolean isEndOfStream(String nextElement) {
                        return false;
                    }
                    @Override
                    public String deserialize(ConsumerRecord<byte[], byte[]> record) {
                        System.out.println(DateUtil.now() + " 接受kafka消息：" + " topic: " + record.topic() + " 偏移量: " + record.offset());
                        return new String(record.value(), StandardCharsets.UTF_8);
                    }
                    @Override
                    public TypeInformation<String> getProducedType() {
                        return Types.STRING;
                    }
                },
                kafkaConsumerProps
        );
        kafkaConsumer.setStartFromLatest(); // 或者其他起始位置设置
        kafkaConsumer.setCommitOffsetsOnCheckpoints(true);
        return kafkaConsumer;
    }

    /**
     * 配置 Kafka 生产者属性，获取生产者
     */
    private static FlinkKafkaProducer getFlinkKafkaProducer(String servers, String topic) {
        Properties kafkaProducerProps = new Properties();
        kafkaProducerProps.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        FlinkKafkaProducer kafkaProducer = new FlinkKafkaProducer<>(
                topic,
                new KafkaSerializationSchema<String>() {
                    @Override
                    public ProducerRecord<byte[], byte[]> serialize(String element, Long timestamp) {
                        return new ProducerRecord<>(topic, element.getBytes(StandardCharsets.UTF_8));
                    }
                },
                kafkaProducerProps,
                FlinkKafkaProducer.Semantic.AT_LEAST_ONCE
        );
        return kafkaProducer;
    }


}
