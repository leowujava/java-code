package com.chery.gb.realtime.algorithm.bo;


import lombok.Data;

/**
 * @author wugaoyang
 * @date 2025/9/30 星期二
 *
 */
@Data
public class KafkaConfigBO {
    private String consumerTopic;
    private String producerTopic;
    private String groupId;
    private String servers;

}
