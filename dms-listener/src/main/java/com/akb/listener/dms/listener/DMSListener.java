package com.akb.listener.dms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.akb.core.comp.kafka.consumer.listener.AbstractKafkaListener;
import com.akb.core.kafka.domain.application.ApplicationInfo;
import com.akb.listener.dms.service.DMSService;
import com.akb.listener.dms.service.DMSServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DMSListener extends AbstractKafkaListener<ApplicationInfo>{
	
	private DMSService dmsService;

	 @Autowired
	 public DMSListener(DMSServiceImpl dmsService) {
		 this.dmsService = dmsService;
	 }
	
	@Override
	public void processMessage(ApplicationInfo message, String messageKey) {
		dmsService.executeRules(message.getApplicationId());
	}

}
