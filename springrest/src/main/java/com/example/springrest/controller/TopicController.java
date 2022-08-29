package com.example.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrest.producer.TopicProducer;

class TopicMessage {
	private String message;
	
	public TopicMessage() {
		
	}

	public TopicMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "{ \"message\" : \"" + message + "\" }";
	}

}

@RestController
@RequestMapping("api/v1")
public class TopicController {

	@Autowired
	TopicProducer topicProducer;
	
	
	@PostMapping("/sendMessge")
	public void sendMessageToTopic(@RequestBody TopicMessage msg) throws InterruptedException {
		for(int counter = 0; counter < 20; counter ++ ) {
			
			// here 10 is no.of partitions.
			topicProducer.sendMessage(msg.toString() + " [ " + counter + " ]", counter % 10);
			Thread.sleep(100);
		}
	}
}
