package com.example.demo.register.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.register.model.Day;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DayAllJson {
	String dayUuid;
	String dayNameTh;
	String dayCodeNameTh;
	String dayNameEn;
	String dayCodeNameEn;
	
	public static DayAllJson packDayPersonalAllJson(Day day) {
		DayAllJson dayCourseAllJson = new DayAllJson();
		dayCourseAllJson.setDayUuid(day.getDayUuid());
		dayCourseAllJson.setDayNameTh(day.getDayNameTh());
		dayCourseAllJson.setDayCodeNameTh(day.getDayNameCodeTh());
		dayCourseAllJson.setDayNameEn(day.getDayNameEn());
		dayCourseAllJson.setDayCodeNameEn(day.getDayNameCodeEn());
		return dayCourseAllJson;
	}
	
	public static List<DayAllJson> dayCourseAllJsons(List<Day> days){
		List<DayAllJson> dayCourseAllJsons = new ArrayList<>();
		for(Day day : days) {
			dayCourseAllJsons.add(packDayPersonalAllJson(day));
		}
		return dayCourseAllJsons;
	}
}
