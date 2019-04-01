package com.akb.core.listener.ews.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.akb.core.comp.thirdparty.listener.AbstractThirdPartyKafkaListener;
import com.akb.core.kafka.domain.application.ApplicationInfo;
import com.akb.dao.entity.Application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class EWSListener extends AbstractThirdPartyKafkaListener {

	@Override
	public void processMessage(ApplicationInfo message, Application applicationEntity, Acknowledgment ack) {
		//Need to implement am ews connector call the connector which will in turn call service
		log.info("invoked thirdparty");
	}

}
