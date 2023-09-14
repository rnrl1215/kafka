package com.example.demo.service;

import com.example.demo.dto.ProductInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "study";

    public void sendMessage(ProductInfo productInfo) {
        kafkaTemplate.send(TOPIC, productInfo);
    }
}
