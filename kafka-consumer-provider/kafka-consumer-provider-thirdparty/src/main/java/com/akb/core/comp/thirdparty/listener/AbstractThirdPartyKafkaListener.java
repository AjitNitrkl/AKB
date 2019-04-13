package com.akb.core.comp.thirdparty.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import com.akb.application.dao.client.api.ApplicationDaoClient;
import com.akb.core.comp.kafka.service.KafkaProducerService;
import com.akb.core.kafka.domain.application.ApplicationInfo;
import com.akb.dao.entity.Application;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractThirdPartyKafkaListener {
	
	@Autowired
	protected ApplicationDaoClient applicationDaoClient;
	
	@Autowired
	protected KafkaProducerService kafkaProducerService;
	
	//${kafka.topicName}
	@KafkaListener(topics = "${kafka.topicName}", containerFactory = "kafkaListenerContainerFactory")
	public void listen(@Payload ApplicationInfo message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)
			String messageKey,Acknowledgment ack) {
		final Application applicationEntity =getApplication(message.getApplicationId());
		log.info("Received thirdparty msg for App Id->",applicationEntity.getId().toString());
		processMessage(message,applicationEntity, ack);
	}

	public abstract void processMessage(ApplicationInfo message, 
			Application applicationEntity, Acknowledgment ack);
		

	private Application getApplication(String applicationId) {
		return applicationDaoClient.getApplication(applicationId);
	}

}
