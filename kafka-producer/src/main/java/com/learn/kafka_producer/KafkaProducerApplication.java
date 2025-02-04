package com.learn.kafka_producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(@Autowired KafkaTemplate<String,String> kafkaTemplate){
		return args -> {

			for(int i=0; i<10000; i++){
				kafkaTemplate.send("rushi", "hello kafka" + i);
			}
		};
	}
}
