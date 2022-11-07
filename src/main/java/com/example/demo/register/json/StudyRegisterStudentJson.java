package com.example.demo.register.json;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.register.model.EnrollRegister;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyRegisterStudentJson {
	String enrollRegisterUid;
	String enrollRegisterStatus;
	
	String studyRegisterUid;
	
	String subjectUid;
	String subjectName;
	int subjectCredit;
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
	
	public static StudyRegisterStudentJson packStudyRegisterStudentJson(EnrollRegister enrollRegister) {
		StudyRegisterStudentJson studyRegisterStudentJson = new StudyRegisterStudentJson();
		studyRegisterStudentJson.setEnrollRegisterUid(enrollRegister.getEnrollRegisterUuid());
		studyRegisterStudentJson.setEnrollRegisterStatus(Integer.toString(enrollRegister.getEnrollRegisterStatus()));
		
		studyRegisterStudentJson.setStudyRegisterUid(enrollRegister.getStudyRegister().getStudyRegisterUuid());
		
		studyRegisterStudentJson.setSubjectUid(enrollRegister.getStudyRegister().getSubject().getSubjectUuid());
		studyRegisterStudentJson.setSubjectName(enrollRegister.getStudyRegister().getSubject().getName());
		studyRegisterStudentJson.setSubjectCredit(enrollRegister.getStudyRegister().getSubject().getCredit());
		studyRegisterStudentJson.setSubjectNameCode(enrollRegister.getStudyRegister().getSubject().getSubjectCode());
		studyRegisterStudentJson.setSubjectGroupUid(enrollRegister.getStudyRegister().getSubject().getGroupSubject().getGroupSubjectUuid());
		studyRegisterStudentJson.setSubjectGroupName(enrollRegister.getStudyRegister().getSubject().getGroupSubject().getName());
		studyRegisterStudentJson.setSec(Integer.toString(enrollRegister.getStudyRegister().getSec()));
		
		studyRegisterStudentJson.setType(enrollRegister.getStudyRegister().getType());
		
		studyRegisterStudentJson.setRoomUid(enrollRegister.getStudyRegister().getRoom().getRoomUuid());
		studyRegisterStudentJson.setRoomCode(enrollRegister.getStudyRegister().getRoom().getRoomCodeName());
		studyRegisterStudentJson.setRoomCapacity(Integer.toString(enrollRegister.getStudyRegister().getRoom().getRoomCapacity()));
		studyRegisterStudentJson.setCapacityMax(Integer.toString(enrollRegister.getStudyRegister().getCapacityMax()));
		
		studyRegisterStudentJson.setBuildingUid(enrollRegister.getStudyRegister().getRoom().getBuilding().getBuildingUuid());
		studyRegisterStudentJson.setBuildingCode(enrollRegister.getStudyRegister().getRoom().getBuilding().getBuildingCodeName());
		
		studyRegisterStudentJson.setDayUid(enrollRegister.getStudyRegister().getDay().getDayUuid());
		studyRegisterStudentJson.setDayName(enrollRegister.getStudyRegister().getDay().getDayNameTh());
		
		studyRegisterStudentJson.setTimeBegin(enrollRegister.getStudyRegister().getTimeBegin());
		studyRegisterStudentJson.setTimeEnd(enrollRegister.getStudyRegister().getTimeEnd());
		return studyRegisterStudentJson;
	}
	
	public static List<StudyRegisterStudentJson> studyRegisterStudentJsons(List<EnrollRegister> enrollRegisters){
		List<StudyRegisterStudentJson> studyRegisterStudentJsons = new ArrayList<>();
		for(EnrollRegister enrollRegister : enrollRegisters) {
			studyRegisterStudentJsons.add(packStudyRegisterStudentJson(enrollRegister));
		}
		return studyRegisterStudentJsons;
	}
}
