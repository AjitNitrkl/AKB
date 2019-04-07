package com.akb.core.kafka.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix="com.akb.core.kafka")
@Data
public class KafkaProperties {

	
	  private String servers;
	  private String groupId;
	  private String securityProtocol;
	  private String saslMechanism;
	  private String saslJaasConfig;
	  private Map<String, String> topics;
	  
	  public String getTopic(KafkaTopic topicName) {
		  return topics.get(topicName.getName());
	  }
}
