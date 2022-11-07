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
public class MemberPersonalAllJson {
	String memberUuid;
	String memberUsername;
	String memberRole;
	String memberUniversity;
	int memberStatus;
	String memberImg;
	
	public static MemberPersonalAllJson packMemberPersonalAllJson(Member member) {
		MemberPersonalAllJson memberPersonalAllJson = new MemberPersonalAllJson();
		memberPersonalAllJson.setMemberUuid(member.getMemberUuid());
		memberPersonalAllJson.setMemberUsername(member.getMemberUsername());
		memberPersonalAllJson.setMemberRole(member.getRole().getRoleName());
		memberPersonalAllJson.setMemberUniversity(member.getUniversity().getUniversityNameTh());
		memberPersonalAllJson.setMemberStatus(member.getMemberStatus());
		memberPersonalAllJson.setMemberImg(member.getMemberImg());
		return memberPersonalAllJson;
	}
	
	public static List<MemberPersonalAllJson> packMemberPersonalAllJson(List<Member> members){
		List<MemberPersonalAllJson> memberPersonalAllJsons = new ArrayList<>();
		for(Member member : members) {
			memberPersonalAllJsons.add(packMemberPersonalAllJson(member));
		}
		return memberPersonalAllJsons;
	}
}
