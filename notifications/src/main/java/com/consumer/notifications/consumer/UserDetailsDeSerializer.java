package com.consumer.notifications.consumer;

import org.apache.kafka.common.ClusterResource;
import org.apache.kafka.common.ClusterResourceListener;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDetailsDeSerializer implements Deserializer<UserDetails>, ClusterResourceListener {
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

	@Override
	public void onUpdate(ClusterResource clusterResource) {
		// TODO Auto-generated method stub
		
		// to use metadata..
		
	}
}
