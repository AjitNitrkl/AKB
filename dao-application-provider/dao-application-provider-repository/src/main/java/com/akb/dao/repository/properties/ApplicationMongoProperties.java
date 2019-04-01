package com.akb.dao.repository.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.akb.core.mongo.provider.properties.AKBMongoProperties;

@Configuration
@ConfigurationProperties(prefix = "com.akb.dao.application")
public class ApplicationMongoProperties extends AKBMongoProperties {

}
