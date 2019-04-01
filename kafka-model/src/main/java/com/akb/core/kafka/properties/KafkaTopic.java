package com.akb.core.kafka.properties;

public enum KafkaTopic
{
	
	 DMS_LISTENER("dms"), 
	 APPLICATIONLOG_LISTENER("applicationlog"),
	 PENDING_APPROVE("pending"), 
	 THROTTLING("throttling"),
	 VALIDATION_EMAIL("email"),
	 EWS("ews");
		

	private String name;
	
	KafkaTopic(String name){
		  this.name = name;
	 }
	  
	public String getName() {
		  return name;
	 }
	
}
  