package com.application.dao.service.dbservice;

import com.akb.dao.entity.Application;

public interface ApplicationDaoService {
	
	Application getApplication(String applicationId);
	Boolean updateStatus(String applicationId,String status);

}
