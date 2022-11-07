package com.example.demo.university.payload;

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
public class UniversityUpdatePayload {
	private String universityUuid;
	private String universityNameTh;
	private String universityNameEn;
	private String universityAddress;
	private String tambonUuid;
	
public void validate() throws MemberException {
		
		if(StringUtils.isEmpty(universityUuid)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
		if(StringUtils.isEmpty(universityNameTh)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
		if(StringUtils.isEmpty(universityNameEn)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
		if(StringUtils.isEmpty(universityAddress)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
		if(StringUtils.isEmpty(tambonUuid)) {
			throw MemberException.userIsEmptyOrNull();
		}
		
	}
}
