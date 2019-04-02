package com.akb.application.dao.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.akb.dao.entity.Application;

@FeignClient(name = "applicationDaoClient", url ="")
public interface ApplicationDaoClient {
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	Application getApplication(@PathVariable(value="id") String id);
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/status/{status}")
	Boolean updateStatus(@PathVariable(value="id") String id);
	
	

}
