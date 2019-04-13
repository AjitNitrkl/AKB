package com.akb.dao.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Builder;
import lombok.Data;

@Document(collection="applicationevent")
@Data
@Builder
public class ApplicationEvent {
	
	  @Id
	  @Field("_id")
	  private String id;
	  @Indexed
	  private String applicationId;
	  private EventType eventType;
	  private Date createdAt;
	  

}
