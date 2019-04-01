package com.akb.core.comp.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractKafkaListener<T> {
	
	@KafkaListener(topics = "${kafka.topicName}", containerFactory = "kafkaListenerContainerFactory")
	public void listen(@Payload T message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageKey,
			Acknowledgment ack) {
		try {
			processMessage(message, messageKey);
			ack.acknowledge();
		}catch(Exception e) {
			log.error("AbstractKafkaListener listen error",e);
		}
		
	}

	public abstract void processMessage(T message, String messageKey);

}
