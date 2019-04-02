package com.application.dao.service.dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.akb.dao.entity.Application;
import com.akb.dao.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ApplicationDaoServiceImpl implements ApplicationDaoService {
	
	private final ApplicationRepository applicationRepository;
	private final MongoTemplate applicationMongoTemplate;
	

	@Override
	public Application getApplication(String applicationId) {
		return applicationRepository.findById(applicationId).orElse(null);
	}

	@Override
	public Boolean updateStatus(String applicationId, String status) {
		Query query = new Query(Criteria.where("id").is("applicationId"));
		Update update = new Update().set("status", status);
		return applicationMongoTemplate.updateFirst(query,
				update, Application.class).getModifiedCount() > 0;
	}

}
