package com.example.demo.member.payload.memberPersonal;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberPersonalInsertPayload {
	private String memberUsername;
	private String memberPassword;
	
	private String memberNameTh;
	private String memberNameEn;
	private LocalDate memberBirthday;
	private String memberTel;
	private String memberEmail;
	
	private String addressDetailTh;
	private String addressDetailEn;
	private String tambonUuid;
}
