package com.chery.gb.realtime.algorithm.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 信号配置
 *
 * @author wugaoyang
 * @date 2025/10/13 星期一
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignalConfigBO {
    /**
     * 倍数
     */
    private Integer scale;
    /**
     * 保留小数点
     */
    private Integer point;
    /**
     * 偏移量
     */
    private Integer offset;
    /**
     * 无效值
     */
    private String invalidValue;
    /**
     * 异常值
     */
    private String errorValue;
    /**
     * 有效范围
     */
    private List<Integer> range;
    /**
     * 最大值
     */
    private Integer max;
    /**
     * 最小值
     */
    private Integer min;
}
