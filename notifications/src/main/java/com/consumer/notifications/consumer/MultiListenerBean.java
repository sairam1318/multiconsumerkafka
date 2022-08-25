package com.consumer.notifications.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

@KafkaListener(id = "189", topics = "multi", groupId = "groupid")
public class MultiListenerBean {

	@KafkaHandler(isDefault = true)
    public void listen(String foo) {
        System.out.println(foo);
    }

	@KafkaHandler
    public void listen(Integer num) {
        System.out.println(num);
    }
}
