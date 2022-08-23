package com.example.springrest.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicProducer {
	
	
	@Autowired
	private KafkaTemplate<String,String> KafkaTemplate;
	
	
	public void sendMessage(String message) {
		
		this.KafkaTemplate.send("subscribe", message);
	}

}
