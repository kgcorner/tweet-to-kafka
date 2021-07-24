package com.kgcorner.tweettokafka.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@Configuration
@Profile("local")
public class AutoCreateConfig {

    @Bean
    public NewTopic tweetTopic() {
        return TopicBuilder.name("trending-tweets")
            .partitions(3)
            .replicas(3)
            .build();
    }
}