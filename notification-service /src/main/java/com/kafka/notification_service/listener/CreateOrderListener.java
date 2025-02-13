package com.kafka.notification_service.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.notification_service.model.dto.CreateOrderNotification;
import com.kafka.notification_service.model.dto.MailDto;
import com.kafka.notification_service.sender.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderListener {

    private final ObjectMapper objectMapper;

    private final EmailSenderService emailSenderService;

    @RetryableTopic(
                attempts = "4",
            backoff = @Backoff(delay = 1000, maxDelay = 5000, multiplier = 2)

    )
    @KafkaListener(topics = "rushi", groupId = "notification-group-id" )
  public  void listenCreateOrderDetails(String orderDetail){

        try {
            CreateOrderNotification createOrderNotification = objectMapper.readValue(orderDetail, CreateOrderNotification.class);

            if (createOrderNotification.getPrice().compareTo(new BigDecimal("200")) == 0) {
                throw new RuntimeException("Price is 200");
            }

             // sendOrderDetailEmailWithDetails(createOrderNotification);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendOrderDetailEmailWithDetails(CreateOrderNotification createOrderNotification) throws JsonProcessingException {
        Map<String, Object> templateVariableNameToValueMap = Map.of(
                "username", "Amee Don (Chota Don)",
                "orderId", createOrderNotification.getOrderId(),
                "orderStatus", createOrderNotification.getOrderStatus()
        );
        MailDto mailDTO = new MailDto("Order Details and Status Update",
                createOrderNotification.getEmail(), templateVariableNameToValueMap,
                "order_detail_status_email");

        System.out.println(objectMapper.writeValueAsString(mailDTO));

        emailSenderService.sendNotification(mailDTO);
    }

    @DltHandler
    @KafkaListener(topics = "rushi", groupId = "notification-group-id" )
    public void handleDeadLetterTopic(String event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        System.out.println("Event moved to Dead Letter Topic: " + event);
        // Log or process failed event further
    }
}
