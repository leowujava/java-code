package com.chery.gb.realtime.algorithm.util;


import com.chery.gb.realtime.algorithm.bo.MySqlConfigBO;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;

/**
 * @author wugaoyang
 * @date 2025/9/28 星期日
 *
 */
public class MysqlEnvUtil {

    public static MySqlSource<String> getMySqlSource(String configFile) {
        return getMySqlSource(YmlReader.readMySqlConfigBO(configFile));
    }

    /**
     * 获取mysql数据源
     */
    public static MySqlSource<String> getMySqlSource(MySqlConfigBO mySqlConfigBO) {
        String hostname = mySqlConfigBO.getHostname();
        int port = mySqlConfigBO.getPort();
        String username = mySqlConfigBO.getUsername();
        String password = mySqlConfigBO.getPassword();
        String database = mySqlConfigBO.getDatabase();
        String table = mySqlConfigBO.getTable();
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname(hostname)
                .port(port)
                .username(username)
                .password(password)
                .databaseList(database)
                .tableList(table) //在写表时，需要带上库名。如果什么都不写，则表示监控所有的表
                .startupOptions(StartupOptions.initial())
                .deserializer(new JsonDebeziumDeserializationSchema())
                .build();
        return mySqlSource;
    }

}
