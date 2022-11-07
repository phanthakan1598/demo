package com.example.demo.member.payload;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.example.demo.address.payload.AddressUpdatePayload;
import com.example.demo.member.exception.MemberException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberChangeProfilePayload {
	private String memberUuid;
	private String memberUsername;
	private String memberPassword;
	private String memberNameTh;
	private String memberNameEn;
	private String memberTel;
	private AddressUpdatePayload addressDetailPayload;
//	private String universityUuid
	
	public void validate() throws MemberException {
		
		if(StringUtils.isEmpty(memberUuid)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
		if(StringUtils.isEmpty(memberNameTh)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
		if(StringUtils.isEmpty(memberNameEn)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
		if(StringUtils.isEmpty(memberTel)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
		if(Objects.isNull(addressDetailPayload)) {
			throw MemberException.userIsEmptyOrNull();
		}
	}
}
