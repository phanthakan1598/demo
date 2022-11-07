package com.example.demo.member.payload;

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
public class MemberProfilePasswordPayload {
	private String currentPassword;
	private String newPassword;
	
	public void validate() throws MemberException {
		if(StringUtils.isEmpty(currentPassword)) {
			throw MemberException.userEmptyCurrentPassword();
		}
		
		if(StringUtils.isEmpty(newPassword)) {
			throw MemberException.userEmptyNewPassword();
		}
	}
}
