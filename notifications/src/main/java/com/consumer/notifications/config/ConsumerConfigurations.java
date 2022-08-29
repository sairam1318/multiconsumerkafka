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
import com.consumer.notifications.consumer.UserDetailsDeSerializer;

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
        props.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, 2000);
        return new DefaultKafkaConsumerFactory<>(props);
    }
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory(ConsumerFactory<String, String> consumerFactory, KafkaTemplate<String, String> kafkaTemplate) {
	    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory);
	    return factory;
	}
	
	@Bean
    public ConsumerFactory<String, String> filterConsumerfactory() {
		Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // proper De serializer is required for filtering..
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String>
	  filterKafkaListenerContainerFactory(KafkaTemplate<String, TopicMessage> kafkaTemplate) {

	    ConcurrentKafkaListenerContainerFactory<String, String> factory =
	      new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(filterConsumerfactory());
	  
	    factory.setAckDiscarded(true); // optional
	    factory.setRecordFilterStrategy(
	      record -> record.value().contains("H")); // to apply filter strategy
	    factory.setReplyTemplate(kafkaTemplate); // to use return option..
	    return factory;
	}
	
//	@Bean
//    public ConsumerFactory<String, UserDetails> usersConsumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UserDetailsDeSerializer.class);
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
//

}
