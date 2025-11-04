package com.chery.gb.realtime.algorithm;

/**
 * 实时数据清洗入口
 */
public class LocalRealtimeAlgorithmApplication extends RealtimeAlgorithmApplication {

    public static void main(String[] args) throws Exception {
        String configFile = "application-local.yml";
        run(configFile);
    }

} 