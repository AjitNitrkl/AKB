package com.akb.ewscheck.service;

import com.akb.ws.ews.EWSRequest;
import com.akb.ws.ews.EWSResponse;

public interface EWSCheckService {
	
	public EWSResponse processEWS(EWSRequest request);

}
