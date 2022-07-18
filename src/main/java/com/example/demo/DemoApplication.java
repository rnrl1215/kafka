package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Properties configs = new Properties();
		configs.put("bootstrap.servers", "3.91.239.118:9092");
		configs.put("session.timeout.ms", "1000");
		configs.put("group.id", "test_log");
		configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");    // key deserializer
		configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  // value deserializer

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);
		consumer.subscribe(Arrays.asList("test_log")); // topic 설정

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(500);
			for (ConsumerRecord<String, String> record : records) {
				String input = record.topic();
				if ("test_log".equals(input)) {
					System.out.println(record.value());
				} else {
					throw new IllegalStateException("get message on topic " + record.topic());
				}
			}
		}
	}

}
