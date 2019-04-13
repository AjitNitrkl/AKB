package com.application.event.dao.service.dbservice;

import java.util.List;

import com.akb.dao.entity.ApplicationEvent;

public interface ApplicationEventDaoService {
	
	List<ApplicationEvent> getApplicationEvents(String id);
}
