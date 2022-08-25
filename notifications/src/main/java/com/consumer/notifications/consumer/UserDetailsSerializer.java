package com.consumer.notifications.consumer;

import org.apache.kafka.common.serialization.Serializer;

public class UserDetailsSerializer implements Serializer<UserDetails> {

	@Override
	public byte[] serialize(String topic, UserDetails data) {
		// TODO Auto-generated method stub
		String id = data.getUserId();
		return id.getBytes();
	}

}
