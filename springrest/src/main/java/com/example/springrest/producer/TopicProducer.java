package com.example.springrest.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.kafka.support.SendResult;

@Service
public class TopicProducer {
	
	@Value("${kafka_topic}")
    private String kafkaTopic;
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	

	
	
	public void sendMessage(String message, int partition) {
//		ListenableFuture<SendResult<String, String>> future =  kafkaTemplate.send(kafkaTopic, message);
//		
//		
//		// Non Blocking...
//		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//
//			@Override
//			public void onSuccess(SendResult<String, String> result) {
//				// TODO Auto-generated method stub
////				System.out.println("Success..." + result.getRecordMetadata().topic());
//				
//			}
//
//			@Override
//			public void onFailure(Throwable ex) {
//				// TODO Auto-generated method stub
////				System.out.println("failure while t/f msgs" + ex.getMessage());
//			}
//			
//		});
//		
//	}
		// to specific partiton..
		kafkaTemplate.send(kafkaTopic, partition, null, message);

	}
	

}
