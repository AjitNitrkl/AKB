package com.akb.listener.dms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akb.core.comp.kafka.consumer.listener.AbstractKafkaListener;
import com.akb.core.kafka.domain.application.ApplicationInfo;
import com.akb.listener.dms.service.DMSService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DMSListener extends AbstractKafkaListener<ApplicationInfo>{

	 @Autowired
	 private DMSService dmsService;
	
	@Override
	public void processMessage(ApplicationInfo message, String messageKey) {
		dmsService.executeRules(message.getApplicationId());
	}

}
