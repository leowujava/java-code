import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import org.junit.Test;

import java.io.IOException;

/**
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public class RuleConfigTest {

    @Test
    public void test() {

    }

    @Test
    public void test5() throws IOException {
        for (NewGbRuleCodeEnum value : NewGbRuleCodeEnum.values()) {
            System.out.println("    " +
                    "/**\n" +
                    "* " + value.getName() + "\n" +
                    "* " + value.getDesc() + "\n" +
                    "*/" +
                    "@Test\n" +
                    "    public void test_" + value.getCode() + "() throws IOException {\n" +
                    "        SignalBO tmpl = BaseDataTest.getTmpl();\n" +
                    "        validate(tmpl);\n" +
                    "    }\n");
        }
    }

}
