package com.example.demo.register.json;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.register.model.StudyRegister;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyRegisterStudentSubjectAllJson {
	String studyRegisterUid;
	
	String subjectUid;
	String subjectName;
	String subjectCodeName;
	String subjectGroupUid;
	String subjectGroupName;
	String subjectCredit;
	String sec;
	String type;
	
	String roomUid;
	String roomCode;
	String roomCapacity;
	String capacityMax;
	String capacityTotal;
	
	String buildingUid;
	String buildingCode;
	
	String memberUid;
	String memberName;
	
	String dayUid;
	String dayName;
	
	LocalTime timeBegin;
	LocalTime timeEnd;
	
	String checkType;
	
	public static StudyRegisterStudentSubjectAllJson packStudyRegisterPersonalAllJson(StudyRegister studyRegister,String type) {
		StudyRegisterStudentSubjectAllJson studyRegisterCourseAllJson = new StudyRegisterStudentSubjectAllJson();
		studyRegisterCourseAllJson.setStudyRegisterUid(studyRegister.getStudyRegisterUuid());
		
		studyRegisterCourseAllJson.setSubjectUid(studyRegister.getSubject().getSubjectUuid());
		studyRegisterCourseAllJson.setSubjectName(studyRegister.getSubject().getName());
		studyRegisterCourseAllJson.setSubjectCodeName(studyRegister.getSubject().getSubjectCode());
		studyRegisterCourseAllJson.setSubjectGroupUid(studyRegister.getSubject().getGroupSubject().getGroupSubjectUuid());
		studyRegisterCourseAllJson.setSubjectGroupName(studyRegister.getSubject().getGroupSubject().getName());
		studyRegisterCourseAllJson.setSubjectCredit(Integer.toString(studyRegister.getSubject().getCredit()));
		studyRegisterCourseAllJson.setSec(Integer.toString(studyRegister.getSec()));
		studyRegisterCourseAllJson.setType(studyRegister.getSubject().getGroupSubject().getTypeSubject().getTypeSubjectUuid());
		
		studyRegisterCourseAllJson.setRoomUid(studyRegister.getRoom().getRoomUuid());
		studyRegisterCourseAllJson.setRoomCode(studyRegister.getRoom().getRoomCodeName());
		studyRegisterCourseAllJson.setRoomCapacity(Integer.toString(studyRegister.getRoom().getRoomCapacity()));
		studyRegisterCourseAllJson.setCapacityMax(Integer.toString(studyRegister.getCapacityMax()));
		studyRegisterCourseAllJson.setCapacityTotal(Integer.toString(studyRegister.getCapacityTotal()));
		
		studyRegisterCourseAllJson.setBuildingUid(studyRegister.getRoom().getBuilding().getBuildingUuid());
		studyRegisterCourseAllJson.setBuildingCode(studyRegister.getRoom().getBuilding().getBuildingCodeName());
		
//		studyRegisterCourseAllJson.setMemberUid(studyRegister.getMember().getMemberUuid());
//		studyRegisterCourseAllJson.setMemberName(studyRegister.getMember().getMemberNameTh());
		
		studyRegisterCourseAllJson.setDayUid(studyRegister.getDay().getDayUuid());
		studyRegisterCourseAllJson.setDayName(studyRegister.getDay().getDayNameTh());
		
		studyRegisterCourseAllJson.setTimeBegin(studyRegister.getTimeBegin());
		studyRegisterCourseAllJson.setTimeEnd(studyRegister.getTimeEnd());
		
		studyRegisterCourseAllJson.setCheckType(type);
		return studyRegisterCourseAllJson;
	}
	
	public static List<StudyRegisterStudentSubjectAllJson> studyRegisterCourseAllJsons(List<StudyRegister> studyRegisters,List<String> types){
		List<StudyRegisterStudentSubjectAllJson> studyRegisterCourseAllJsons = new ArrayList<>();
		int index = 0;
		for(StudyRegister studyRegister : studyRegisters) {
			studyRegisterCourseAllJsons.add(packStudyRegisterPersonalAllJson(studyRegister, types.get(index)));
			index++;
		}
		return studyRegisterCourseAllJsons;
	}
}
