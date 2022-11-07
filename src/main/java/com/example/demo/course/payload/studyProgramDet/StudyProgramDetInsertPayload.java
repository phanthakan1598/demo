package com.example.demo.course.payload.studyProgramDet;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyProgramDetInsertPayload {
	private String term;
	private String levelClass;
	private String studyProgramUid;
	private String typeSubjectUid;
	private String subjectCode;
}
