package com.learn.kafka_producer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerTwo {

    @KafkaListener(
            topics = "rushi",
            groupId = "groupId"
    )
    void listener(String data){
        System.out.println("KafkaListenerTwo"+"=======" + data);
    }
}
