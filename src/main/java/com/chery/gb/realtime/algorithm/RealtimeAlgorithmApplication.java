package com.chery.gb.realtime.algorithm;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.chery.gb.realtime.algorithm.enums.EnvEnum;
import com.chery.gb.realtime.algorithm.util.KafkaEnvUtil;
import com.chery.gb.realtime.algorithm.util.MysqlEnvUtil;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.List;
import java.util.Map;

/**
 * 实时数据清洗入口
 */
public class RealtimeAlgorithmApplication {

    public static void main(String[] args) throws Exception {
        run(null);
    }

    protected static void run(String configFile) throws Exception {
        MySqlSource<String> mySqlSource = MysqlEnvUtil.getMySqlSource(configFile);
        FlinkKafkaConsumer<String> flinkKafkaConsumer = KafkaEnvUtil.getFlinkKafkaConsumer(configFile);
        FlinkKafkaProducer flinkKafkaProducer = KafkaEnvUtil.getFlinkKafkaProducer(configFile);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);

        // 定义广播状态描述符
        MapStateDescriptor<String, Map<String, List<String>>> dimensionDescriptor = new MapStateDescriptor<>(
                "dimensionState", TypeInformation.of(String.class), TypeInformation.of(new TypeHint<Map<String, List<String>>>() {
        }));

        // 1. 创建MySQL CDC源，捕获维度表变更。从 MySQL 读取维度数据并监听变化
        DataStream<String> dimensionStream = env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "mysql-source");

        // 2. 将维度数据广播到所有并行实例
        BroadcastStream<String> broadcastDimensionStream = dimensionStream.broadcast(dimensionDescriptor);

        // 3. 创建主数据流，从Kafka读取原始数据
        DataStream<String> mainStream = env.addSource(flinkKafkaConsumer, "mainStream-flink-kafka");

        // 4. 关联维度数据并清洗
        SingleOutputStreamOperator<String> dimensionEnrichment =
                mainStream
                        .keyBy(record -> extractVinFromRecord(record))
                        .connect(broadcastDimensionStream)
                        .process(new DimensionDataCleaner(dimensionDescriptor))
                        .name("Dimension Enrichment")
                        .uid("dimension-enrichment");

        //5.生产数据
        dimensionEnrichment.addSink(flinkKafkaProducer);
        env.execute("Flink CDC Local Map Dimension Join Job");
    }

    // 从记录中提取VIN作为key
    private static String extractVinFromRecord(String record) {
        try {
            JSONObject json = JSON.parseObject(record);
            return json.getString("vin");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to extract VIN from record", e);
        }
    }

} 