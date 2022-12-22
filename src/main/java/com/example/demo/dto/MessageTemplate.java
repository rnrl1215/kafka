package com.example.demo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.kafka.common.utils.PrimitiveRef;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MessageTemplate {
    private String message;
    private Integer price;

    public MessageTemplate(String message, Integer price) {
        this.message = message;
        this.price = price;
    }
}
