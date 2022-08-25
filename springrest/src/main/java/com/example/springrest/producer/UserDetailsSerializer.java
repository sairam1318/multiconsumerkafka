package com.example.springrest.producer;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDetailsSerializer implements Deserializer<UserDetails> {

	@Override
	public UserDetails deserialize(String topic, byte[] data) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		UserDetails userPayload = null;
		try {
			userPayload = mapper.readValue(data, UserDetails.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userPayload;
	}

}
