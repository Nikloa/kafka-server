package ru.vironit.kafkaserver.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

public class KafkaTopicConfig {

    @Value(value = "${kafka.server}")
    private String server;

    @Bean
    public NewTopic firstTopic() {
        return TopicBuilder.name("1")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic secondTopic() {
        return TopicBuilder.name("2")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean NewTopic thirdTopic() {
        return TopicBuilder.name("3")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        return new KafkaAdmin(configs);
    }
}
