package com.kafka.order_service.controller;


import com.kafka.order_service.model.request.CreateOrderRequest;
import com.kafka.order_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order-service")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private  OrderService orderService;

    @PostMapping("/add")
    public void createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        this.orderService.createOrder(createOrderRequest);
    }
}
