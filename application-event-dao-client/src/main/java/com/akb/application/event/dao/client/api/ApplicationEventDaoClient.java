package com.akb.application.event.dao.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.akb.dao.entity.ApplicationEvent;

@FeignClient(name = "applicationEventDaoClient", url ="")
public interface ApplicationEventDaoClient {
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	ApplicationEvent getApplication(@PathVariable(value="id") String id);
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/status/{status}")
	Boolean updateStatus(@PathVariable(value="id") String id);
	
	

}
