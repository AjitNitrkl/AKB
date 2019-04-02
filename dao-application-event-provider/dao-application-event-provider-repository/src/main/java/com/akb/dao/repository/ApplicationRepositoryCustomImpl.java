package com.akb.dao.repository;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.akb.dao.entity.ApplicationEvent;

@Component
public class ApplicationRepositoryCustomImpl implements ApplicationEventRepositoryCustom {
	
	private MongoTemplate applicationMongoTemplate;

	@Override
	public List<ApplicationEvent> findByTPAndCallStatus(String eventType, String applicationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkSsnInReview(String ssn) {
		// TODO Auto-generated method stub
		return false;
	}

}
