package com.example.demo.service;

import com.example.demo.dto.MessageTemplate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, MessageTemplate> kafkaTemplate;

    private static final String TOPIC = "study";

    public void sendMessage(String message) {
        MessageTemplate messageTemplate = new MessageTemplate("Hi", 1000);
        kafkaTemplate.send(TOPIC, messageTemplate);
    }
}
