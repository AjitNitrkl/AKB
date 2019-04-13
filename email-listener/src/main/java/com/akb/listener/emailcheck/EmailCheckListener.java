package com.akb.listener.emailcheck;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.akb.core.comp.thirdparty.listener.AbstractThirdPartyKafkaListener;
import com.akb.core.kafka.domain.application.ApplicationInfo;
import com.akb.dao.entity.Applicant;
import com.akb.dao.entity.Application;
import com.akb.dao.entity.ApplicationEvent;
import com.akb.dao.entity.EventType;
import com.akb.dao.entity.ResultEvent;
import com.akb.listener.email.dbservice.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class EmailCheckListener extends AbstractThirdPartyKafkaListener {
	

	private final EmailService emailService;
	
	@Override
	public void processMessage(ApplicationInfo applicationInfo, Application applicationEntity,
			Acknowledgment ack) {
		
		log.info("Need to do email check");
		Boolean isEmailValid = EmailCheckUtil.validateEmail(applicationEntity.getApplicantList()
										.stream()
										.filter(Applicant::isPrimary)
										.findFirst().get().getEmail());
		ApplicationEvent event = null;		
		if(isEmailValid) {
			 event = ApplicationEvent.builder()
						.applicationId(applicationEntity.getId())
						.eventType(EventType.EMAIL)
						.result(ResultEvent.APPROVED)
						.createdAt(LocalDateTime.now()).build();
			
		}else {
			 event = ApplicationEvent.builder()
						.applicationId(applicationEntity.getId())
						.eventType(EventType.EMAIL)
						.result(ResultEvent.DECLINED)
						.createdAt(LocalDateTime.now()).build();
			
		}
		notify(applicationInfo, event, ack);
		log.info("Email check is done");
						
	}

	private void notify(ApplicationInfo applicationInfo, ApplicationEvent event,
			Acknowledgment ack) {
		emailService.saveEmailEvent(event);
		this.kafkaProducerService.sendDMSMessage(applicationInfo);
		ack.acknowledge();
		
	}

}
