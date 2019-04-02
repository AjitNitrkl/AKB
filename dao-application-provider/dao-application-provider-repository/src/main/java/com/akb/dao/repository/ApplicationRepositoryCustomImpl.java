package com.akb.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.akb.dao.entity.Application;

@Component
public class ApplicationRepositoryCustomImpl implements ApplicationRepositoryCustom {
	
	private MongoTemplate applicationMongoTemplate;

	@Override
	public Page<Application> customSearch(List<String> search, Pageable pageable) {
		return null;
	}

	@Override
	public boolean checkThrottlingLimits(String ssn, int moreThan) {
		return false;
	}

	@Override
	public Boolean updateStatus(String applicationId, String status) {
		
		final Query query= new Query().addCriteria(Criteria.where("id")
				.is(applicationId));
		final Update update = new Update().set("applicants.$[].status", status);
		return applicationMongoTemplate.updateMulti(query, update, Application.class).getModifiedCount() > 0;
	}

}
