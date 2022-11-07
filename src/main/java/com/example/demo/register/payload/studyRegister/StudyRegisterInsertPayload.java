package com.example.demo.register.payload.studyRegister;

import java.time.LocalTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyRegisterInsertPayload {
	private String subjectUid;
	private String roomUid;
	private String capacityMax;
	private String dayUid;
	private LocalTime timeBegin;
	private LocalTime timeEnd;
	private String termUid;
}
