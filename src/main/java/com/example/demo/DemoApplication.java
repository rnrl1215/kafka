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

        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, TOPIC_NAME);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());    // key deserializer
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());  // value deserializer

        Consumer<String, String> consumer = new KafkaConsumer<String, String>(configs);
        consumer.subscribe(Collections.singletonList(TOPIC_NAME)); // topic 설정

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String, String> record : records) {
                String input = record.topic();
                if (TOPIC_NAME.equals(input)) {
                    System.out.println(record.value());
                } else {
                    throw new IllegalStateException("get message on topic " + record.topic());
                }
            }
        }
    }
}
