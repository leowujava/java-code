package com.chery.gb.realtime.algorithm.util;

import cn.hutool.core.util.StrUtil;
import com.chery.gb.realtime.algorithm.enums.RuleSymbolEnum;

import java.util.Objects;

/**
 * @author zhonghua
 * @date 2025/6/18
 */
public class CompareUtil {

    /**
     * 根据规则运算符号比较值
     */
    public static boolean compare(String symbol, String ruleValue, Object signalValue) {
        if(RuleSymbolEnum.EQ.name().equals(symbol)){

            if(StrUtil.isBlank(ruleValue)){
                if(Objects.isNull(signalValue) || StrUtil.isBlank(signalValue.toString())){
                    return true;
                }
                return false;
            }

            if(Objects.isNull(signalValue) || StrUtil.isBlank(signalValue.toString())){
                return false;
            }

            if(Double.parseDouble(signalValue.toString()) == Double.parseDouble(ruleValue)){
                return true;
            }
        }

        if(RuleSymbolEnum.LT.name().equals(symbol)){
            if(Objects.isNull(signalValue) || StrUtil.isBlank(signalValue.toString().trim())){
                return false;
            }

            if(Objects.isNull(ruleValue) || StrUtil.isBlank(ruleValue.trim())){
                return false;
            }

            if(Double.parseDouble(signalValue.toString()) < Double.parseDouble(ruleValue)){
                return true;
            }
        }

        if(RuleSymbolEnum.LE.name().equals(symbol)){

            if(Objects.isNull(signalValue) || StrUtil.isBlank(signalValue.toString().trim())){
                return false;
            }

            if(Objects.isNull(ruleValue) || StrUtil.isBlank(ruleValue)){
                return false;
            }

            if(Double.parseDouble(signalValue.toString()) <= Double.parseDouble(ruleValue)){
                return true;
            }
        }

        if(RuleSymbolEnum.GT.name().equals(symbol)){

            if(Objects.isNull(signalValue) || StrUtil.isBlank(signalValue.toString().trim())){
                return false;
            }

            if(Objects.isNull(ruleValue) || StrUtil.isBlank(ruleValue)){
                return false;
            }

            if(Double.parseDouble(signalValue.toString()) > Double.parseDouble(ruleValue)){
                return true;
            }
        }

        if(RuleSymbolEnum.GE.name().equals(symbol)){

            if(Objects.isNull(signalValue) || StrUtil.isBlank(signalValue.toString().trim())){
                return false;
            }

            if(Objects.isNull(ruleValue) || StrUtil.isBlank(ruleValue.trim())){
                return false;
            }

            if(Double.parseDouble(signalValue.toString()) >= Double.parseDouble(ruleValue)){
                return true;
            }
        }

        if(RuleSymbolEnum.NE.name().equals(symbol)){
            if(Double.parseDouble(ruleValue) != Double.parseDouble(signalValue.toString())){
                return true;
            }
        }
        return false;
    }
}
