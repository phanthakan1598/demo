package com.example.demo.course.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.course.model.StudyProgramDet;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyFacSubjectAllJson {
//	String subName;
//	String subUid;
	String subNameCode;
	String subTerm;
//	String type;
//	String facName;
//	String degreeName;
	
	
	public static StudyFacSubjectAllJson studyFacSubjectAllJson(StudyProgramDet studyProgramDet) {
		StudyFacSubjectAllJson studyFacSubjectAllJson = new StudyFacSubjectAllJson();
		studyFacSubjectAllJson.setSubNameCode(studyProgramDet.getSubjectCode());
		studyFacSubjectAllJson.setSubTerm(studyProgramDet.getTerm());
//		studyFacSubjectAllJson.setSubUid(courseStructureDet.getSubject().getSubjectUuid());
//		studyFacSubjectAllJson.setSubName(courseStructureDet.getSubject().getName());
//		studyFacSubjectAllJson.setSubNameCode(courseStructureDet.getSubject().getSubjectCode());
//		studyFacSubjectAllJson.setType(courseStructureDet.getSubject().getGroupSubject().getTypeSubject().getTypeSubjectNameEn());
//		studyFacSubjectAllJson.setFacName(courseStructureDet.getCourseStructure().getProgram().getFaculty().getFacultyNameTh());
//		studyFacSubjectAllJson.setDegreeName(courseStructureDet.getCourseStructure().getProgram().getDegreeLevel().getName());
		return studyFacSubjectAllJson;
	}
	
	public static List<StudyFacSubjectAllJson> studyFacSubjectAllJsons(List<StudyProgramDet> studyProgramDets){
		List<StudyFacSubjectAllJson> studyFacSubjectAllJsons = new ArrayList<>();
		for(StudyProgramDet studyProgramDet : studyProgramDets) {
			studyFacSubjectAllJsons.add(studyFacSubjectAllJson(studyProgramDet));
		}
		return studyFacSubjectAllJsons;
	}
}
