package com.akb.core.comp.kafka.consumer.bootstrap;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer.AckMode;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.EnableAsync;

import com.akb.core.kafka.properties.KafkaProperties;
//import com.fasterxml.jackson.databind.deser.std.StringDeserializer;


@Configuration
@EnableAsync
@EnableKafka
public class KafkaConsumerConfig
{
  @Autowired
  KafkaProperties kafkaProperties;
  
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory()
  {
	  Map<String, Object> props = new HashMap<>();
	  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getServers());
			  //);
	  props.put(ConsumerConfig.GROUP_ID_CONFIG,kafkaProperties.getGroupId());
			  //k);
	  props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
	  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	  
	  JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
	  jsonDeserializer.addTrustedPackages("*");
	  
	  ConcurrentKafkaListenerContainerFactory<String, Object> factory =
			  new ConcurrentKafkaListenerContainerFactory<>();
	  factory.setConsumerFactory(
			  new DefaultKafkaConsumerFactory<String, Object>(props,
			  (Deserializer<String>) new StringDeserializer(), jsonDeserializer));
			
 	  factory.getContainerProperties().setAckMode(AckMode.MANUAL);
	  return factory;
  }
}
