import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.bo.SignalBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.rule.factory.RuleValidatorFactory;
import com.chery.gb.realtime.algorithm.rule.validator.BaseRuleValidator;
import com.chery.gb.realtime.algorithm.util.SignalUtil;
import com.chery.gb.realtime.algorithm.util.check.NewGBRuleDataCheckUtil;
import data.BaseDataTest;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public class RuleValidateTest {

    @Test
    public void test() {
        NewGbRuleCodeEnum ruleCode1 = NewGbRuleCodeEnum.RULE_CODE_VEHICLE_STATE_IS_NULL;

        BaseRuleValidator ruleValidate = RuleValidatorFactory.getRuleValidate(ruleCode1.getCode());
        JSONArray retData = new JSONArray();
        if (ruleValidate != null) {
            try {
                ruleValidate.validate(null
                        , null
                        , retData);
            } catch (GbException e) {
                e.printStackTrace();
            }
            System.out.println(retData);
        } else {
            //处理逻辑
            if (ruleCode1.isReturn()) {
                throw new GbException(ruleCode1);
            }
        }
    }

    @Test
    public void test2() throws IOException {
        JSONArray retData = new JSONArray();
        SignalBO tmpl = BaseDataTest.getTmpl();
        tmpl.set_220C(2);
        tmpl.set_2001(60);
        tmpl.set_21AA(0);
        HashMap<String, Object> signalMap = JSON.parseObject(JSON.toJSONString(tmpl), HashMap.class);
        SignalUtil.convert(signalMap);
        System.out.println("处理后的数据：" + JSON.toJSONString(signalMap));
        HashMap<String, Map<String, List<RuleDetailBO>>> ruleMap = new HashMap<>();
        NewGBRuleDataCheckUtil.checkDataFromConfig(signalMap, ruleMap, retData);
        System.out.println(retData);
    }

    @Test
    public void test3() throws IOException {

        JSONArray retData = new JSONArray();
        SignalBO tmpl = BaseDataTest.getTmpl();
        tmpl.set_207A(120000000000L);
        tmpl.set_207B(120000000000L);
        tmpl.set_215D(90000001);
        tmpl.set_215C(90000001);
        HashMap<String, Object> signalMap = JSON.parseObject(JSON.toJSONString(tmpl), HashMap.class);
        SignalUtil.convert(signalMap);
        System.out.println("处理后的数据：" + JSON.toJSONString(signalMap));
        HashMap<String, Map<String, List<RuleDetailBO>>> ruleMap = new HashMap<>();
        NewGBRuleDataCheckUtil.checkDataFromConfig(signalMap, ruleMap, retData);
        System.out.println(retData);
    }

    @Test
    public void test4() throws IOException {

        JSONArray retData = new JSONArray();
        SignalBO tmpl = BaseDataTest.getTmpl();
//        tmpl = new SignalBO();
        tmpl.set_220C(254);
        HashMap<String, Object> signalMap = JSON.parseObject(JSON.toJSONString(tmpl), HashMap.class);
        SignalUtil.convert(signalMap);
        System.out.println("处理后的数据：" + JSON.toJSONString(signalMap));
        HashMap<String, Map<String, List<RuleDetailBO>>> ruleMap = new HashMap<>();
        NewGBRuleDataCheckUtil.checkDataFromWorkFlow(signalMap, ruleMap, retData);
        System.out.println(retData);
    }
}
