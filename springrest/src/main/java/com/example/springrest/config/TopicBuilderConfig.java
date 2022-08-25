package com.example.springrest.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class TopicBuilderConfig {

    @Value("${kafka_topic}")
    private String kafkaTopic;

    @Value("${partitions_count}")
    private String partitionsCount;
	
//    @Autowired
//    private KafkaAdmin admin;

    @Bean
    public NewTopic dthTopic() {
    	// if partion's are added, then works will be shared across each consumer.
    	//  return TopicBuilder.name("dthSubscription").partitions(3).replicas(3).build();
    	

    	/*
    	 * The replication factor determines the number of copies that must be held of the partition.
    	 * There are two different types of replicas:
    	 * Leader Replica: 
    	 * 	Each partition is designated as the leader with a single replica. 
    	 * 	All demands for producing and consuming go through the leader to ensure consistency.
    	 * Followers Replica: 
    	 * 	All replicas are called followers for a partition that is not leaders. 
    	 * 	Followers do not answer client requests; their only task is to replicate the leader’s messages and keep up to date with the leader’s most recent messages. 
    	 *  If a leader replica crashes for a partition, then one of the follower replicas will be promoted to become the new partition leader. */
        int count = Integer.parseInt(partitionsCount);
        
//        AdminClient client = AdminClient.create(admin.getConfigurationProperties());
        

        return TopicBuilder.name(kafkaTopic).partitions(count).build();
    }
    
    @Bean
    public NewTopic processTopic() {
    	return TopicBuilder.name("process").partitions(1).config(TopicConfig.CLEANUP_POLICY_DELETE, "true").build();
    }
}
