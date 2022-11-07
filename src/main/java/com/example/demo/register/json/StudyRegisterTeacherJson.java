package com.example.demo.register.json;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.register.model.StudyRegisterMember;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyRegisterTeacherJson {
	String studyRegisterUid;
	
	String subjectUid;
	String subjectName;
	String subjectNameCode;
	String subjectGroupUid;
	String subjectGroupName;
	String sec;
	
	String type;
	
	String roomUid;
	String roomCode;
	String roomCapacity;
	String capacityMax;
	
	String buildingUid;
	String buildingCode;
	
	String dayUid;
	String dayName;
	
	LocalTime timeBegin;
	LocalTime timeEnd;
	
	public static StudyRegisterTeacherJson packStudyRegisterTeacherJson(StudyRegisterMember studyRegisterMember) {
		StudyRegisterTeacherJson studyRegisterTeacherJson = new StudyRegisterTeacherJson();
		studyRegisterTeacherJson.setStudyRegisterUid(studyRegisterMember.getStudyRegister().getStudyRegisterUuid());
		
		studyRegisterTeacherJson.setSubjectUid(studyRegisterMember.getStudyRegister().getSubject().getSubjectUuid());
		studyRegisterTeacherJson.setSubjectName(studyRegisterMember.getStudyRegister().getSubject().getName());
		studyRegisterTeacherJson.setSubjectNameCode(studyRegisterMember.getStudyRegister().getSubject().getSubjectCode());
		studyRegisterTeacherJson.setSubjectGroupUid(studyRegisterMember.getStudyRegister().getSubject().getGroupSubject().getGroupSubjectUuid());
		studyRegisterTeacherJson.setSubjectGroupName(studyRegisterMember.getStudyRegister().getSubject().getGroupSubject().getName());
		studyRegisterTeacherJson.setSec(Integer.toString(studyRegisterMember.getStudyRegister().getSec()));
		
		studyRegisterTeacherJson.setType(studyRegisterMember.getStudyRegister().getType());
		
		studyRegisterTeacherJson.setRoomUid(studyRegisterMember.getStudyRegister().getRoom().getRoomUuid());
		studyRegisterTeacherJson.setRoomCode(studyRegisterMember.getStudyRegister().getRoom().getRoomCodeName());
		studyRegisterTeacherJson.setRoomCapacity(Integer.toString(studyRegisterMember.getStudyRegister().getRoom().getRoomCapacity()));
		studyRegisterTeacherJson.setCapacityMax(Integer.toString(studyRegisterMember.getStudyRegister().getCapacityMax()));
		
		studyRegisterTeacherJson.setBuildingUid(studyRegisterMember.getStudyRegister().getRoom().getBuilding().getBuildingUuid());
		studyRegisterTeacherJson.setBuildingCode(studyRegisterMember.getStudyRegister().getRoom().getBuilding().getBuildingCodeName());
		
		studyRegisterTeacherJson.setDayUid(studyRegisterMember.getStudyRegister().getDay().getDayUuid());
		studyRegisterTeacherJson.setDayName(studyRegisterMember.getStudyRegister().getDay().getDayNameTh());
		
		studyRegisterTeacherJson.setTimeBegin(studyRegisterMember.getStudyRegister().getTimeBegin());
		studyRegisterTeacherJson.setTimeEnd(studyRegisterMember.getStudyRegister().getTimeEnd());
		return studyRegisterTeacherJson;
	}
	
	public static List<StudyRegisterTeacherJson> studyRegisterTeacherJsons(List<StudyRegisterMember> studyRegisterMembers){
		List<StudyRegisterTeacherJson> studyRegisterTeacherJsons = new ArrayList<>();
		for(StudyRegisterMember studyRegisterMember : studyRegisterMembers) {
			studyRegisterTeacherJsons.add(packStudyRegisterTeacherJson(studyRegisterMember));
		}
		return studyRegisterTeacherJsons;
	}
}
