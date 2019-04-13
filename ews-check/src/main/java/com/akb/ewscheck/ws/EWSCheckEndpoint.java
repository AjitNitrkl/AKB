package com.akb.ewscheck.ws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.akb.ewscheck.service.EWSCheckService;
import com.akb.ws.ews.EWSRequest;
import com.akb.ws.ews.EWSResponse;

import lombok.RequiredArgsConstructor;


@Endpoint
@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class EWSCheckEndpoint {

	 private final EWSCheckService ewsService;
	
	  @PayloadRoot(namespace = "http://ws.akb.com/ews", localPart = "EWSRequest")
	  @ResponsePayload
	  public EWSResponse processEWS(@RequestPayload EWSRequest request) {
		  
		 return ewsService.processEWS(request);
	  }

}