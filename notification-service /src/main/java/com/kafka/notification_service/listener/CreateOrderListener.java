package com.kafka.notification_service.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.notification_service.model.dto.CreateOrderNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderListener {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "rushi", groupId = "notification-group-id" )
  public  void listenCreateOrderDetails(String orderDetail){

//        objectMapper.readValues(orderDetail, CreateOrderNotification.class);

        try {
            CreateOrderNotification createOrderNotification = objectMapper.readValue(orderDetail, CreateOrderNotification.class);
          System.out.println(createOrderNotification.toString());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }



}
