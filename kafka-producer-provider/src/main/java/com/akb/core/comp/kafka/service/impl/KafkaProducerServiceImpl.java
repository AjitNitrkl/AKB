package com.akb.core.comp.kafka.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.akb.core.comp.kafka.service.KafkaProducerService;
import com.akb.core.kafka.domain.AKBKafkaMessage;
import com.akb.core.kafka.domain.application.ApplicationInfo;
import com.akb.core.kafka.properties.KafkaProperties;
import com.akb.core.kafka.properties.KafkaTopic;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService

{
	
  private static final Logger log = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
  KafkaTemplate<String, Object> kafkaTemplate;
  KafkaProperties kafkaProperties;
  
  @Autowired
  public KafkaProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate, KafkaProperties kafkaProperties)
  {
    this.kafkaTemplate = kafkaTemplate;
    this.kafkaProperties = kafkaProperties;
  }
  
//  public void sendApplicationLogMessage(ApplicationLog applicationLog)
//  {
//    sendMessage(PNCKafkaMessage.builder().payload(applicationLog)
//      .topicName(this.kafkaProperties.getTopic(KafkaTopic.APPLICATIONLOG_LISTENER)).build());
//  }
  
  public void sendDMSMessage(ApplicationInfo applicationInfo)
  {
    applicationInfo.setApplicationId("111");
    sendMessage(AKBKafkaMessage.builder().payLoad(applicationInfo)
      .topicName(this.kafkaProperties.getTopic(KafkaTopic.DMS_LISTENER)).build());
  }
  
  public void sendMessage(AKBKafkaMessage<?> message)
  {
	 Message<?> messageToSend = MessageBuilder.withPayload(message.getPayLoad())
			 	.setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString())
			 	.setHeader(KafkaHeaders.TOPIC, message.getTopicName())
			 	.build();
	 if(message.getHeaders() != null && message.getHeaders().isEmpty()) {
		 messageToSend.getHeaders().putAll(message.getHeaders());
	 }
	 kafkaTemplate.send(messageToSend)
	 .completable()
	 .thenAccept(result ->{
		 log.info("Message sent --",result.getProducerRecord().key());
	 	})
	 .exceptionally(error -> {
		 log.error("Error sending Message to topic --"+message.getTopicName(),error);
		 return null;
	 });
    //throw new Error("Unresolved compilation problems: \n\tMessage cannot be resolved to a type\n\tMessageBuilder cannot be resolved\n");
  }
}
