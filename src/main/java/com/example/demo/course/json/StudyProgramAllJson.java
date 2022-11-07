package com.example.demo.course.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.course.model.StudyProgram;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyProgramAllJson {
	String studyProgramUuid;
	String name;
	String courseStructureUid;
	String courseStructureName;
	String ProgramName;
	String ProgramUid;
	
	public static StudyProgramAllJson packStudyProgramPersonalAllJson(StudyProgram studyProgram) {
		StudyProgramAllJson studyProgramStudyProgramAllJson = new StudyProgramAllJson();
		studyProgramStudyProgramAllJson.setStudyProgramUuid(studyProgram.getStudyProgramUuid());
		studyProgramStudyProgramAllJson.setName(studyProgram.getName());
		studyProgramStudyProgramAllJson.setCourseStructureUid(studyProgram.getCourseStructure().getCourseStructureUuid());
		studyProgramStudyProgramAllJson.setCourseStructureName(studyProgram.getCourseStructure().getName());
		studyProgramStudyProgramAllJson.setProgramUid(studyProgram.getCourseStructure().getProgram().getProgramUuid());
		studyProgramStudyProgramAllJson.setProgramName(studyProgram.getCourseStructure().getProgram().getProgramNameEn());
		return studyProgramStudyProgramAllJson;
	}
	
	public static List<StudyProgramAllJson> studyProgramStudyProgramAllJsons(List<StudyProgram> studyPrograms){
		List<StudyProgramAllJson> studyProgramStudyProgramAllJsons = new ArrayList<>();
		for(StudyProgram studyProgram : studyPrograms) {
			studyProgramStudyProgramAllJsons.add(packStudyProgramPersonalAllJson(studyProgram));
		}
		return studyProgramStudyProgramAllJsons;
	}
}
