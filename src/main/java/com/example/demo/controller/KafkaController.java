package com.example.demo.controller;

import com.example.demo.service.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("message")
public class KafkaController {
    private final Producer producer;

    @GetMapping
    public void sendMessage() {
        producer.sendMessage("TEST");
    }
}
