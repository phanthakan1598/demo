package com.example.demo.address.payload;

import org.apache.commons.lang3.StringUtils;

import com.example.demo.member.exception.MemberException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressDetailPayload {
	private String addressUuid;
	private String addressDetailTh;
	private String addressDetailEn;
	private String tambonUuid;
	
	public void validate() throws MemberException {
		if(StringUtils.isEmpty(addressDetailTh)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
		if(StringUtils.isEmpty(addressDetailEn)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
		if(StringUtils.isEmpty(tambonUuid)) {
			throw MemberException.userIsEmptyOrNull();
		}
	}		
}
