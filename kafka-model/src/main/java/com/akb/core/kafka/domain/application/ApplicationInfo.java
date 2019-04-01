package com.akb.core.kafka.domain.application;

import java.util.EnumMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInfo {
	
	private String applicationId;
	@Builder.Default
	private EnumMap<DataType, Object> data;
	
	public ApplicationInfo(String applicationId) {
		this.applicationId = applicationId;
		this.data = new EnumMap<>(DataType.class);
		data.put(DataType.IS_RETRY, false);
	}
	
	public ApplicationInfo() {
		this.data = new EnumMap<>(DataType.class);
		data.put(DataType.IS_RETRY, false);
	}
	
	
	public enum DataType{
		IS_RETRY
	}
	
	

}
