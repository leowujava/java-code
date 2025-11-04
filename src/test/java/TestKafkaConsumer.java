import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class TestKafkaConsumer {
    private static final String TOPIC = "ntsp_gb_data_message";

    private static final String TOPIC_OUT = "ntsp_gb_data_login_logout";
    private static final String BOOTSTRAP_SERVERS = "172.25.171.72:9092,172.25.171.106:9092,172.25.171.113:9092";
    private static final String GROUP_ID = "flink-group2";

    public static void main(String[] args) {
        // 1. 配置消费者属性
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); // 从最早的消息开始消费
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true"); // 禁用自动提交offset

        // 2. 创建消费者实例
        try (Consumer<String, byte[]> consumer = new KafkaConsumer<>(props)) {
            // 3. 订阅主题
            consumer.subscribe(Collections.singletonList(TOPIC));

            // 4. 轮询获取消息
            while (true) {
                ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, byte[]> record : records) {
                    // 5. 处理消息
                   System.out.printf("Received message: Topic: %s Offset: %d",record.topic(),record.offset());
                    System.out.println(new String(record.value()));
                    // 6. 手动提交offset（同步提交）
                    consumer.commitSync();
                }
            }
        } catch (Exception e) {
            System.err.println("Consumer error: " + e.getMessage());
        }
    }
}