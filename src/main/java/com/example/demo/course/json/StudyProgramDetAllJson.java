package com.example.demo.course.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.course.model.StudyProgramDet;
import com.example.demo.subject.model.Subject;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyProgramDetAllJson {
	String studyProgramDetUuid;
	String term;
	String levelClass;
	String studyProgramUid;
	String studyProgramName;
	
	String degreeUid;
//	String courseUid;
//	String courseGroupUid;
	
//	String courseGroupDetUid;
//	String courseGroupDetName;
//	String courseGroupDetCredit;
	String type;
	String subjectUid;
	String subjectCodeName;
	String subjectName;
	String subjectCredit;
	
	public static StudyProgramDetAllJson packStudyProgramDetPersonalAllJson(StudyProgramDet studyProgramDet,Subject subjects) {
		StudyProgramDetAllJson studyProgramDetStudyProgramDetAllJson = new StudyProgramDetAllJson();
		studyProgramDetStudyProgramDetAllJson.setStudyProgramDetUuid(studyProgramDet.getStudyProgramDetUuid());
		studyProgramDetStudyProgramDetAllJson.setTerm(studyProgramDet.getTerm());
		studyProgramDetStudyProgramDetAllJson.setLevelClass(Integer.toString(studyProgramDet.getLevelClass()));
		studyProgramDetStudyProgramDetAllJson.setStudyProgramUid(studyProgramDet.getStudyProgram().getStudyProgramUuid());
		studyProgramDetStudyProgramDetAllJson.setStudyProgramName(studyProgramDet.getStudyProgram().getName());
		studyProgramDetStudyProgramDetAllJson.setType(studyProgramDet.getTypeSubject().getTypeSubjectUuid());
//		studyProgramDetStudyProgramDetAllJson.setSubjectCodeName(subjects.get(index).getSubjectCode());
//		studyProgramDetStudyProgramDetAllJson.setSubjectName(subjects.get(index).getName());
//		studyProgramDetStudyProgramDetAllJson.setSubjectCredit(Integer.toString(subjects.get(index).getCredit()));
		studyProgramDetStudyProgramDetAllJson.setSubjectUid(subjects.getSubjectUuid());
		studyProgramDetStudyProgramDetAllJson.setSubjectCodeName(subjects.getSubjectCode());
		studyProgramDetStudyProgramDetAllJson.setSubjectName(subjects.getName());
		studyProgramDetStudyProgramDetAllJson.setSubjectCredit(Integer.toString(subjects.getCredit()));
		
//		studyProgramDetStudyProgramDetAllJson.setCourseGroupUid(studyProgramDet.getCourseGroupDet().getCourseGroup().getCourseGroupUuid());
//		studyProgramDetStudyProgramDetAllJson.setCourseUid(studyProgramDet.getCourseGroupDet().getCourseGroup().getCourse().getCourseUuid());
		studyProgramDetStudyProgramDetAllJson.setDegreeUid(studyProgramDet.getStudyProgram().getCourseStructure().getProgram().getDegreeLevel().getDegreeLevelUuid());
//		
//		studyProgramDetStudyProgramDetAllJson.setCourseGroupDetUid(studyProgramDet.getCourseGroupDet().getCourseGroupDetUuid());
//		studyProgramDetStudyProgramDetAllJson.setCourseGroupDetName(studyProgramDet.getCourseGroupDet().getName());
//		studyProgramDetStudyProgramDetAllJson.setCourseGroupDetCredit(Integer.toString(studyProgramDet.getCourseGroupDet().getCredit()));
		return studyProgramDetStudyProgramDetAllJson;
	}
	
	public static List<StudyProgramDetAllJson> studyProgramDetStudyProgramDetAllJsons(List<StudyProgramDet> studyProgramDets, List<Subject> subjects){
		List<StudyProgramDetAllJson> studyProgramDetStudyProgramDetAllJsons = new ArrayList<>();
//		int index = 0;
//		for(StudyProgramDet studyProgramDet : studyProgramDets) {
//			studyProgramDetStudyProgramDetAllJsons.add(packStudyProgramDetPersonalAllJson(studyProgramDet,subjects,index));
//			index++;
//		}
		int index = 0;
		for(StudyProgramDet studyProgramDet : studyProgramDets) {
//			for(Subject subject : subjects) {
				studyProgramDetStudyProgramDetAllJsons.add(packStudyProgramDetPersonalAllJson(studyProgramDet,subjects.get(index)));
//			}
			index++;
		}
		
		return studyProgramDetStudyProgramDetAllJsons;
	}
}
