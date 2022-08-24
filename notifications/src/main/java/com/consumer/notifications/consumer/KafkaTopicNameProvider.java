package com.consumer.notifications.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopicNameProvider {
    
    @Value("${kafka_topic}")
    private String kafkaTopic;

    public String provideName() {
        return kafkaTopic;
    }

}
