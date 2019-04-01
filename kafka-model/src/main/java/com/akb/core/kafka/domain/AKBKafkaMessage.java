package com.akb.core.kafka.domain;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AKBKafkaMessage<T> {
	
	private String topicName;
	private Map<String,String> headers;
	T payLoad;

}
