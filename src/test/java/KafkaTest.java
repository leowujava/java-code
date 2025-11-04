import com.chery.gb.realtime.algorithm.util.JsonFileReader;
import org.apache.kafka.clients.producer.*;

import java.io.IOException;
import java.util.Properties;

/**
 * @author zhonghua
 * @date 2025/5/28
 */
public class KafkaTest {

    private static final String TOPIC_NAME = "ntsp_gb_data_message";

    public static void main(String[] args) throws IOException {
        // 1. 配置生产者属性
        Properties props = new Properties();
        String servers = "172.25.171.72:9092,172.25.171.106:9092,172.25.171.113:9092";//测试环境
//        servers = "172.25.171.37:9092,172.25.171.52:9092,172.25.171.54:9092";//开发环境
//        String servers = "127.0.0.1:9092";//本地环境
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("group.id", "flink-group2");

        // 2. 创建生产者实例
        Producer<String, String> producer = new KafkaProducer<>(props);

        String filePath = "data/data-129-6.json"; // 你的JSON文件路径
//        String path = KafkaTest.class.getClassLoader().getResource(filePath).getPath();
        String value = JsonFileReader.readJsonFileAsString(filePath);
        try {
            System.out.println("文件：" + filePath);
            // 3. 创建ProducerRecord
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, value);
            // 4. 发送消息（异步）
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        System.err.println("发送消息失败: " + e.getMessage());
                    } else {
                        System.out.printf("消息发送成功! topic=%s, partition=%d, offset=%d",
                                metadata.topic(), metadata.partition(), metadata.offset());
                    }
                }
            });
        } finally {
            // 5. 关闭生产者
            producer.close();
        }

    }
}
