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
            System.out.println("RULE_CODE_" + split[0] + "(\"" + split[0] + "\",\"" + split[1] +  "\",\"" + split[2]  + "\",\"" + split[3]  + "\",\"" + split[4] +"\"),");
//            System.out.println("newGbRuleCodeEnums.add(RULE_CODE_"+split[0]+");");
            line = br.readLine();
        }
    }
}
