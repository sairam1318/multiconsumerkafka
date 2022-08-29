package com.consumer.notifications.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.consumer.notifications.config.TopicMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class ConsumeTopic {

	@Autowired
	KafkaTopicNameProvider kafkaTopicNameProvider;
	
	
	@Autowired
	private KafkaListenerEndpointRegistry registry;
	
	@Autowired
	private KafkaAdmin admin;

//
//	@KafkaListener(
//			topics = "#{kafkaTopicNameProvider.provideName()}", 
//			groupId = "groupid", 
//			containerFactory = "filterKafkaListenerContainerFactory"
//	)
//	@SendTo("process")
	
	@KafkaListener(
			topics = "#{kafkaTopicNameProvider.provideName()}", 
			groupId = "groupid")
//	)
	public TopicMessage listener(String data, ConsumerRecordMetadata meta) throws InterruptedException, JsonMappingException, JsonProcessingException {
      System.out.println( meta.topic() + " [" + meta.partition() + "]: ---->  "  + data + " " + meta.serializedKeySize());
//      registry.getListenerContainer("189").start();
//      registry.getAllListenerContainers().forEach(con -> System.out.println(con.getListenerId()));
      	
      registry.getAllListenerContainers().forEach(con -> System.err.println(con.getListenerId() + " id " + con.toString()));
      System.out.println(admin.DEFAULT_CLOSE_TIMEOUT);
      ObjectMapper mapper = new ObjectMapper();
      TopicMessage  msg = mapper.readValue(data, TopicMessage.class);  
      return msg;
    }
	
//	@KafkaListener(
//			topics = "process", 
//			groupId = "groupid"
//	)
//	void anotherTopicListener(String data, ConsumerRecordMetadata meta) throws InterruptedException {
//      System.out.println( data + " from another topic... ");
//    }
}
