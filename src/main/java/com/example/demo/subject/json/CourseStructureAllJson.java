package com.example.demo.subject.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.subject.model.CourseStructure;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseStructureAllJson {
	String courseStructureUuid;
	String courseStructureName;
	String courseStructureProgramUuid;
	String courseStructureProgramNameTh;
	String courseStructureProgramNameEn;
	String courseGtructureGeCredits;
	String courseGtructureMajorCredits;
	String courseGtructureTotalCredits;
	
	public static CourseStructureAllJson packCourseStructurePersonalAllJson(CourseStructure courseStructure) {
		CourseStructureAllJson courseStructureCourseAllJson = new CourseStructureAllJson();
		courseStructureCourseAllJson.setCourseStructureUuid(courseStructure.getCourseStructureUuid());
		courseStructureCourseAllJson.setCourseStructureName(courseStructure.getName());
		courseStructureCourseAllJson.setCourseStructureProgramUuid(courseStructure.getProgram().getProgramUuid());
		courseStructureCourseAllJson.setCourseStructureProgramNameTh(courseStructure.getProgram().getProgramNameTh());
		courseStructureCourseAllJson.setCourseStructureProgramNameEn(courseStructure.getProgram().getProgramNameEn());
		courseStructureCourseAllJson.setCourseGtructureGeCredits(Integer.toString(courseStructure.getCourseStructureGeCredits()));
		courseStructureCourseAllJson.setCourseGtructureMajorCredits(Integer.toString(courseStructure.getCourseStructureMajorCredits()));
		courseStructureCourseAllJson.setCourseGtructureTotalCredits(Integer.toString(courseStructure.getCourseStructureMajorCredits() +
																	courseStructure.getCourseStructureGeCredits()));
		return courseStructureCourseAllJson;
	}
	
	public static List<CourseStructureAllJson> courseStructureCourseAllJsons(List<CourseStructure> courseStructures){
		List<CourseStructureAllJson> courseStructureCourseAllJsons = new ArrayList<>();
		for(CourseStructure courseStructure : courseStructures) {
			courseStructureCourseAllJsons.add(packCourseStructurePersonalAllJson(courseStructure));
		}
		return courseStructureCourseAllJsons;
	}
}
