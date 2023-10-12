package com.example.demo.config;

import com.example.demo.dto.ProductInfo;
import com.example.demo.handler.KafkaErrorHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.groupid}")
    private String groupId;

    @Value("${spring.kafka.topics.test}")
    private String topic;

    private final KafkaErrorHandler kafkaErrorHandler;

    @Bean
    public ConsumerFactory<String, ProductInfo> consumerFactory() {
        Map<String,Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, bootstrapServers);
       return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(),
                new JsonDeserializer<>(ProductInfo.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductInfo> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProductInfo> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setCommonErrorHandler(kafkaErrorHandler);
        return factory;
    }
}
