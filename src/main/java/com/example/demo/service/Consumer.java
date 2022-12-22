package com.example.demo.service;


import com.example.demo.dto.MessageTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer {

    @KafkaListener(topics = "study", groupId = "study-1")
    public void processMessage(ConsumerRecord<String, Object> record) {
        Object value = record.value();
        log.info("Get group1 message!!! :" + value);
        throw new IllegalArgumentException();
    }
}
