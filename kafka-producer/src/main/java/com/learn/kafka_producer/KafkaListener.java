package com.learn.kafka_producer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener  implements CommandLineRunner {

          @org.springframework.kafka.annotation.KafkaListener(
                  topics = "rushi",
                  groupId = "groupId"

          )
        void listener(String data){
              System.out.println("KafkaListener"+"=======" + data);
        }

    @Override
    public void run(String... args) throws Exception {

    }
}
