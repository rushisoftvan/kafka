package com.kafka.order_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name =  "order_id")
    String orderId;

    @Column(name="product_name")
    String productName;

    @Column(name="price")
    BigDecimal price;

    @Column(name="email")
    String email;

    @Column(name="order_status")
    String OrderStatus;


}
