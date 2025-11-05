import java.io.*;

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
//            genValidateFile(split[0]);
            line = br.readLine();
        }
    }

    private static void genValidateFile(String ruleCode) throws IOException {

        String content = "package com.chery.gb.realtime.algorithm.validate.rule;\n" +
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
                " * @author wugaoyang\n" +
                " * @date 2025/11/3 星期一\n" +
                " *\n" +
                " */\n" +
                "@RuleValidate(rule = NewGbRuleCodeEnum.RULE_CODE_" + ruleCode + ")\n" +
                "public class RuleValidate_"+ ruleCode +" extends BaseRuleValidate {\n" +
                "    @Override\n" +
                "    public void validate(Map<String, Object> signalMap, Map<String, Map<String, List<RuleDetailBO>>> ruleMap, JSONArray retData) {\n" +
                "\n" +
                "    }\n" +
                "}\n";

        File file = new File("./validate", "RuleValidate_" + ruleCode + ".java");
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }

    private static void genConfigFile(String ruleCode, String name, String desc) throws IOException {

        String content = "package com.chery.gb.realtime.algorithm.validate.config;\n" +
                "\n" +
                "\n" +
                "import com.chery.gb.realtime.algorithm.anotation.RuleConfig;\n" +
                "import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;\n" +
                "import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;\n" +
                "import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "\n" +
                "/**\n" +
                " * " + name + "\n" +
                " * " +  desc + "\n" +
                " * \n" +
                " * @author wugaoyang\n" +
                " * @date 2025/11/4 星期二\n" +
                " *\n" +
                " */\n" +
                "@RuleConfig(rule = NewGbRuleCodeEnum.RULE_CODE_" + ruleCode + ")\n" +
                "public class RuleConfig_" + ruleCode + " extends BaseConfig {\n" +
                "\n" +
                "    @Override\n" +
                "    public RuleConfigBO getRuleConfigBO() {\n" +
                "        RuleConfigBO ruleConfigBO = new RuleConfigBO();\n" +
                "        ArrayList<RuleDetailBO> conditions = new ArrayList<>();\n" +
                "        ruleConfigBO.setConditions(conditions);\n" +
                "        ruleConfigBO.setReturn(false);\n" +
                "        return ruleConfigBO;\n" +
                "    }\n" +
                "\n" +
                "}\n";

        File file = new File("./config", "RuleConfig_" + ruleCode + ".java");
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }
}
