package com.example.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListenerContainer;

import java.util.Collection;

@Slf4j
public class KafkaErrorHandler implements CommonErrorHandler {


    @Override
    public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        Collection<TopicPartition> assignedPartitions = container.getAssignedPartitions();
        log.info("error");
        ContainerProperties containerProperties = container.getContainerProperties();
        consumer.seekToEnd(assignedPartitions);
        consumer.assignment();
    }

    @Override
    public void handleRecord(
            Exception thrownException,
            ConsumerRecord<?, ?> record,
            Consumer<?, ?> consumer,
            MessageListenerContainer container) {
        log.warn("Global error handler for message: {}", record.value().toString());
    }
}