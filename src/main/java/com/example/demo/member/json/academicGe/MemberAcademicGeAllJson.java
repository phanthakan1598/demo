package com.example.demo.member.json.academicGe;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.member.model.Member;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberAcademicGeAllJson {
	String memberUuid;
	String memberUsername;
	String memberRole;
	String memberUniversity;
	int memberStatus;
	String memberImg;
	
	public static MemberAcademicGeAllJson packMemberAcademicGeAllJson(Member member) {
		MemberAcademicGeAllJson memberAcademicGeAllJson = new MemberAcademicGeAllJson();
		memberAcademicGeAllJson.setMemberUuid(member.getMemberUuid());
		memberAcademicGeAllJson.setMemberUsername(member.getMemberUsername());
		memberAcademicGeAllJson.setMemberRole(member.getRole().getRoleName());
		memberAcademicGeAllJson.setMemberUniversity(member.getUniversity().getUniversityNameTh());
		memberAcademicGeAllJson.setMemberStatus(member.getMemberStatus());
		memberAcademicGeAllJson.setMemberImg(member.getMemberImg());
		return memberAcademicGeAllJson;
	}
	
	public static List<MemberAcademicGeAllJson> packMemberAcademicGeAllJson(List<Member> members){
		List<MemberAcademicGeAllJson> memberAcademicGeAllJsons = new ArrayList<>();
		for(Member member : members) {
			memberAcademicGeAllJsons.add(packMemberAcademicGeAllJson(member));
		}
		return memberAcademicGeAllJsons;
	}
}
