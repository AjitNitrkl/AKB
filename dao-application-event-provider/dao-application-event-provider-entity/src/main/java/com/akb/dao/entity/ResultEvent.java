package com.akb.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEvent {
	APPROVED(1),
	DECLINED(2),
	UNABLE_TO_PROCESS(3),
	IN_REVIEW(4),
	EXCEPTION(5);
	
	private Integer statusPriority;

}
