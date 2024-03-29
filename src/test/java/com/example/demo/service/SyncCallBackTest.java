package com.example.demo.service;

import com.example.demo.config.KafkaProducerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(KafkaProducerConfig.class)
class SyncCallBackTest {

    @Autowired
    private SyncCallBack syncCallBack;

    @Test
    void send() {
        syncCallBack.syncCallBack();
    }
}