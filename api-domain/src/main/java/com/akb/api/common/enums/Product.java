package com.akb.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Product {
	
	T1("checkings"), 
	T2("Savings");
	
	@Getter
	private String value;

}
