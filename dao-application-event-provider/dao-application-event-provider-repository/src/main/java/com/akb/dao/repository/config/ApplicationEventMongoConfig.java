package com.akb.dao.repository.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.akb.core.mongo.provider.bootstrap.MongoConfig;
import com.akb.core.mongo.provider.properties.AKBMongoProperties;
import com.akb.dao.repository.ApplicationEventRepository;
import com.akb.dao.repository.properties.ApplicationEventMongoProperties;

@Configuration
@EnableMongoRepositories(basePackageClasses = {
		ApplicationEventRepository.class}, 
mongoTemplateRef = ApplicationEventMongoConfig.APPLICATION_EVENT_MONGO_TEMPLATE)
public class ApplicationEventMongoConfig extends MongoConfig{
	
	public static final String APPLICATION_EVENT_MONGO_TEMPLATE =  "applicationEventMongoTemplate";
	
	@Autowired
	ApplicationEventMongoProperties applicationMongoProperties;
	
	@Override
	@Bean(APPLICATION_EVENT_MONGO_TEMPLATE)
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), getDatabaseName());
	}
	
	protected AKBMongoProperties getAKBMongoProperties() {
		return applicationMongoProperties;
	}

}
