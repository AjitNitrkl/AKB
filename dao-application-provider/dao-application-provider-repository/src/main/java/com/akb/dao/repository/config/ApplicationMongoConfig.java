package com.akb.dao.repository.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.akb.core.mongo.provider.bootstrap.MongoConfig;
import com.akb.core.mongo.provider.properties.AKBMongoProperties;
import com.akb.dao.repository.ApplicationRepository;
import com.akb.dao.repository.properties.ApplicationMongoProperties;

@Configuration
@EnableMongoRepositories(basePackageClasses = {
		ApplicationRepository.class}, 
mongoTemplateRef = ApplicationMongoConfig.APPLICATION_MONGO_TEMPLATE)
public class ApplicationMongoConfig extends MongoConfig{
	
	public static final String APPLICATION_MONGO_TEMPLATE =  "applicationMongoTemplate";
	
	@Autowired
	ApplicationMongoProperties applicationMongoProperties;
	
	@Override
	@Bean(APPLICATION_MONGO_TEMPLATE)
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), getDatabaseName());
	}
	
	protected AKBMongoProperties getAKBMongoProperties() {
		return applicationMongoProperties;
	}

}
