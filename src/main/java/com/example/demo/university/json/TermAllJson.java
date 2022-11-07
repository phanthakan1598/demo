package com.example.demo.university.json;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.university.model.Term;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TermAllJson {
	String termUuid;
	int termname;
	int year;
	LocalDate beginDate;
	LocalDate endDate;
	
	public static TermAllJson packTermPersonalAllJson(Term term) {
		TermAllJson termCourseAllJson = new TermAllJson();
		termCourseAllJson.setTermUuid(term.getTermUuid());
		termCourseAllJson.setTermname(term.getTerm());
		termCourseAllJson.setYear(term.getYear());
		termCourseAllJson.setBeginDate(term.getBeginDate());
		termCourseAllJson.setEndDate(term.getEndDate());
		return termCourseAllJson;
	}
	
	public static List<TermAllJson> termCourseAllJsons(List<Term> terms){
		List<TermAllJson> termCourseAllJsons = new ArrayList<>();
		for(Term term : terms) {
			termCourseAllJsons.add(packTermPersonalAllJson(term));
		}
		return termCourseAllJsons;
	}
}
