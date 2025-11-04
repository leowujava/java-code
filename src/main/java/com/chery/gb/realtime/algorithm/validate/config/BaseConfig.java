package com.chery.gb.realtime.algorithm.validate.config;


import com.chery.gb.realtime.algorithm.bo.RuleConfigBO;
import com.chery.gb.realtime.algorithm.bo.RuleDetailBO;

import java.util.List;

/**
 * @author wugaoyang
 * @date 2025/11/4 星期二
 *
 */
public abstract class BaseConfig {

    public abstract RuleConfigBO getRuleConfigBO();

    public List<RuleDetailBO> getCondition() {
        return getRuleConfigBO().getConditions();
    }

    public List<RuleDetailBO> getPreCondition() {
        return getRuleConfigBO().getPreConditions();
    }

    public boolean isReturn() {
        return getRuleConfigBO().isReturn();
    }

}
