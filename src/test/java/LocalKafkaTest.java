import com.chery.gb.realtime.algorithm.util.JsonFileReader;
import com.chery.gb.realtime.algorithm.util.KafkaProducerUtil;

import java.io.IOException;

/**
 * @author zhonghua
 * @date 2025/5/28
 */
public class LocalKafkaTest {
    public static void main(String[] args) throws IOException {

        String filePath = "./data/data.json"; // 你的JSON文件路径
//        String path = new File(filePath).getPath();
        String value = JsonFileReader.readJsonFileAsString(filePath);

        System.out.println("文件：" + filePath);
        KafkaProducerUtil.sendMsgToLocal(value);

    }
}
