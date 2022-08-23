package com.example.springrest.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicBuilderConfig {
	

    @Bean
    public NewTopic dthTopic() {
    	// if partion's are added, then works will be shared across each consumer.
    	//  return TopicBuilder.name("dthSubscription").partitions(3).replicas(3).build();
        return TopicBuilder.name("subscribe").build();
    }
}
