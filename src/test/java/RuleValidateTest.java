import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.factory.RuleValidateFactory;
import com.chery.gb.realtime.algorithm.util.check.NewGBRuleDataCheckUtil;
import com.chery.gb.realtime.algorithm.rule.validator.BaseRuleValidator;
import org.junit.Test;

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
    public void test2() {
        JSONArray retData = new JSONArray();
        HashMap<String, Object> signalMap = new HashMap<>();
        HashMap<String, Map<String, List<RuleDetailBO>>> ruleMap = new HashMap<>();
        NewGBRuleDataCheckUtil.checkDataFromConfig(signalMap, ruleMap, retData);
    }
}
