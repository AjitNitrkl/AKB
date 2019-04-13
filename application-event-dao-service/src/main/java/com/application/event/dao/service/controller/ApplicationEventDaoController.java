package com.application.event.dao.service.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akb.dao.entity.ApplicationEvent;
import com.application.event.dao.service.manager.ApplicationEventDaoManager;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/application-event", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ApplicationEventDaoController {
	
	private final ApplicationEventDaoManager applicaitonEventDaoManager;
	
	@GetMapping("/{id}")
	public CompletableFuture<List<ApplicationEvent>> 
						getApplicationEvents(@PathVariable(value="id") String id){
		return supplyAsync(()-> applicaitonEventDaoManager.getApplicationEvents(id));
	}

}
