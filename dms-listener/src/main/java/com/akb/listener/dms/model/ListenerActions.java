package com.akb.listener.dms.model;

import com.akb.core.kafka.properties.KafkaTopic;

public enum ListenerActions {

	 THROTTLING(KafkaTopic.THROTTLING),
	 VALIDATION_EMAIL(KafkaTopic.VALIDATION_EMAIL),
	 PENDING_APPROVE(KafkaTopic.PENDING_APPROVE),
	 EWS(KafkaTopic.EWS);

	private KafkaTopic topicName;
	
	ListenerActions(KafkaTopic topicName){
		  this.topicName = topicName;
	 }
	  
	public KafkaTopic getTopicName() {
		  return topicName;
	 }
}
