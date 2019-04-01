package com.akb.core.mongo.provider.properties;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AKBMongoProperties {
	
	  private String connectionString;
	  @Value("2")
	  private Integer maxPoolSize;
	  @Value("1")
	  private Integer minPoolSize;
	  @Value("60000")
	  private Integer maxIdleTime;
	  
	  

}
