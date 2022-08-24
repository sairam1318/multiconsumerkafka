package com.consumer.subscribe;

import org.springframework.beans.factory.annotation.Value;

public class KafkaTopicNameProvider {
    
    @Value("${kafka_topic}")
    private String kafkaTopic;

    @Value("${next_topic}")
    private String nextKafkaTopic;

    public String provideName() {
        return kafkaTopic;
    }

    public String getNextTopic() {
        return nextKafkaTopic;
    }
}
