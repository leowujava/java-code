import com.chery.gb.realtime.algorithm.util.YmlReaderUtil;
import org.junit.Test;

import java.util.Map;

/**
 * @author wugaoyang
 * @date 2025/11/10 星期一
 *
 */
public class YamlReaderTest {

    @Test
    public void test(){
        Map<String, Object> map = YmlReaderUtil.readConfig("./config/signal_config.yml");
        System.out.println(map.get("signal"));
    }
}
