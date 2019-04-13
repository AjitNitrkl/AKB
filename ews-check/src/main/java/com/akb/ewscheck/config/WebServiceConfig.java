package com.akb.ewscheck.config;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{
	
	@Value("${client.user.name}")
	private String userName;

	@Value("${client.user.password}")
	private String userPassword;

  @Bean
  public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
    MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
    messageDispatcherServlet.setApplicationContext(context);
    messageDispatcherServlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
  }
  
  
//  @Bean
//  public HttpComponentsMessageSender httpComponentsMessageSender() {
//    HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
//    // set the basic authorization credentials
//    httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());
//
//    return httpComponentsMessageSender;
//  }
//
//  @Bean
//  public UsernamePasswordCredentials usernamePasswordCredentials() {
//    // pass the user name and password to be used
//    return new UsernamePasswordCredentials(userName, userPassword);
//  }
//  
  
  @Bean
  public SimplePasswordValidationCallbackHandler securityCallbackHandler(){
      SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
      Properties users = new Properties();
      users.setProperty(userName, userPassword);
      callbackHandler.setUsers(users);
      return callbackHandler;
  }

  @Bean
  public Wss4jSecurityInterceptor securityInterceptor(){
      Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
      securityInterceptor.setValidationActions("UsernameToken");
      //changed the validation to usernameToken from Timestamp UsernameToken
      //in soap ui makesure create time is UTC
      // the base64 in the nonce field didnot change it is base64 of admin:secret
      //https://memorynotfound.com/spring-ws-username-password-authentication-wss4j/
      securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());
      securityInterceptor.setFutureTimeToLive(600);
      return securityInterceptor;
  }
  
  @Override
  public void addInterceptors(List interceptors) {
      interceptors.add(securityInterceptor());
  }
  
  @Bean
  Jaxb2Marshaller jaxb2Marshaller() {
    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
    jaxb2Marshaller.setContextPath("com.akb.ws.ews");
    return jaxb2Marshaller;
  }
  
}