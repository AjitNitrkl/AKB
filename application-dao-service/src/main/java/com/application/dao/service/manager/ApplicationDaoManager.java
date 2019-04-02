package com.application.dao.service.manager;

import com.akb.dao.entity.Application;

public interface ApplicationDaoManager {
	
	Application getApplication(String applicationId);
	Boolean updateStatus(String applicationId, String status);

}
