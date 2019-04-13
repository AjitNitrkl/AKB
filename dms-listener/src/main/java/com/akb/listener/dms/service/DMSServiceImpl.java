package com.akb.listener.dms.service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kie.api.command.Command;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akb.api.common.enums.Product;
import com.akb.core.comp.kafka.service.KafkaProducerService;
import com.akb.core.kafka.domain.AKBKafkaMessage;
import com.akb.core.kafka.domain.application.ApplicationInfo;
import com.akb.core.kafka.properties.KafkaProperties;
import com.akb.dao.entity.Application;
import com.akb.dao.entity.ApplicationEvent;
import com.akb.listener.dms.config.DroolsBeanFactory;
import com.akb.listener.dms.model.Actions;
import com.akb.listener.dms.model.DMSActions;
import com.akb.listener.dms.model.ListenerActions;
import com.akb.dao.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
//@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class DMSServiceImpl implements DMSService {
	
	 Map<Product, StatelessKieSession> kieSessions;
	 KafkaProperties kafkaProperties;
	 KafkaProducerService kafkaProducerService;
	 @Autowired
	 ApplicationRepository applicationRepository;
	
	@Autowired
	DMSServiceImpl(DroolsBeanFactory droolsBeanFactory, KafkaProperties kafkaProperties,
			KafkaProducerService kafkaProducerService){
		this.kieSessions = buildKieSessions(droolsBeanFactory);
		this.kafkaProperties = kafkaProperties;
		this.kafkaProducerService = kafkaProducerService;
	}

	private Map<Product, StatelessKieSession> buildKieSessions(DroolsBeanFactory droolsBeanFactory) {
		EnumMap<Product, StatelessKieSession> sessions = new EnumMap<>(Product.class);
		for(Product product : Product.values())
			sessions.put(product, droolsBeanFactory.getKieSession(product));
		return sessions;
	}

	@Override
	public void executeRules(String appId) {

		Application application = applicationRepository.findById(appId).get();
				//Application.builder().id(appId).build();
		if(application != null) {
			List<ApplicationEvent> events =  null;
					//ApplicationEvent.builder().id("A47ZX5EX8A").build();
			Actions actions = getActions(application,events);
			executeDMSAction(application,actions.getDmsAction());
			executeListenerAction(application, actions.getListenerAction());
			
		}
		
	}

	private void executeListenerAction(Application application, 
			List<ListenerActions> listenerAction) {
		
	
	kafkaProducerService.sendMessage(AKBKafkaMessage.builder().payLoad(ApplicationInfo.builder()
										.applicationId(application.getId()).build())
										.topicName("email")
										.build());
		
//		listenerAction.stream().map(action-> AKBKafkaMessage.builder()
//										.payLoad(ApplicationInfo.builder()
//										.applicationId(application.getId()).build())
//										.topicName("email")
//										.build())
//										.forEach(kafkaProducerService :: sendMessage);;
	}

	private void executeDMSAction(Application application, List<DMSActions> dmsAction) {
		dmsAction.forEach(it ->{
			switch(it) {
			case SET_STATUS_APPROVED:
			case SET_STATUS_DECLINED:
			case SET_STATUS_INREVIEW:
				log.info("status changed");
				this.changeStatus(application,"");
				break;
			case UPDATE_DMS_APPROVED:
				this.updateDMSApproved(application);
				break;
			default:
				break;
				
			}
		});
	}

	private void updateDMSApproved(Application application) {
		// TODO Auto-generated method stub
		
	}

	private void changeStatus(Application application, String string) {
		// TODO Auto-generated method stub
		
	}

	private Actions getActions(Application application, List<ApplicationEvent> events) {
		 Optional<StatelessKieSession> kieSessionOptional = getKieSession(application);
		 Actions actions = new Actions();
		 return kieSessionOptional.map(kieSession ->{
			 List<Command<?>> commands = buildCommands(application, events, actions);
			 kieSession.execute(CommandFactory.newBatchExecution(commands));
			 return actions;
		 }).orElseGet(()->{
			log.info("No rules defined for ",application.getProduct());
			return actions;
		 });
	}

	private List<Command<?>> buildCommands(Application application, List<ApplicationEvent> events,
			Actions actions) {
	//	events.forEach(k->System.out.println("Events to be exec"+k.getEventType()));
		List<Command<?>> cmds = new ArrayList<>();
		cmds.add(CommandFactory.newInsert(application));
		cmds.add(CommandFactory.newInsert(events));
		//cmds.add(CommandFactory.newSetGlobal("actions", actions));
		return cmds;
	}

	private Optional<StatelessKieSession> getKieSession(Application application) {
		return Optional.ofNullable(kieSessions.get(Product.valueOf(application.getProduct())));
		
	}

	
}
