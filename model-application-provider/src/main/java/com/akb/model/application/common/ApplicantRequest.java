package com.akb.model.application.common;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApplicantRequest {
	
	  private String fname;
	  private String lname;
	  private Date dob;
	  private String ssn;
	  private List<AddressDTO> addressList;

}
