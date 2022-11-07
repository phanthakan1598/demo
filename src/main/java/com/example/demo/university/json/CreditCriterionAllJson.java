//package com.example.demo.university.json;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.example.demo.university.model.CreditCriterion;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
//public class CreditCriterionAllJson {
//	String creditCriterionUuid;
//	String name;
//	int totalMax;
//	String degreeName;
//	String programUid;
//	String programName;
//	
//	public static CreditCriterionAllJson packCreditCriterionPersonalAllJson(CreditCriterion creditCriterion) {
//		CreditCriterionAllJson creditCriterionCourseAllJson = new CreditCriterionAllJson();
//		creditCriterionCourseAllJson.setCreditCriterionUuid(creditCriterion.getCreditCriterionUuid());
//		creditCriterionCourseAllJson.setName(creditCriterion.getName());
//		creditCriterionCourseAllJson.setTotalMax(creditCriterion.getTotalMax());
//		creditCriterionCourseAllJson.setDegreeName(creditCriterion.getProgram().getDegreeLevel().getName());
//		creditCriterionCourseAllJson.setProgramUid(creditCriterion.getProgram().getProgramUuid());
//		creditCriterionCourseAllJson.setProgramName(creditCriterion.getProgram().getProgramNameEn());
//		return creditCriterionCourseAllJson;
//	}
//	
//	public static List<CreditCriterionAllJson> creditCriterionCourseAllJsons(List<CreditCriterion> creditCriterions){
//		List<CreditCriterionAllJson> creditCriterionCourseAllJsons = new ArrayList<>();
//		for(CreditCriterion creditCriterion : creditCriterions) {
//			creditCriterionCourseAllJsons.add(packCreditCriterionPersonalAllJson(creditCriterion));
//		}
//		return creditCriterionCourseAllJsons;
//	}
//}
