import com.alibaba.fastjson2.JSON;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import com.chery.gb.realtime.algorithm.workflow.WorkFlowEnum;
import com.chery.gb.realtime.algorithm.workflow.config.BaseWorkFlowConfig;
import com.chery.gb.realtime.algorithm.workflow.factory.WorkFlowConfigFactory;
import org.apache.commons.collections.CollectionUtils;
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

    @Test
    public void test3() {
        for (WorkFlowEnum value : WorkFlowEnum.values()) {
            BaseWorkFlowConfig workFlowConfig = WorkFlowConfigFactory.getByCode(value.getCode());
            String CODE = "\"" + value.getCode() + "\"";
            List<NewGbRuleCodeEnum> ruleCodeList;
            if (workFlowConfig != null) {
                ruleCodeList = workFlowConfig.getRuleCodeList();
                if (CollectionUtils.isNotEmpty(ruleCodeList)) {
                    List<String> collect = ruleCodeList.stream().map(NewGbRuleCodeEnum::getCode).collect(Collectors.toList());
                    String join = String.join(",", collect);

                    System.out.println(CODE + ": " + join);
                } else {
                    System.out.println(CODE + ": null");
                }
            } else {
                System.out.println(CODE + ": null");
            }
        }
    }

    @Test
    public void test4() {
        List<NewGbRuleCodeEnum> ruleCodeListByCode = WorkFlowEnum.getRuleCodeListByCode(WorkFlowEnum.ALL_DATA.getCode());
        System.out.println(JSON.toJSONString(ruleCodeListByCode));
    }

    @Test
    public void test5() {
        for (int i = 77; i <= 94; i++) {
            System.out.print(i +",");
        }
    }
}
