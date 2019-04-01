package com.akb.service.channel.mapper;

import java.util.ArrayList;
import java.util.List;

import com.akb.dao.entity.Applicant;
import com.akb.model.application.common.ApplicantRequest;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicantMapper {
	
	public static List<Applicant> toEntity(List<ApplicantRequest> req){
		List<Applicant> applicantList = new ArrayList<Applicant>();
		req.forEach(elm -> applicantList.add(createApplicant(elm)));
		return applicantList;
	}
	
	static Applicant createApplicant(ApplicantRequest applicant){
		return Applicant.builder()
				.fname(applicant.getFname())
				.lname(applicant.getLname())
				.dob(applicant.getDob())
				.ssn(applicant.getSsn())
				.addressList(AddressMapper.toEntity(applicant.getAddressList())).build();
	}

}
