package com.application.dao.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akb.dao.entity.Application;
import com.application.dao.service.manager.ApplicationDaoManager;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/applications", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ApplicationDaoController {
	
	private final ApplicationDaoManager applicationDaoManager;
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	Application getApplication(@PathVariable(value="id") String id) {
		return applicationDaoManager.getApplication(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/status/{status}")
	Boolean updateStatus(@PathVariable(value="id") String id, 
			@PathVariable(value="status") String status) {
		return applicationDaoManager.updateStatus(id, status);
	}

}
