package com.application.event.dao.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akb.dao.entity.ApplicationEvent;
import com.application.event.dao.service.dbservice.ApplicationEventDaoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ApplicationEventDaoManagerImpl implements ApplicationEventDaoManager {
	
	private final ApplicationEventDaoService applicationEventDaoService;

	@Override
	public List<ApplicationEvent> getApplicationEvents(String id) {
		return applicationEventDaoService.getApplicationEvents(id);
	}

}
