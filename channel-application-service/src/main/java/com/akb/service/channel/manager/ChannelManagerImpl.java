package com.akb.service.channel.manager;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akb.core.comp.kafka.service.KafkaProducerService;
import com.akb.core.kafka.domain.AKBKafkaMessage;
import com.akb.core.kafka.domain.application.ApplicationInfo;
import com.akb.model.application.common.ApplicationRequest;
import com.akb.model.application.common.ApplicationResponse;
import com.akb.service.channel.service.ChannelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
//@slf4j
public class ChannelManagerImpl implements ChannelManager{
	
	private final ChannelService channelService;
	private final KafkaProducerService kafkaProducerService;
	
	@Override
	public ApplicationResponse saveApplication(ApplicationRequest req) {
		final Consumer<String> notifyOnNewApplication = applicationId ->{
			sendDMSMessage(applicationId);
		};
		return channelService.saveApplication(req, notifyOnNewApplication);
	}

	private void sendDMSMessage(String applicationId) {
//		AKBKafkaMessage message = new AKBKafkaMessage();
//		message.setPayLoad(applicationId);
//		message.setTopicName("application");
		kafkaProducerService.sendDMSMessage(ApplicationInfo.builder()
				.applicationId(applicationId).build());
		
	}

}
