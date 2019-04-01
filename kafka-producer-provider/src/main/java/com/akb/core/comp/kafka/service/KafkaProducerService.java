package com.akb.core.comp.kafka.service;

import com.akb.core.kafka.domain.application.ApplicationInfo;
import com.akb.core.kafka.domain.AKBKafkaMessage;

public abstract interface KafkaProducerService
{
 // public abstract void sendApplicationLogMessage(ApplicationLog paramApplicationLog);
  
  public abstract void sendDMSMessage(ApplicationInfo paramApplicationInfo);
  
  public abstract void sendMessage(AKBKafkaMessage<?> paramAKBKafkaMessage);
}
