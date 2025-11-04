import com.alibaba.fastjson2.JSONArray;
import com.chery.gb.realtime.algorithm.enums.NewGbRuleCodeEnum;
import com.chery.gb.realtime.algorithm.exception.GbException;
import com.chery.gb.realtime.algorithm.factory.RuleValidateFactory;
import com.chery.gb.realtime.algorithm.util.check.NewGBRuleDataCheckUtil;
import com.chery.gb.realtime.algorithm.validate.rule.BaseRuleValidate;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author wugaoyang
 * @date 2025/11/3 星期一
 *
 */
public class RuleValidateTest {

    @Test
    public void test() {
        NewGbRuleCodeEnum ruleCode1 = NewGbRuleCodeEnum.RULE_CODE_VEHICLE_STATE_IS_NULL;

        BaseRuleValidate ruleValidate = RuleValidateFactory.getRuleValidate(ruleCode1.getCode());
        JSONArray retData = new JSONArray();
        if (ruleValidate != null) {
            try {
                ruleValidate.validate(null
                        , null
                        , retData);
            }catch (GbException e){
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
    public void test2(){
        JSONArray retData = new JSONArray();
        NewGBRuleDataCheckUtil.checkDataFromRule(new HashMap<>(), new HashMap<>(), retData);
    }
}
