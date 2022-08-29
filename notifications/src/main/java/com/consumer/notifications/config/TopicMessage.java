package com.consumer.notifications.config;

public class TopicMessage {
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
