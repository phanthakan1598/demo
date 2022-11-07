package com.example.demo.member.payload.member;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EditProfileMemberPayload {
	private int memberStatusUpdateUsername;
	private int memberStatusUpdateEmail;
	private String memberUsername;
	private String memberNameTh;
	private String memberNameEn;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate memberBirthday;
	private String memberTel;
	private String memberEmail;
	private MultipartFile pic;
}
