package com.example.demo.member.json.teacher;

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
public class MemberTeacherAllJson {
	String memberUuid;
	String memberName;
	String memberUsername;
	String memberRole;
	String memberUniversity;
	int memberStatus;
	String memberImg;
	String memberFacultyName;
	String memberFacultyNameCode;
	
	public static MemberTeacherAllJson packMemberTeacherAllJson(MemberFaculty memberFaculty) {
		MemberTeacherAllJson memberTeacherAllJson = new MemberTeacherAllJson();
		memberTeacherAllJson.setMemberUuid(memberFaculty.getMember().getMemberUuid());
		memberTeacherAllJson.setMemberName(memberFaculty.getMember().getMemberNameTh());
		memberTeacherAllJson.setMemberUsername(memberFaculty.getMember().getMemberUsername());
		memberTeacherAllJson.setMemberRole(memberFaculty.getMember().getRole().getRoleName());
		memberTeacherAllJson.setMemberUniversity(memberFaculty.getMember().getUniversity().getUniversityNameTh());
		memberTeacherAllJson.setMemberStatus(memberFaculty.getMember().getMemberStatus());
		memberTeacherAllJson.setMemberImg(memberFaculty.getMember().getMemberImg());
		memberTeacherAllJson.setMemberFacultyName(memberFaculty.getFaculty().getFacultyNameEn());
		memberTeacherAllJson.setMemberFacultyNameCode(memberFaculty.getFaculty().getFacultyCodeName());
		return memberTeacherAllJson;
	}
	
	public static List<MemberTeacherAllJson> packMemberTeacherAllJson(List<MemberFaculty> memberFaculties){
		List<MemberTeacherAllJson> memberTeacherAllJsons = new ArrayList<>();
		for(MemberFaculty memberFaculty : memberFaculties) {
			memberTeacherAllJsons.add(packMemberTeacherAllJson(memberFaculty));
		}
		return memberTeacherAllJsons;
	}
}
