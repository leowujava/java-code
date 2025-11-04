package com.chery.gb.realtime.algorithm.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wugaoyang
 * @date 2025/10/13 星期一
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertBO {
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
}
