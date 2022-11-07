package com.example.demo.member.payload.memberAdmin;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberAdminUpdatePayload {
	private String memberUuid;
	private int memberStatusUpdateUsername;
	private String memberUsername;
	private int memberStatusUpdatePassword;
	private String memberPassword;
	
	private String memberNameTh;
	private String memberNameEn;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate memberBirthday;
	private String memberTel;
	private int memberStatusUpdateEmail;
	private String memberEmail;
	
	private String addressUuid;
	private String addressDetailTh;
	private String addressDetailEn;
	private String tambonUuid;
}
