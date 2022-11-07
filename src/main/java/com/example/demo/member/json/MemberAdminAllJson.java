package com.example.demo.member.json;

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
public class MemberAdminAllJson {
	String memberUuid;
	String memberUsername;
	String memberRole;
	String memberUniversity;
	int memberStatus;
	String memberImg;
	
	public static MemberAdminAllJson packMemberAdminAllJson(Member member) {
		MemberAdminAllJson memberAdminAllJson = new MemberAdminAllJson();
		memberAdminAllJson.setMemberUuid(member.getMemberUuid());
		memberAdminAllJson.setMemberUsername(member.getMemberUsername());
		memberAdminAllJson.setMemberRole(member.getRole().getRoleName());
		memberAdminAllJson.setMemberUniversity(member.getUniversity().getUniversityNameTh());
		memberAdminAllJson.setMemberStatus(member.getMemberStatus());
		memberAdminAllJson.setMemberImg(member.getMemberImg());
		return memberAdminAllJson;
	}
	
	public static List<MemberAdminAllJson> packMemberAdminAllJson(List<Member> members){
		List<MemberAdminAllJson> memberAdminAllJsons = new ArrayList<>();
		for(Member member : members) {
			memberAdminAllJsons.add(packMemberAdminAllJson(member));
		}
		return memberAdminAllJsons;
	}
	
}
