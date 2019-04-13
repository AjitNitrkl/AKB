package com.akb.listener.email.dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akb.dao.entity.ApplicationEvent;
import com.akb.dao.repository.ApplicationEventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class EmailServiceImpl implements EmailService {
	
	private final ApplicationEventRepository applicationEventRepository;
	
	@Override
	public void saveEmailEvent(ApplicationEvent event) {
		applicationEventRepository.save(event);
	}

}
