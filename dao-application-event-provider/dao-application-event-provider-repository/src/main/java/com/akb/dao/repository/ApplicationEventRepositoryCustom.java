package com.akb.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.akb.dao.entity.ApplicationEvent;

public interface ApplicationEventRepositoryCustom {
	
	List<ApplicationEvent> findByTPAndCallStatus(String eventType, String applicationId);
	boolean checkSsnInReview(String ssn);

}
