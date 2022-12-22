package com.example.demo;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

@SpringBootApplication
public class DemoApplication {
    private static final String TOPIC_NAME = "study";

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
