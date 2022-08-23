package com.consumer.subscribe;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {
	
	private KafkaTemplate<String, String> kafkaTemplate;
		

	public TopicConsumer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	// subscribe

	@KafkaListener(topics = "subscribe", groupId = "groupid")
	void listener(String data) {
      System.out.println("Hi, " + data + " processing payment of Rs.500 ");
      sendNotitfication();
    }

	private void sendNotitfication() {
		// send to subscribe topic .. will do it later...
		kafkaTemplate.send("payment", "DTH subscription was successful..!");
		
	}
}
