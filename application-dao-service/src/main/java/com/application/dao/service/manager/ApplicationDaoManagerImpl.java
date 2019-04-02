package com.application.dao.service.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akb.core.comp.kafka.service.KafkaProducerService;
import com.akb.core.kafka.domain.application.ApplicationInfo;
import com.akb.dao.entity.Application;
import com.application.dao.service.dbservice.ApplicationDaoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ApplicationDaoManagerImpl implements ApplicationDaoManager{
	
	private final ApplicationDaoService applicationDaoService;
	private final KafkaProducerService kafkaProducerService;

	@Override
	public Application getApplication(String applicationId) {
		return applicationDaoService.getApplication(applicationId);
	}

	@Override
	public Boolean updateStatus(String applicationId, String status) {
		final Boolean sts= applicationDaoService.updateStatus(applicationId, status);
		if(sts) {
			kafkaProducerService.sendDMSMessage(ApplicationInfo.builder()
					.applicationId(applicationId)
					.build());
		}
		
		return sts;
	}

}
