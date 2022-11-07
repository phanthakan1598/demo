package com.example.demo.university.json;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.university.model.Calendar;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CalendarAllJson {
	String calendarUuid;
	String name;
	LocalDate beginDate;
	LocalDate endDate;
	
	public static CalendarAllJson packCalendarPersonalAllJson(Calendar calendar) {
		CalendarAllJson calendarCourseAllJson = new CalendarAllJson();
		calendarCourseAllJson.setCalendarUuid(calendar.getCalendarUuid());
		calendarCourseAllJson.setName(calendar.getName());
		calendarCourseAllJson.setBeginDate(calendar.getBeginDate());
		calendarCourseAllJson.setEndDate(calendar.getEndDate());
		return calendarCourseAllJson;
	}
	
	public static List<CalendarAllJson> calendarCourseAllJsons(List<Calendar> calendars){
		List<CalendarAllJson> calendarCourseAllJsons = new ArrayList<>();
		for(Calendar calendar : calendars) {
			calendarCourseAllJsons.add(packCalendarPersonalAllJson(calendar));
		}
		return calendarCourseAllJsons;
	}
}
