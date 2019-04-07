package com.akb.service.channel.controller;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akb.listener.dms.service.DMSService;
import com.akb.model.application.common.ApplicationRequest;
import com.akb.model.application.common.ApplicationResponse;
import com.akb.service.channel.manager.ChannelManager;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping(path="/channel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ChannelController {

	private final ChannelManager channelManager;
	private final DMSService dmsService;
	
	@PostMapping("/applications")
	public CompletableFuture<ApplicationResponse> postApplication(@Valid @RequestBody ApplicationRequest req){
		//dmsService.executeRules("A47ZX5EX8A");
		//return null;
		return supplyAsync((()->channelManager.saveApplication(req)));
	}
}
