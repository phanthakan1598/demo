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
public class MemberListJson {
	String memberUuid;
	String memberNameTh;
	String memberNameEn;
	int memberStatus;
	String roleName;
	
	public static MemberListJson packMemberListJson(Member member) {
		MemberListJson memberListJson = new MemberListJson();
		memberListJson.setMemberUuid(member.getMemberUuid());
		memberListJson.setMemberNameTh(member.getMemberNameTh());
		memberListJson.setMemberNameEn(member.getMemberNameEn());
		memberListJson.setMemberStatus(member.getMemberStatus());
		memberListJson.setRoleName(member.getRole().getRoleName());
		return memberListJson;
	}
	
	public static List<MemberListJson> packMemberListJson(List<Member> members){
		List<MemberListJson> memberListJsons = new ArrayList<>();
		for(Member member : members) {
			memberListJsons.add(packMemberListJson(member));
		}
		return memberListJsons;
	}
}