package com.kafka.order_service;

import com.kafka.order_service.services.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(OrderServiceApplication.class, args);
		OrderService bean = run.getBean(OrderService.class);
		


	}

}
