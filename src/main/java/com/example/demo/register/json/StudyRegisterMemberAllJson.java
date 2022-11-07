package com.example.demo.register.json;

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
public class StudyRegisterMemberAllJson {
	String studyRegisterMemberUid;
	String studyRegisterUid;
	
	String memberUid;
	String memberName;
	
	public static StudyRegisterMemberAllJson packStudyRegisterPersonalAllJson(StudyRegisterMember studyRegisterMember) {
		StudyRegisterMemberAllJson studyRegisterCourseAllJson = new StudyRegisterMemberAllJson();
		studyRegisterCourseAllJson.setStudyRegisterMemberUid(studyRegisterMember.getStudyRegisterMemberUuid());
		studyRegisterCourseAllJson.setStudyRegisterUid(studyRegisterMember.getStudyRegister().getStudyRegisterUuid());
		
		studyRegisterCourseAllJson.setMemberUid(studyRegisterMember.getMember().getMemberUuid());
		studyRegisterCourseAllJson.setMemberName(studyRegisterMember.getMember().getMemberNameTh());
		
		return studyRegisterCourseAllJson;
	}
	
	public static List<StudyRegisterMemberAllJson> studyRegisterCourseAllJsons(List<StudyRegisterMember> studyRegisterMembers){
		List<StudyRegisterMemberAllJson> studyRegisterCourseAllJsons = new ArrayList<>();
		for(StudyRegisterMember studyRegisterMember : studyRegisterMembers) {
			studyRegisterCourseAllJsons.add(packStudyRegisterPersonalAllJson(studyRegisterMember));
		}
		return studyRegisterCourseAllJsons;
	}
}
