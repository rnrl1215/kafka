package com.example.demo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductInfo {
    private String productName;
    private Integer price;

    public ProductInfo(String productName, Integer price) {
        this.productName = productName;
        this.price = price;
    }
}
