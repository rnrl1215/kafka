package com.example.demo.controller;

import com.example.demo.dto.ProductInfo;
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
        ProductInfo productInfo = new ProductInfo("MacBookPro", 1000);
        producer.sendMessage(productInfo);
    }
}
