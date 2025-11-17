import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.enums.SignalEnum;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public class RuleConfigTest {

    @Test
    public void test() {

    }

    //规则和信号对应关系
    @Test
    public void test2() {
        Map<String, SignalEnum> ruleSignalRelationMap = NewGbRuleCodeEnum.getRuleSignalRelationMap();
        List<String> collect = ruleSignalRelationMap.keySet().stream().sorted(Comparator.comparing(s -> Long.valueOf(s))).collect(Collectors.toList());
        List<String> codeList = new ArrayList<>();
        collect.forEach(key -> {
            NewGbRuleCodeEnum gbRuleCodeEnum = NewGbRuleCodeEnum.getByCode(key);
            SignalEnum value = ruleSignalRelationMap.get(key);
            String name = "";
            if (value != null) {
                name = value.getName();
            }
            String name1 = gbRuleCodeEnum.getName();
            System.out.println("#" + name1 + ":" + name);
            String code = null;
            if (value != null) {
                code = "\"" + value.getCode() + "\"";
            } else {
                String signal = name1.replace("无效", "").replace("越界", "").replace("异常", "").replace("无定义", "").replace(" ", "").replace("/", "_");
//                printSignal(codeList, signal);
                code = "\"" + signal + "\"";
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

    private static void printSignal(List<String> codeList, String signal) {
        if (codeList.contains(signal)) {
            return;
        }
        codeList.add(signal);
        System.out.println(signal + "(\"" + signal + "\", \"" + signal + "\", null, null, null),");
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


    @Test
    public void test6() throws IOException {
        String rules = "37,38,39,40,41,42,43,44,45,46,47,48,50,51,52,53,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,71,72,73,74,75,76,151,154,155,159,160,161,164,165,166,168,169,170,172,173,174,176,177,178,180,181,182,251,252,253";
        List<String> codeList1 = new ArrayList<>();
        List<String> codeList2 = new ArrayList<>();
        Set<String> codeName = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for (String ruleCode : rules.split(",")) {
            NewGbRuleCodeEnum code = NewGbRuleCodeEnum.getByCode(ruleCode);
            if (code == null) {
                continue;
            }
            String ruleSubType = code.getRuleSubType();
            String name = code.getName();
            String signal = name.replace("无效", "").replace("越界", "").replace("异常", "").replace("无定义", "").replace(" ", "").replace("/", "_");
            codeName.add(signal);
            if (ruleSubType.contains("异常") || ruleSubType.contains("无效")) {
                sb.append(ruleCode + "\t" + name);
                codeList1.add(ruleCode);
            } else {
                sb2.append(ruleCode + "\t" + name);
                codeList2.add(ruleCode);
            }
        }
        System.out.println(sb);
        System.out.println("codeList1: " + codeList1.size() + "\t" + String.join(",", codeList1));

        System.out.println(sb2);
        System.out.println("codeList2: " + codeList2.size() + "\t" + String.join(",", codeList2));

        System.out.println(String.join(",", codeName));
    }

}
