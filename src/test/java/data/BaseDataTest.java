package data;

import com.alibaba.fastjson2.JSON;
import com.chery.gb.realtime.algorithm.bo.SignalBO;
import com.chery.gb.realtime.algorithm.util.JsonFileReader;

import java.io.IOException;

/**
 * @author wugaoyang
 * @date 2025/10/20 星期一
 *
 */
public class BaseDataTest {

    public SignalBO getTmpl() throws IOException {

        String filePath = "./data/data-tmpl.json"; // 你的JSON文件路径
//        String path = new File(filePath).getPath();
        String value = JsonFileReader.readJsonFileAsString(filePath);

        SignalBO signalBO = JSON.parseObject(value, SignalBO.class);
        System.out.println(JSON.toJSONString(signalBO));
        return signalBO;
    }
}
