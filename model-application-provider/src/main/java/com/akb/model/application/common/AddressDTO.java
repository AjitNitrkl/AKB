package com.akb.model.application.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddressDTO {
	 private String addressType;
	  private String address1;
	  private String address2;
	  private String state;
	  private String city;
	  private String zip;

}
