package com.example.demo.register.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CountCreditAllJson {
	int courseStructureCredits;
	int creditMax;
	int creditMin;
	int courseStructureCreditsMajor;
	int courseStructureCreditsGe;
	int creditEnroll;
	int creditEnrollTerm;
//	String countCreditNameTh;
//	String countCreditCodeNameTh;
//	String countCreditNameEn;
//	String countCreditCodeNameEn;
	
	public static CountCreditAllJson packCountCreditPersonalAllJson(int courseStructureCredits,int creditMax,int creditMin,int courseStructureCreditsMajor,	int courseStructureCreditsGe, int creditEnroll, int creditEnrollTerm) {
		CountCreditAllJson countCreditCourseAllJson = new CountCreditAllJson();
		countCreditCourseAllJson.setCourseStructureCredits(courseStructureCredits);
		countCreditCourseAllJson.setCourseStructureCreditsMajor(courseStructureCreditsMajor);
		countCreditCourseAllJson.setCourseStructureCreditsGe(courseStructureCreditsGe);
		countCreditCourseAllJson.setCreditMax(creditMax);
		countCreditCourseAllJson.setCreditMin(creditMin);
		countCreditCourseAllJson.setCreditEnroll(creditEnroll);
		countCreditCourseAllJson.setCreditEnrollTerm(creditEnrollTerm);
		
//		countCreditCourseAllJson.setCountCreditNameTh(countCredit.getCountCreditNameTh());
//		countCreditCourseAllJson.setCountCreditCodeNameTh(countCredit.getCountCreditNameCodeTh());
//		countCreditCourseAllJson.setCountCreditNameEn(countCredit.getCountCreditNameEn());
//		countCreditCourseAllJson.setCountCreditCodeNameEn(countCredit.getCountCreditNameCodeEn());
		return countCreditCourseAllJson;
	}
}
