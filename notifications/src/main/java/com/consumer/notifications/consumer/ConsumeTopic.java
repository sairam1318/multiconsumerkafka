package com.consumer.notifications.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


@Component
public class ConsumeTopic {

	@Autowired
	KafkaTopicNameProvider kafkaTopicNameProvider;
	
	
	@Autowired
	private KafkaListenerEndpointRegistry registry;
	
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
	public String listener(String data, ConsumerRecordMetadata meta) throws InterruptedException {
      System.out.println( meta.topic() + " [" + meta.partition() + "]: ---->  "  + data );
//      registry.getListenerContainer("189").start();
//      registry.getAllListenerContainers().forEach(con -> System.out.println(con.getListenerId()));
      return data.toLowerCase();
    }
	
//	@KafkaListener(
//			topics = "process", 
//			groupId = "groupid"
//	)
//	void anotherTopicListener(String data, ConsumerRecordMetadata meta) throws InterruptedException {
//      System.out.println( data + " from another topic... ");
//    }
}
