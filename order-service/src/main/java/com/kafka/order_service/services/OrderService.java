package com.kafka.order_service.services;

import com.kafka.order_service.entities.OrderEntity;
import com.kafka.order_service.model.event.OrderCreatedEvent;
import com.kafka.order_service.model.request.CreateOrderRequest;
import com.kafka.order_service.model.response.CustomResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class OrderService {

      private  final KafkaTemplate kafkaTemplate;

      public CustomResponse createOrder(CreateOrderRequest createOrderRequest){
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(UUID.randomUUID().toString() +"_"+createOrderRequest.productName());
            orderEntity.setEmail(createOrderRequest.email());
            orderEntity.setPrice(createOrderRequest.price());
            orderEntity.setOrderStatus("pending");
            OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.prepareOrderCreateEvent(orderEntity);
            kafkaTemplate.send(orderEntity.getOrderId(), orderCreatedEvent);
            return null;
      }
}
