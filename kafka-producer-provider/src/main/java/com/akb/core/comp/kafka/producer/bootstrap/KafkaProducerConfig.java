package com.akb.core.comp.kafka.producer.bootstrap;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.EnableAsync;

import com.akb.core.kafka.properties.KafkaProperties;

@Configuration
@EnableAsync
public class KafkaProducerConfig
{
  @Autowired
  KafkaProperties kafkaProperties;
  
  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate()
  {
	  Map<String, Object> props = new HashMap<>();
	  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
			  //kafkaProperties.getServers());
	  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	  
	  return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(props));
  }
}
