package com.consumer.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {
	
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	KafkaTopicNameProvider kafkaTopicNameProvider;
		

	public TopicConsumer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	// subscribe

	@KafkaListener(topics = "#{kafkaTopicNameProvider.provideName()}", groupId = "groupid")
	void listener(String data) throws InterruptedException {
      System.out.println( data);
      sendNotitfication();
    }

	private void sendNotitfication() throws InterruptedException {
		Thread.sleep(10000);
		String nextTopic = kafkaTopicNameProvider.getNextTopic();
		if(nextTopic != null && nextTopic.length() > 0) {
			kafkaTemplate.send(nextTopic, "next topic process...");
		}
		
	}
}
