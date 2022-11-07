package com.example.demo.member.payload;

import com.example.demo.address.payload.AddressUpdatePayload;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberProfilePayload {
	private String memberUsername;
	private String memberPassword;
	private String memberNameTh;
	private String memberNameEn;
	private String memberTel;
	private String memberEmail;
	private AddressUpdatePayload addressDetailPayload;
	private String universityUuid;
	private String roleUuid;
	private SendMailPayload sendMailPayload;
	
//	public void validate() throws MemberException {
//		if(StringUtils.isEmpty(memberUsername)) {
//			throw MemberException.userIsEmptyOrNull();
//		}
//		
//		if(StringUtils.isEmpty(memberPassword)) {
//			throw MemberException.userIsEmptyOrNull();
//		}
//
//		if(StringUtils.isEmpty(memberNameTh)) {
//			throw MemberException.userIsEmptyOrNull();
//		}
//		
//		if(StringUtils.isEmpty(memberNameEn)) {
//			throw MemberException.userIsEmptyOrNull();
//		}
//		
//		if(StringUtils.isEmpty(memberTel)) {
//			throw MemberException.userIsEmptyOrNull();
//		}
//		
//		if(StringUtils.isEmpty(memberEmail)) {
//			throw MemberException.userIsEmptyOrNull();
//		}
//		
//		if(Objects.isNull(addressDetailPayload)) {
//			throw MemberException.userIsEmptyOrNull();
//		}
//		
//		if(Objects.isNull(sendMailPayload)) {
//			throw MemberException.userIsEmptyOrNull();
//		}
//		
//		if(Objects.isNull(universityUuid)) {
//			throw MemberException.userIsEmptyOrNull();
//		}
//		
//		if(Objects.isNull(roleUuid)) {
//			throw MemberException.userIsEmptyOrNull();
//		}
//	}
}
