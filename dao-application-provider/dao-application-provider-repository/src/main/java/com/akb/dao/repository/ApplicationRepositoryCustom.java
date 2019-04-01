package com.akb.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.akb.dao.entity.Application;

public interface ApplicationRepositoryCustom {
	
	Page<Application> customSearch(List<String> search, Pageable pageable);
	boolean checkThrottlingLimits(String ssn, int moreThan);
	Boolean updateStatus(String id, String status);

}
