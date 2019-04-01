package com.akb.service.channel.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akb.core.comp.kafka.service.KafkaProducerService;
import com.akb.core.kafka.domain.AKBKafkaMessage;
import com.akb.dao.entity.Application;
import com.akb.dao.repository.ApplicationRepository;
import com.akb.model.application.common.ApplicationRequest;
import com.akb.model.application.common.ApplicationResponse;
import com.akb.service.channel.mapper.ApplicationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
//@slf4j
public class ChannelServiceImpl implements ChannelService {

	private final ApplicationRepository applicationRepository;
	private final KafkaProducerService kafkaProducerService;
	
	

	@Override
	public ApplicationResponse saveApplication(ApplicationRequest req, 
			Consumer<String> notifyNewApp) {
		Application application =ApplicationMapper.toEntity(req);
		applicationRepository.save(application);
		notifyNewApp.accept(application.getId());
		return ApplicationResponse.builder()
				.id(application.getId())
				.status(application.getStatus()).build();
	}

}
