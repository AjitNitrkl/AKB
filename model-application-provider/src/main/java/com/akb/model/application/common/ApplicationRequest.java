package com.akb.model.application.common;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApplicationRequest {
	
	  private List<ApplicantRequest> applicants;
	  private String status;
	  private String idempotenceId;
	  private final Date createdAt;

}
