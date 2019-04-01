package com.akb.core.mongo.provider.bootstrap;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.akb.core.mongo.provider.properties.AKBMongoProperties;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public abstract class MongoConfig  extends AbstractMongoConfiguration{
	
	
	protected abstract AKBMongoProperties getAKBMongoProperties();

	@Override
	public MongoClient mongoClient() {
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
		final ConnectionString connectionString = new 
				ConnectionString("mongodb://localhost:27017/aop");
						//getAKBMongoProperties().getConnectionString());
		MongoClientOptions options = builder
				.connectionsPerHost(getAKBMongoProperties().getMaxPoolSize())
				.minConnectionsPerHost(getAKBMongoProperties().getMinPoolSize())
				.maxConnectionIdleTime(getAKBMongoProperties().getMaxIdleTime())
				.sslEnabled(Optional.ofNullable(connectionString.getSslEnabled()).orElse(false))
				.build();
		
		final MongoCredential credential = connectionString.getCredential();
		
		if (credential != null)
			return new MongoClient(connectionString.getHosts()
					.stream()
					.map(ServerAddress::new)
					.collect(Collectors.toList()),credential,options);
		else {
			return new MongoClient(connectionString.getHosts()
					.stream()
					.map(ServerAddress::new)
					.collect(Collectors.toList()),options);
		}
	}

	@Override
	protected String getDatabaseName() {
		return new ConnectionString("mongodb://localhost:27017/aop").getDatabase();
				//getAKBMongoProperties().getConnectionString()).getDatabase();
	}
	
	@Override
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), getDatabaseName());
	}

}
