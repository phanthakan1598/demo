package com.example.demo.register.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyRegisterUidJson {
	String studyRegisterUid;
	
	public static StudyRegisterUidJson packStudyRegisterUidJson(String studyRegisterUid) {
		StudyRegisterUidJson studyRegisterUidJson = new StudyRegisterUidJson();
		studyRegisterUidJson.setStudyRegisterUid(studyRegisterUid);
		return studyRegisterUidJson;
	}
}
