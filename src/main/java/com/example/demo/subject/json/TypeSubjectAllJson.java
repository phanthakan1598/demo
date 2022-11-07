package com.example.demo.subject.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.subject.model.TypeSubject;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TypeSubjectAllJson {
	String typeSubjectUuid;
	String typeSubjectNameTh;
	String typeSubjectNameEn;
	
	public static TypeSubjectAllJson packTypeSubjectPersonalAllJson(TypeSubject typeSubject) {
		TypeSubjectAllJson typeSubjectCourseAllJson = new TypeSubjectAllJson();
		typeSubjectCourseAllJson.setTypeSubjectUuid(typeSubject.getTypeSubjectUuid());
		typeSubjectCourseAllJson.setTypeSubjectNameTh(typeSubject.getTypeSubjectNameTh());
		typeSubjectCourseAllJson.setTypeSubjectNameEn(typeSubject.getTypeSubjectNameEn());
		return typeSubjectCourseAllJson;
	}
	
	public static List<TypeSubjectAllJson> typeSubjectCourseAllJsons(List<TypeSubject> typeSubjects){
		List<TypeSubjectAllJson> typeSubjectCourseAllJsons = new ArrayList<>();
		for(TypeSubject typeSubject : typeSubjects) {
			typeSubjectCourseAllJsons.add(packTypeSubjectPersonalAllJson(typeSubject));
		}
		return typeSubjectCourseAllJsons;
	}
}
