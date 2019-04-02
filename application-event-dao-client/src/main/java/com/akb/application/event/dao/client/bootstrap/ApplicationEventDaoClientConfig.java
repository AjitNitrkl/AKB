package com.akb.application.event.dao.client.bootstrap;

import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Configuration
@EnableFeignClients(basePackages = "com.akb")
public class ApplicationEventDaoClientConfig {

}
