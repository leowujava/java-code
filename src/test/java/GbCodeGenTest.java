import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/10/31 星期五
 *
 */
public class GbCodeGenTest {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\Dev\\需求\\newGbCode.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();
        while (line != null) {
            String[] split = line.split("\t");
            System.out.println("RULE_CODE_" + split[0] + "(\"" + split[0] + "\",\"" + split[1] + "\",\"" + split[2] + "\",\"" + split[3] + "\",\"" + split[4] + "\"),");
//            System.out.println("newGbRuleCodeEnums.add(RULE_CODE_"+split[0]+");");
            genConfigFile(split[0], split[1], split[2]);
//            genValidateFile(split[0], split[1]);
            line = br.readLine();
        }
    }


    @Test
    public void test3() throws IOException {
        List<NewGbRuleCodeEnum> ruleSubTypes = NewGbRuleCodeEnum.getByRuleSubTypes(Arrays.asList("异常值", "数据越界", "无效值", "缺失"));
        for (NewGbRuleCodeEnum ruleCodeEnum : ruleSubTypes) {
            String string = ruleCodeEnum.getCode() + "\t" + ruleCodeEnum.getName() + "\t" + ruleCodeEnum.getDesc() + "\t" + ruleCodeEnum.getRuleSubType();

            if (ruleCodeEnum.getRuleSubType().equals("异常值") && !ruleCodeEnum.getDesc().contains("0xFE")) {
//                genConfigFile(ruleCodeEnum.getCode(), ruleCodeEnum.getName(), ruleCodeEnum.getDesc());
                continue;
            }
//            System.out.println(string);
            System.out.println("signaleMap.put(NewGbRuleCodeEnum.RULE_CODE_" + ruleCodeEnum.getCode() + ".getCode(), null);");
//            genConfigFile(ruleCodeEnum.getCode(), ruleCodeEnum.getName(), ruleCodeEnum.getDesc());
//            genConfigFile2(ruleCodeEnum.getCode(), ruleCodeEnum.getName(), ruleCodeEnum.getDesc());
        }
    }

    private static void genValidateFile(String ruleCode, String name) throws IOException {

        String content = "package com.chery.gb.realtime.algorithm.rule.validator;\n" +
                "\n" +
                "\n" +
                "import com.alibaba.fastjson2.JSONArray;\n" +
                "import com.chery.gb.realtime.algorithm.anotation.RuleValidate;\n" +
                "import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;\n" +
                "import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;\n" +
                "\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/**\n" +
                " * " + name + "\n" +
                " * \n" +
                " * @author wugaoyang\n" +
                " * @date 2025/11/3 星期一\n" +
                " *\n" +
                " */\n" +
                "@RuleValidate(rule = NewGbRuleCodeEnum.RULE_CODE_" + ruleCode + ")\n" +
                "public class RuleValidator_" + ruleCode + " extends BaseRuleValidator {\n" +
                "    @Override\n" +
                "    public void validate(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {\n" +
                "        super.validate(signalMap, ruleMap, retData);\n" +
                "    }\n" +
                "}\n";

        File file = new File("./validate", "RuleValidator_" + ruleCode + ".java");
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }

    private static void genConfigFile(String ruleCode, String name, String desc) throws IOException {

        String content = "package com.chery.gb.realtime.algorithm.rule.config;\n" +
                "\n" +
                "\n" +
                "import com.chery.gb.realtime.algorithm.anotation.RuleConfig;\n" +
                "import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;\n" +
                "import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;\n" +
                "import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;\n" +
                "import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**\n" +
                " * " + name + "\n" +
                " * " + desc + "\n" +
                " * \n" +
                " * @author wugaoyang\n" +
                " * @date 2025/11/4 星期二\n" +
                " *\n" +
                " */\n" +
                "@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_" + ruleCode + ")\n" +
                "public class RuleConfig_" + ruleCode + " extends BaseConfig {\n" +
                "\n" +
                "    public RuleConfig_" + ruleCode + "() {\n" +
                "        ruleConfigBO = new RuleConfigBO();\n" +
                "        RuleConditionBO condition = new RuleConditionBO();\n" +
                "        ruleConfigBO.setCondition(condition);\n" +
                "    }\n" +
                "\n" +
                "}\n";

        File file = new File("./config", "RuleConfig_" + ruleCode + ".java");
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }


    private static void genConfigFile2(String ruleCode, String name, String desc) throws IOException {

        String content = "package com.chery.gb.realtime.algorithm.rule.config;\n" +
                "\n" +
                "\n" +
                "import com.chery.gb.realtime.algorithm.anotation.RuleConfig;\n" +
                "import com.chery.gb.realtime.algorithm.bo.RuleConditionBO;\n" +
                "import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;\n" +
                "import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;\n" +
                "import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**\n" +
                " * " + name + "\n" +
                " * " + desc + "\n" +
                " * \n" +
                " * @author wugaoyang\n" +
                " * @date 2025/11/4 星期二\n" +
                " *\n" +
                " */\n" +
                "@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_" + ruleCode + ")\n" +
                "public class RuleConfig_" + ruleCode + " extends BaseConfig {\n" +
                "\n" +
                "    public RuleConfig_" + ruleCode + "() {\n" +
                "        ruleConfigBO = new RuleConfigBO();\n" +
                "        RuleConditionBO condition = buildCondition();\n" +
                "        ruleConfigBO.setCondition(condition);\n" +
                "    }\n" +
                "\n" +
                "}\n";

        File file = new File("./config", "RuleConfig_" + ruleCode + ".java");
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }
}
