package com.kafka.notification_service.model.dto;

import com.kafka.notification_service.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderNotification {

    private  String orderId;
    private  String productName;
    private  BigDecimal price;
    private  String email;
    private OrderStatusEnum orderStatus;

}
