package com.example.demo.member.payload.memberTeacher;


import java.time.LocalDate;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberTeacherUpdatePayload {
	private String memberUuid;
	private int memberStatusUpdateUsername;
	private String memberUsername;
	private int memberStatusUpdatePassword;
	private String memberPassword;
	
	private String memberNameTh;
	private String memberNameEn;
	private LocalDate memberBirthday;
	private String memberTel;
	private int memberStatusUpdateEmail;
	private String memberEmail;
	
	private String addressUuid;
	private String addressDetailTh;
	private String addressDetailEn;
	private String tambonUuid;
	private String facultyUuid;
}
