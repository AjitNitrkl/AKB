package com.application.event.dao.service.manager;

import java.util.List;

import com.akb.dao.entity.ApplicationEvent;

public interface ApplicationEventDaoManager {
	
	List<ApplicationEvent> getApplicationEvents(String id);

}
