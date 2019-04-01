package com.akb.dao.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection="applicationevent")
@Getter
@Setter
public class ApplicationEvent {
	
	  @Id
	  @Field("_id")
	  private String id;
	  @Indexed
	  private String applicationId;
	  private EventType eventType;
	  private Date createdAt;

}
