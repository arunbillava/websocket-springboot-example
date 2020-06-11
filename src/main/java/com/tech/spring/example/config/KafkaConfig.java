package com.tech.spring.example.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.tech.spring.example.model.User;

@EnableKafka
@Configuration
public class KafkaConfig {

	@Value("${group_id}")
	private String groupId;
	
	@Value("${user_group_id}")
	private String userGroupId;
	
	@Value("${bootstap.severs}")
	private String bootstrapSever;
	
	private String valueDeserialize;
	
	private String stringDeserialize;
	
	@Bean
	public ConsumerFactory<String,String> consumerFactory(){
		Map<String,Object> config=new HashMap<String,Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapSever);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		return new DefaultKafkaConsumerFactory<>(config);
				
	}
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
	
	   @Bean
	    public ConsumerFactory<String, User> userConsumerFactory() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapSever);
	        config.put(ConsumerConfig.GROUP_ID_CONFIG, userGroupId);
	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
	                new JsonDeserializer<>(User.class));
	    }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListenerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(userConsumerFactory());
	        factory.getContainerProperties().setErrorHandler(new ErrorHandler() {
				
				@Override
				public void handle(Exception thrownException, ConsumerRecord<?, ?> data) {
					System.out.println(thrownException.getMessage());
					throw new SerializationException(thrownException.getMessage());
					//System.out.println(thrownException.getMessage());
					
				}
			});
	        return factory;
	    }

}
