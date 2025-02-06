package com.kafka.order_service.model.event;

import com.kafka.order_service.entities.OrderEntity;
import com.kafka.order_service.enums.OrderStatusEnum;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Setter
@Getter

@AllArgsConstructor
public final class OrderCreatedEvent  implements Serializable {
    private final String orderId;
    private final String productName;
    private final BigDecimal price;
    private final String email;
    private final OrderStatusEnum orderStatus;



    public static OrderCreatedEvent prepareOrderCreateEvent(OrderEntity orderEntity) {
        return builder()
                .orderId(orderEntity.getOrderId())
                .productName(orderEntity.getProductName())
                .price(orderEntity.getPrice())
                .email(orderEntity.getEmail())
                .orderStatus(orderEntity.getOrderStatus())
                .build();
    }




}
