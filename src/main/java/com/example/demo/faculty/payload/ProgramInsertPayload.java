package com.example.demo.faculty.payload;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProgramInsertPayload {
	private String programNameTh;
	private String programNameEn;
	private String programCodeName;
	private String facultyUuid;
	private String degreeLevelUid;
	
}
