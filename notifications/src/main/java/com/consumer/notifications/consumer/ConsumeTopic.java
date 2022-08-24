package com.consumer.notifications.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Component;


@Component
public class ConsumeTopic {

	@Autowired
	KafkaTopicNameProvider kafkaTopicNameProvider;

	@KafkaListener(topics = "#{kafkaTopicNameProvider.provideName()}", groupId = "groupid")
	void listener(String data, ConsumerRecordMetadata meta) throws InterruptedException {
      System.out.println( meta.topic() + " [ " + meta.partition() + " ]: "  + data);
    }
}
