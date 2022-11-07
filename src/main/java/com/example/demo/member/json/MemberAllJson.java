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
public class MemberAllJson {
	String memberUuid;
	String memberUsername;
	String memberRole;
	String memberUniversity;
	int memberStatus;
	String memberImg;
	
	public static MemberAllJson packMemberPersonalAllJson(Member member) {
		MemberAllJson memberCourseAllJson = new MemberAllJson();
		memberCourseAllJson.setMemberUuid(member.getMemberUuid());
		memberCourseAllJson.setMemberUsername(member.getMemberUsername());
		memberCourseAllJson.setMemberRole(member.getRole().getRoleName());
		memberCourseAllJson.setMemberUniversity(member.getUniversity().getUniversityNameTh());
		memberCourseAllJson.setMemberStatus(member.getMemberStatus());
		memberCourseAllJson.setMemberImg(member.getMemberImg());
		return memberCourseAllJson;
	}
	
	public static List<MemberAllJson> memberCourseAllJsons(List<Member> members ){
		List<MemberAllJson> memberCourseAllJsons = new ArrayList<>();
		for(Member member : members) {
			memberCourseAllJsons.add(packMemberPersonalAllJson(member));
			
		}
		return memberCourseAllJsons;
	}
}
