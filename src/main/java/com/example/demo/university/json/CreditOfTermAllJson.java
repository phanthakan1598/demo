package com.example.demo.university.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.university.model.CreditOfTerm;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreditOfTermAllJson {
	String creditOfTermUuid;
	int creditMin;
	int creditMax;
	String degreeName;
	String programUid;
	String programName;
	
	public static CreditOfTermAllJson packCreditOfTermPersonalAllJson(CreditOfTerm creditOfTerm) {
		CreditOfTermAllJson creditOfTermCourseAllJson = new CreditOfTermAllJson();
		creditOfTermCourseAllJson.setCreditOfTermUuid(creditOfTerm.getCreditOfTermUuid());	
		creditOfTermCourseAllJson.setCreditMin(creditOfTerm.getCreditMin());
		creditOfTermCourseAllJson.setCreditMax(creditOfTerm.getCreditMax());
		creditOfTermCourseAllJson.setDegreeName(creditOfTerm.getProgram().getDegreeLevel().getName());
		creditOfTermCourseAllJson.setProgramUid(creditOfTerm.getProgram().getProgramUuid());
		creditOfTermCourseAllJson.setProgramName(creditOfTerm.getProgram().getProgramNameEn());
		return creditOfTermCourseAllJson;
	}
	
	public static List<CreditOfTermAllJson> creditOfTermCourseAllJsons(List<CreditOfTerm> creditOfTerms){
		List<CreditOfTermAllJson> creditOfTermCourseAllJsons = new ArrayList<>();
		for(CreditOfTerm creditOfTerm : creditOfTerms) {
			creditOfTermCourseAllJsons.add(packCreditOfTermPersonalAllJson(creditOfTerm));
		}
		return creditOfTermCourseAllJsons;
	}
}
