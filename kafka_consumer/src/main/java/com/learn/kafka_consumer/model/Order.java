package com.learn.kafka_consumer.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class Order {

    private String id;
    private Double price;
    private String email;

    private String productName;
}
