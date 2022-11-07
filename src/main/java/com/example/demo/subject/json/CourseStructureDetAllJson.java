package com.example.demo.subject.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.subject.model.CourseStructureDet;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseStructureDetAllJson {
	String courseStructureDetUuid;
	String groupSubjectUid;
	String groupSubjectName;
	String subjectUid;
	String subjectCode;
	String subjectName;
	String subjectCredit;
	String subjectType;
	String subjectTypeUid;
	
	
	public static CourseStructureDetAllJson packCourseStructureDetAllJson(CourseStructureDet courseStructureDet) {
		CourseStructureDetAllJson courseStructureDetCourseAllJson = new CourseStructureDetAllJson();
		courseStructureDetCourseAllJson.setCourseStructureDetUuid(courseStructureDet.getCourseStructureDetUuid());
		courseStructureDetCourseAllJson.setGroupSubjectUid(courseStructureDet.getSubject().getGroupSubject().getGroupSubjectUuid());
		courseStructureDetCourseAllJson.setGroupSubjectName(courseStructureDet.getSubject().getGroupSubject().getName());
		courseStructureDetCourseAllJson.setSubjectUid(courseStructureDet.getSubject().getSubjectUuid());
		courseStructureDetCourseAllJson.setSubjectCode(courseStructureDet.getSubject().getSubjectCode());
		courseStructureDetCourseAllJson.setSubjectName(courseStructureDet.getSubject().getName());
		courseStructureDetCourseAllJson.setSubjectCredit(Integer.toString(courseStructureDet.getSubject().getCredit()));
		courseStructureDetCourseAllJson.setSubjectType(courseStructureDet.getSubject().getGroupSubject().getTypeSubject().getTypeSubjectNameEn());
		courseStructureDetCourseAllJson.setSubjectTypeUid(courseStructureDet.getSubject().getGroupSubject().getTypeSubject().getTypeSubjectUuid());
		return courseStructureDetCourseAllJson;
	}
	
	public static List<CourseStructureDetAllJson> courseStructureDetCourseAllJsons(List<CourseStructureDet> courseStructureDets){
		List<CourseStructureDetAllJson> courseStructureDetCourseAllJsons = new ArrayList<>();
		for(CourseStructureDet courseStructureDet : courseStructureDets) {
			courseStructureDetCourseAllJsons.add(packCourseStructureDetAllJson(courseStructureDet));
		}
		return courseStructureDetCourseAllJsons;
	}
}
