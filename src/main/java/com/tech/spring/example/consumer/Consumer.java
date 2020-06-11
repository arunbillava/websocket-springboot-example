package com.tech.spring.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.tech.spring.example.model.User;

@Component
public class Consumer {
	
	@Autowired
	SimpMessagingTemplate template;

	@KafkaListener(topics = "Kafka_listner_ex", group = "kafka_test_group")
	public void consume(String message){
		System.out.println("received: "+message);
		template.convertAndSend("/topic/user",message);
	}
	
	@KafkaListener(topics = "Kafka_listner_json", group = "kafka_user_group",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user) {
        System.out.println("Received user: " + user.getName());
    }
}
