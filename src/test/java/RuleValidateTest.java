import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.bo.SignalBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.rule.factory.RuleValidateFactory;
import com.chery.gb.realtime.algorithm.util.check.NewGBRuleDataCheckUtil;
import com.chery.gb.realtime.algorithm.rule.validator.BaseRuleValidator;
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

        BaseRuleValidator ruleValidate = RuleValidateFactory.getRuleValidate(ruleCode1.getCode());
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
        HashMap<String, Object> signalMap = JSON.parseObject(JSON.toJSONString(tmpl), HashMap.class);
        HashMap<String, Map<String, List<RuleDetailBO>>> ruleMap = new HashMap<>();
        NewGBRuleDataCheckUtil.checkDataFromConfig(signalMap, ruleMap, retData);
        System.out.println(retData);
    }
}
