package com.example.demo.university.json;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.university.model.RegistrationDate;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegistrationDateAllJson {
	String registrationDateUuid;
	int levelClass;
	int range;
	String type;
	LocalDate beginDate;
	LocalDate endDate;
	
	public static RegistrationDateAllJson packRegistrationDatePersonalAllJson(RegistrationDate registrationDate) {
		RegistrationDateAllJson registrationDateCourseAllJson = new RegistrationDateAllJson();
		registrationDateCourseAllJson.setRegistrationDateUuid(registrationDate.getRegistrationDateUuid());
		registrationDateCourseAllJson.setLevelClass(registrationDate.getRegistrationDateLevelClass());
		registrationDateCourseAllJson.setRange(registrationDate.getRegistrationDateRange());
		registrationDateCourseAllJson.setBeginDate(registrationDate.getBeginDate());
		registrationDateCourseAllJson.setEndDate(registrationDate.getEndDate());
		registrationDateCourseAllJson.setType(registrationDate.getRegistrationDateType());
		return registrationDateCourseAllJson;
	}
	
	public static List<RegistrationDateAllJson> registrationDateCourseAllJsons(List<RegistrationDate> registrationDates){
		List<RegistrationDateAllJson> registrationDateCourseAllJsons = new ArrayList<>();
		for(RegistrationDate registrationDate : registrationDates) {
			registrationDateCourseAllJsons.add(packRegistrationDatePersonalAllJson(registrationDate));
		}
		return registrationDateCourseAllJsons;
	}
}
