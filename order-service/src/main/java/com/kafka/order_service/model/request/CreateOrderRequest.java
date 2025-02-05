package com.kafka.order_service.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateOrderRequest(

        @NotEmpty
        String productName,

        @NotNull
        Integer quantity,

        @NotNull
        BigDecimal price,

        @Email
        String email) {

}
