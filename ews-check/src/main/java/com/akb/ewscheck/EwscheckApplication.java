package com.akb.ewscheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@SpringBootApplication
public class EwscheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(EwscheckApplication.class, args);
	}
	
	
	@Bean(name = "ews")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema studentsSchema) {
	  DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
	  definition.setPortTypeName("StudentPort");
	  definition.setTargetNamespace("http://ws.akb.com/ews");
	  definition.setLocationUri("/ws");
	  definition.setSchema(studentsSchema);
	  return definition;
	}

	@Bean
	public XsdSchema studentsSchema() {
	  return new SimpleXsdSchema(new ClassPathResource("ews.xsd"));
	}

}
