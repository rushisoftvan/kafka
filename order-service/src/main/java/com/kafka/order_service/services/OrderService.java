package com.kafka.order_service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.order_service.entities.OrderEntity;
import com.kafka.order_service.entities.OutboxCreatOrderEventEntity;
import com.kafka.order_service.enums.OrderStatusEnum;
import com.kafka.order_service.model.event.OrderCreatedEvent;
import com.kafka.order_service.model.request.CreateOrderRequest;
import com.kafka.order_service.model.response.CustomResponse;
import com.kafka.order_service.repo.OrderEventRepo;
import com.kafka.order_service.repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
@Transactional(readOnly = true)
@Slf4j
public class OrderService {

    private final KafkaTemplate kafkaTemplate;

    private final ObjectMapper objectMapper;

    private final OrderRepo orderRepo;

    private final OrderEventRepo orderEventRepo;


    public OrderService(KafkaTemplate kafkaTemplate, ObjectMapper objectMapper, OrderRepo orderRepo, OrderEventRepo orderEventRepo) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.orderRepo = orderRepo;
        this.orderEventRepo = orderEventRepo;
    }

    @Transactional
    public CustomResponse createOrder(CreateOrderRequest createOrderRequest) {
        try {
            OrderEntity orderEntity = new OrderEntity();
            String orderId = UUID.randomUUID().toString() + "_" + createOrderRequest.productName();
            orderEntity.setOrderId(orderId);
            orderEntity.setEmail(createOrderRequest.email());
            orderEntity.setPrice(createOrderRequest.price());
            orderEntity.setProductName(createOrderRequest.productName());

            orderEntity.setOrderStatus(OrderStatusEnum.PENDING);
          //  System.out.println("orderEntity" + orderEntity.toString());
            orderRepo.save(orderEntity);
            OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.prepareOrderCreateEvent(orderEntity);
            OutboxCreatOrderEventEntity outboxCreatOrderEventEntity = new OutboxCreatOrderEventEntity();
            outboxCreatOrderEventEntity.setOrderPayLoad(objectMapper.writeValueAsString(orderCreatedEvent));
            outboxCreatOrderEventEntity.setOrderId(orderId);
            outboxCreatOrderEventEntity.setEventProcessed(false);
            orderEventRepo.save(outboxCreatOrderEventEntity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


//        try {
//            String kafkaSendData = objectMapper.writeValueAsString(orderCreatedEvent);
//            kafkaTemplate.send("rushi",orderEntity.getOrderId(), kafkaSendData);
//        } catch (Exception e) {
//              e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//        return CustomResponse.success(null,"order");
//      }

        return CustomResponse.success(null, "m");

    }


    @Scheduled(cron = "*/10 * * * * *")
    public void sendDataToKafka(){
        log.info("scheduler start ::  --------------------------");
       var outboxCreatOrderEventEntities = this.orderEventRepo.fetchOrderEven();

        for(OutboxCreatOrderEventEntity  orderEvent: outboxCreatOrderEventEntities){
            kafkaTemplate.send("rushi",orderEvent.getOrderPayLoad());
            orderEvent.setEventProcessed(true);

            orderEventRepo.save(orderEvent);
        }
        log.info("sheduller end :: ------------------------------");
    }
}

