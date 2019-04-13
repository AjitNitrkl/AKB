package com.application.event.dao.service.dbservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akb.dao.entity.ApplicationEvent;
import com.akb.dao.repository.ApplicationEventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ApplicationEventDaoServiceImpl implements ApplicationEventDaoService {
	
	private final ApplicationEventRepository applicationEventRepository;

	@Override
	public List<ApplicationEvent> getApplicationEvents(String id) {
		log.info("Fetching events for App Id: "+id);
		return applicationEventRepository.findAllByAppId(id);
	}

}
