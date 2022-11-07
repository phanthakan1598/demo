package com.example.demo.university.payload;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UniversityPayload {
	private String universityNameTh;
	private String universityNameEn;
	private String universityAddress;
	private String tambonUuid;
	private MultipartFile pic;
	
}
