package com.kafka.order_service.model.event;

import com.kafka.order_service.entities.OrderEntity;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderCreatedEvent(

        String orderId,
        String productName,
        BigDecimal price,
        String email

) {

    public static OrderCreatedEvent prepareOrderCreateEvent(OrderEntity orderEntity){
       return   builder()
                 .orderId(orderEntity.getOrderId())
                 .productName(orderEntity.getProductName())
                 .price(orderEntity.getPrice())
                 .email(orderEntity.getEmail())
                 .build();
    }
}
