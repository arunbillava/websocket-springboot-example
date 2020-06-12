package com.tech.spring.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.tech.spring.example.model.UserRequest;

@Component
public class Consumer {
	
	@Autowired
	SimpMessagingTemplate template;

	@KafkaListener(topics = {"${kafka_listner_string}"}, groupId = "${group_id}")
	public void consume(String message){
		System.out.println("received: "+message);
		template.convertAndSend("/topic/user",message);
	}
	
	@KafkaListener(topics = {"${kafka_listner_json}"}, groupId = "${user_group_id}",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(UserRequest user) {
        System.out.println("Received user: " + user.getName());
    }
}
