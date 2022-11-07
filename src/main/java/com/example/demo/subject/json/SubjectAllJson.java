package com.example.demo.subject.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.subject.model.Subject;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SubjectAllJson {
	String groupSubjectUuid;
	String groupSubjectName;
	String subjectUuid;
	String subjectName;
	String credit;
	String subjectCode;
	
	public static SubjectAllJson packSubjectPersonalAllJson(Subject subject) {
		SubjectAllJson subjectCourseAllJson = new SubjectAllJson();
		subjectCourseAllJson.setGroupSubjectUuid(subject.getGroupSubject().getGroupSubjectUuid());
		subjectCourseAllJson.setGroupSubjectName(subject.getGroupSubject().getName());
		subjectCourseAllJson.setSubjectUuid(subject.getSubjectUuid());
		subjectCourseAllJson.setSubjectName(subject.getName());
		subjectCourseAllJson.setCredit(Integer.toString(subject.getCredit()));
		subjectCourseAllJson.setSubjectCode(subject.getSubjectCode());
		return subjectCourseAllJson;
	}
	
	public static List<SubjectAllJson> subjectCourseAllJsons(List<Subject> subjects){
		List<SubjectAllJson> subjectCourseAllJsons = new ArrayList<>();
		for(Subject subject : subjects) {
			subjectCourseAllJsons.add(packSubjectPersonalAllJson(subject));
		}
		return subjectCourseAllJsons;
	}
}
