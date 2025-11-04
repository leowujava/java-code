package com.chery.gb.realtime.algorithm.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wugaoyang
 * @date 2025/9/28 星期日
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MySqlConfigBO {
    String hostname;
    int port;
    String username;
    String password;
    String database;
    String table;
}
