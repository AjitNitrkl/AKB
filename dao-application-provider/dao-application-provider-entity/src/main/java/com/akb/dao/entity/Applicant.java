package com.akb.dao.entity;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Applicant {
	
	  private String fname;
	  private String lname;
	  private Date dob;
	  private String ssn;
	  private String email;
	  private boolean isPrimary;
	  private List<Address> addressList;

}
