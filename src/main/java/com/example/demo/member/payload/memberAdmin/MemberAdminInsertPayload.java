package com.example.demo.member.payload.memberAdmin;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberAdminInsertPayload {
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
	
//	private MultipartFile pic;
}
