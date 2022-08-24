package com.example.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
}

@RestController
@RequestMapping("api/v1")
public class TopicController {

	@Autowired
	TopicProducer topicProducer;
	
	@PostMapping("/sendMessge")
	public void sendMessageToTopic(@RequestBody TopicMessage msg) throws InterruptedException {
		for(int counter = 0; counter < 100000; counter ++ ) {
			topicProducer.sendMessage(msg.getMessage() + " ( " + counter + " )");
		}
		
	}
}
