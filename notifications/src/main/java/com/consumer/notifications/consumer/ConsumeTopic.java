package com.consumer.notifications.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumeTopic {

	@KafkaListener(topics = "payment", groupId = "groupid")
	void listener(String data) throws InterruptedException {
      System.out.println(data);
    }
}
