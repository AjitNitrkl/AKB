package com.akb.listener.dms.config;


import java.io.File;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Component;

import com.akb.api.common.enums.Product;

@Component
public class DroolsBeanFactory {

	private static final String RULE_PATH = "rules";
	private static final String RULES_FILE_SUFFIX = "ApplicationValidation_";
	private static final String RULES_FILE_PREFIX = "drl";
	private KieServices kieServices = KieServices.Factory.get();
	
	private void getKieRepository() {
		final KieRepository kieRepository = kieServices.getRepository();
		kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
	}
	
	public StatelessKieSession getKieSession(Product product) {
		getKieRepository();
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		String fileRules = RULE_PATH+File.separator + RULES_FILE_PREFIX 
				+"productname"+RULES_FILE_SUFFIX;
		kieFileSystem.write(ResourceFactory.newClassPathResource(fileRules));
		KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
		kb.buildAll();
		KieModule kieModule = kb.getKieModule();
		KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
		return kContainer.newStatelessKieSession();
		
	}
}
