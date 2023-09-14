package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncCallBack {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void syncCallBack() {
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send("sync-callback", "key1", "test");
        try {
            SendResult<String, Object> stringStringSendResult = send.get();
            log.info("Send Result: {}", stringStringSendResult.toString());
        } catch (Exception e) {
            log.info("Exception: {}", e);
        }
    }
}
