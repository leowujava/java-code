import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        Map<String, SignalEnum> ruleSignalRelationMap = NewGbRuleCodeEnum.getRuleSignalRelationMap();
        List<String> collect = ruleSignalRelationMap.keySet().stream().sorted(Comparator.comparing(s -> Long.valueOf(s))).collect(Collectors.toList());
        collect.forEach(key -> {
            SignalEnum value = ruleSignalRelationMap.get(key);
            String code = null;
            if (value != null) {
                code = "\"" + value.getCode() + "\"";
            }
            System.out.println("\"" + key + "\": " + code);
        });
//        ruleSignalRelationMap.forEach((key, value) -> {
//            String code = null;
//            if (value != null) {
//                code = value.getCode();
//            }
//            System.out.println(key + ": " + code);
//        });
    }
}
