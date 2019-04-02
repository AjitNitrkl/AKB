package com.akb.dao.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "applicationEvent")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationEvent {
	
	@Id
	@Field("_id")
	private String id;
	
	private String applicationId;
	
	private String eventType;
	
	private Date createdAt;
	
	private List<String> thirdPartyCalls;
	
	private ResultEvent result;

}