package com.kafka.order_service.entities;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.kafka.order_service.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEntity extends BaseEntity {


    @Column(name =  "order_id")
   private String orderId;

    @Column(name="product_name")
   private  String productName;

    @Column(name="price")
  private  BigDecimal price;

    @Column(name="email")
  private  String email;

    @Column(name="order_status")
    OrderStatusEnum OrderStatus;
}
