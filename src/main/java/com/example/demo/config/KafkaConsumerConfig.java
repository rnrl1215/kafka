package com.example.demo.config;

import com.example.demo.dto.MessageTemplate;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, MessageTemplate> consumerFactory() {
        Map<String,Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
       return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(),
                new JsonDeserializer<>(MessageTemplate.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageTemplate> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageTemplate> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
