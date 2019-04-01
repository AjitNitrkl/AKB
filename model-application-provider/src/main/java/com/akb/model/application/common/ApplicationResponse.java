package com.akb.model.application.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationResponse {
	private String id;
	private String status;

}
