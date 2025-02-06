package com.kafka.order_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.order_service.entities.OrderEntity;
import com.kafka.order_service.enums.OrderStatusEnum;
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
public class OrderService {

      private  final KafkaTemplate kafkaTemplate;

      private final ObjectMapper objectMapper;

    public OrderService(KafkaTemplate kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public CustomResponse createOrder(CreateOrderRequest createOrderRequest){
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(UUID.randomUUID().toString() +"_"+createOrderRequest.productName());
            orderEntity.setEmail(createOrderRequest.email());
            orderEntity.setPrice(createOrderRequest.price());
            orderEntity.setOrderStatus(OrderStatusEnum.PENDING);

            OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.prepareOrderCreateEvent(orderEntity);
        try {
            String kafkaSendData = objectMapper.writeValueAsString(orderCreatedEvent);
            kafkaTemplate.send("rushi",orderEntity.getOrderId(), kafkaSendData);
        } catch (Exception e) {
              e.printStackTrace();
            throw new RuntimeException(e);
        }
        return CustomResponse.success(null,"order");
      }
}
