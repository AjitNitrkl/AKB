package com.akb.service.channel.mapper;

import java.util.ArrayList;
import java.util.List;

import com.akb.dao.entity.Address;
import com.akb.model.application.common.AddressDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AddressMapper {

	public static List<Address> toEntity(List<AddressDTO> req){
		List<Address> addressList = new ArrayList<Address>();
		 req.forEach(elm -> addressList.add(createAddress(elm)));
		 return addressList;
	}
	
	static Address createAddress(AddressDTO address){
		return Address.builder()
		.address1(address.getAddress1())
		.address2(address.getAddress2())
		.addressType(address.getAddressType())
		.city(address.getCity())
		.state(address.getState())
		.zip(address.getZip()).build();
	}
}
