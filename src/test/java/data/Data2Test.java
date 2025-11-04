package data;


import com.alibaba.fastjson2.JSON;
import com.chery.gb.realtime.algorithm.bo.SignalBO;
import com.chery.gb.realtime.algorithm.util.KafkaProducerUtil;
import org.junit.Test;

import java.io.IOException;

/**
 *
 * @author wugaoyang
 * @date 2025/10/20 星期一
 *
 */
public class Data2Test extends BaseDataTest {

    /**
     *
     * CODE-2
     * 21AA不为空，且21AA!=3，且220C=1或2，且220B=3;
     * 2076信号不传或空值或传33-255 触发规则
     */
    @Test
    public void test1() throws IOException {
        SignalBO tmpl = getTmpl();
        tmpl.set_21AA(2);
        tmpl.set_220C(1);
        tmpl.set_220B(3);
        tmpl.set_2076(null);
        KafkaProducerUtil.sendMsgToLocal(JSON.toJSONString(tmpl));
    }

    @Test
    public void test2() throws IOException {
        SignalBO tmpl = getTmpl();
        tmpl.set_21AA(2);
        tmpl.set_220C(1);
        tmpl.set_220B(3);
        tmpl.set_2076("33");
        KafkaProducerUtil.sendMsgToLocal(JSON.toJSONString(tmpl));
    }

    @Test
    public void test3() throws IOException {
        SignalBO tmpl = getTmpl();
        tmpl.set_21AA(2);
        tmpl.set_220C(1);
        tmpl.set_220B(3);
        tmpl.set_2076("255");
        KafkaProducerUtil.sendMsgToLocal(JSON.toJSONString(tmpl));
    }
}
