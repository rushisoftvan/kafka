package com.kafka.order_service.repo;

import com.kafka.order_service.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity,Long> {



}
