package com.example.demo.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaErrorHandler implements CommonErrorHandler {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topics.dead-letter-queue}")
    private String dlqTopic;

    @Override
    public void handleOtherException(
            Exception thrownException,
            Consumer<?, ?> consumer,
            MessageListenerContainer container,
            boolean batchListener)
    {
        Collection<TopicPartition> assignedPartitions = container.getAssignedPartitions();
        log.info("error");
        consumer.seekToEnd(assignedPartitions);
        consumer.assignment();;
    }

    @Override
    public void handleRecord(
            @NotNull Exception thrownException,
            ConsumerRecord<?, ?> record,
            @NotNull Consumer<?, ?> consumer,
            @NotNull MessageListenerContainer container)
    {
        kafkaTemplate.send(dlqTopic, record.value());
        log.error("Global error handler for message: {}", record.value().toString());
    }
}