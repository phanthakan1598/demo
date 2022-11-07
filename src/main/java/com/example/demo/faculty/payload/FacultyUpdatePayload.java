package com.example.demo.faculty.payload;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FacultyUpdatePayload {
	private String facultyUuid;
	private String facultyNameTh;
	private String facultyNameEn;
	private String facultyCodeName;
	
}
