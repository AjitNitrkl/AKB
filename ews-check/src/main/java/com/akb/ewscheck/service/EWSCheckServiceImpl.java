package com.akb.ewscheck.service;

import org.springframework.stereotype.Component;

import com.akb.ws.ews.EWSRequest;
import com.akb.ws.ews.EWSResponse;
import com.akb.ws.ews.EWSResponseDetails;
import com.akb.ws.ews.Status;

@Component
public class EWSCheckServiceImpl implements EWSCheckService {

	@Override
	public EWSResponse processEWS(EWSRequest request) {
		 EWSResponse response = new EWSResponse();
		  EWSResponseDetails details = new EWSResponseDetails();
		  details.setScore(10);
		  details.setStatus(Status.APPROVED);
		  response.setEWSREsponse(details);
		  return response;
	}

}
