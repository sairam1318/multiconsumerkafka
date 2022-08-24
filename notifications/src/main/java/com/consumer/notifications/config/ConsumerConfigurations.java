package com.consumer.notifications.config;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import com.consumer.notifications.consumer.UserDetails;

@Configuration
@EnableKafka
public class ConsumerConfigurations {

	@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
	
	@Bean
    public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory(ConsumerFactory<String, String> consumerFactory) {
	    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory);
	    return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String>
	  filterKafkaListenerContainerFactory() {

	    ConcurrentKafkaListenerContainerFactory<String, String> factory =
	      new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    factory.setAckDiscarded(true);
//	    factory.setRecordFilterStrategy(
//	      record -> record.value().contains("H"));
	    return factory;
	}
	
//	@Bean
//    public ConsumerFactory<String, UserDetails> usersConsumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UserDetails.class);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//	
//	@Bean
//    public ConcurrentKafkaListenerContainerFactory<String, UserDetails> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, UserDetails> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(usersConsumerFactory());
//        return factory;
//    }
	
	 	

}
