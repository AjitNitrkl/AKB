package com.akb.service.channel.mapper;

import com.akb.dao.entity.Application;
import com.akb.model.application.common.ApplicationRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class ApplicationMapper {

	public Application toEntity(ApplicationRequest req) {
		return Application.builder()
				.id(createRandomId())
				.status("PENDING")
				.product("HYS")
				.applicantList(ApplicantMapper.toEntity(req.getApplicants())).build();
	}
	
	private String createRandomId() {
		return RandomStringUtils.randomAlphanumeric(10).toUpperCase();
	}
}
