import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import org.junit.Test;

import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public class MyTest {

    @Test
    public void test1() {
        List<NewGbRuleCodeEnum> byGroup = NewGbRuleCodeEnum.getByGroup("整车数据");
        for (NewGbRuleCodeEnum newGbRuleCodeEnum : byGroup) {
            System.out.println(newGbRuleCodeEnum.getDesc());
        }
    }

    @Test
    public void test2() {
        List<NewGbRuleCodeEnum> byGroup = NewGbRuleCodeEnum.getByGroup("动力蓄电池数据");
        for (NewGbRuleCodeEnum newGbRuleCodeEnum : byGroup) {
            System.out.println(newGbRuleCodeEnum.getCode() + "\t" + newGbRuleCodeEnum.getName() + "\t" + newGbRuleCodeEnum.getGroup() + "\t" + newGbRuleCodeEnum.getDesc());
        }
    }
}
