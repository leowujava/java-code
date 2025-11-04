
package com.chery.gb.realtime.algorithm.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则详情数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDetailBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 规则代码
     */
    @JSONField(name = "rule_id")
    private String ruleId;
    @JSONField(name = "signal_id")
    private String signalId;
    @JSONField(name = "signal_rule")
    private String signalRule;
    @JSONField(name = "signal_value")
    private String signalValue;
    @JSONField(name = "detail_relation")
    private String detailRelation;
    @JSONField(name = "rule_status")
    private String ruleStatus;
    /**
     * 比较类型:0:固定值比较;1:2个信号值比较;2:信号列表长度
     */
    @JSONField(name = "compare_type")
    private Integer compareType;
    /**
     * 信号的值
     */
    private Object compareSignalValue;
    private Object vinSignalValue;
    private String group;
    private Integer level;
}
