package com.example.demo.handler;

import com.example.demo.dto.ProductInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListenerContainer;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaErrorHandler implements CommonErrorHandler {

    private final KafkaTemplate<String, ProductInfo> kafkaTemplate;

    @Override
    public void handleOtherException(
            Exception thrownException,
            Consumer<?, ?> consumer,
            MessageListenerContainer container,
            boolean batchListener)
    {
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
            MessageListenerContainer container)
    {
        log.error("Global error handler for message: {}", record.value().toString());
    }
}