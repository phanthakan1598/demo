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
public class SubjectCourseStructureAllJson {
	String subName;
	String subUid;
	String subNameCode;
	String type;
	String facName;
	String degreeName;
	
	public static SubjectCourseStructureAllJson packSubjectCourseStructureAllJson(CourseStructureDet courseStructureDet) {
		SubjectCourseStructureAllJson subjectCourseStructureAllJson = new SubjectCourseStructureAllJson();
		subjectCourseStructureAllJson.setSubUid(courseStructureDet.getSubject().getSubjectUuid());
		subjectCourseStructureAllJson.setSubName(courseStructureDet.getSubject().getName());
		subjectCourseStructureAllJson.setSubNameCode(courseStructureDet.getSubject().getSubjectCode());
		subjectCourseStructureAllJson.setType(courseStructureDet.getSubject().getGroupSubject().getTypeSubject().getTypeSubjectNameEn());
		subjectCourseStructureAllJson.setFacName(courseStructureDet.getCourseStructure().getProgram().getFaculty().getFacultyNameTh());
		subjectCourseStructureAllJson.setDegreeName(courseStructureDet.getCourseStructure().getProgram().getDegreeLevel().getName());
//		subjectCourseStructureAllJson.setTerm(courseStructureDet);
		return subjectCourseStructureAllJson;
	}
	
	public static List<SubjectCourseStructureAllJson> subjectCourseStructureAllJsons(List<CourseStructureDet> courseStructureDets){
		List<SubjectCourseStructureAllJson> subjectCourseStructureAllJsons = new ArrayList<>();
		for(CourseStructureDet courseStructureDet : courseStructureDets) {
			subjectCourseStructureAllJsons.add(packSubjectCourseStructureAllJson(courseStructureDet));
		}
		return subjectCourseStructureAllJsons;
	}
}
