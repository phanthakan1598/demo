package com.example.demo.member.json.student;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.member.model.MemberFaculty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberStudentAllJson {
	String memberUuid;
	String memberUsername;
	String memberRole;
	String memberUniversity;
	int memberStatus;
	String memberImg;
	String memberFacultyName;
	String memberProgramName;
	String degreeLevelName;
	String sId;
	
	public static MemberStudentAllJson packMemberStudentAllJson(MemberFaculty memberFaculty) {
		MemberStudentAllJson memberStudentAllJson = new MemberStudentAllJson();
		memberStudentAllJson.setSId(memberFaculty.getStudentId());
		memberStudentAllJson.setMemberUuid(memberFaculty.getMember().getMemberUuid());
		memberStudentAllJson.setMemberUsername(memberFaculty.getMember().getMemberUsername());
		memberStudentAllJson.setMemberRole(memberFaculty.getMember().getRole().getRoleName());
		memberStudentAllJson.setMemberUniversity(memberFaculty.getMember().getUniversity().getUniversityNameTh());
		memberStudentAllJson.setMemberStatus(memberFaculty.getMember().getMemberStatus());
		memberStudentAllJson.setMemberImg(memberFaculty.getMember().getMemberImg());
		memberStudentAllJson.setMemberFacultyName(memberFaculty.getFaculty().getFacultyNameEn());
		memberStudentAllJson.setMemberProgramName(memberFaculty.getProgram().getProgramNameEn());
		memberStudentAllJson.setDegreeLevelName(memberFaculty.getProgram().getDegreeLevel().getName());
		return memberStudentAllJson;
	}
	
	public static List<MemberStudentAllJson> packMemberStudentAllJson(List<MemberFaculty> memberFaculties){
		List<MemberStudentAllJson> memberStudentAllJsons = new ArrayList<>();
		for(MemberFaculty memberFaculty : memberFaculties) {
			memberStudentAllJsons.add(packMemberStudentAllJson(memberFaculty));
		}
		return memberStudentAllJsons;
	}
}
