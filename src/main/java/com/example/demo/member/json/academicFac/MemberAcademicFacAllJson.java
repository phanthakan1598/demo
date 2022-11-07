package com.example.demo.member.json.academicFac;

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
public class MemberAcademicFacAllJson {
	String memberUuid;
	String memberUsername;
	String memberRole;
	String memberUniversity;
	int memberStatus;
	String memberImg;
	String memberFacultyName;
	
	public static MemberAcademicFacAllJson packMemberAcademicFacAllJson(MemberFaculty memberFaculty) {
		MemberAcademicFacAllJson memberAcademicFacAllJson = new MemberAcademicFacAllJson();
		memberAcademicFacAllJson.setMemberUuid(memberFaculty.getMember().getMemberUuid());
		memberAcademicFacAllJson.setMemberUsername(memberFaculty.getMember().getMemberUsername());
		memberAcademicFacAllJson.setMemberRole(memberFaculty.getMember().getRole().getRoleName());
		memberAcademicFacAllJson.setMemberUniversity(memberFaculty.getMember().getUniversity().getUniversityNameTh());
		memberAcademicFacAllJson.setMemberStatus(memberFaculty.getMember().getMemberStatus());
		memberAcademicFacAllJson.setMemberImg(memberFaculty.getMember().getMemberImg());
		memberAcademicFacAllJson.setMemberFacultyName(memberFaculty.getFaculty().getFacultyNameEn());
		return memberAcademicFacAllJson;
	}
	
	public static List<MemberAcademicFacAllJson> packMemberAcademicFacAllJson(List<MemberFaculty> memberFaculties){
		List<MemberAcademicFacAllJson> memberAcademicFacAllJsons = new ArrayList<>();
		for(MemberFaculty memberFaculty : memberFaculties) {
			memberAcademicFacAllJsons.add(packMemberAcademicFacAllJson(memberFaculty));
		}
		return memberAcademicFacAllJsons;
	}
}
