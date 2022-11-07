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
public class UniversityAllJson {
	String memberUuid;
	String memberUsername;
	String memberUniversity;
	String universityImg;
	String memberImg;
	String number;
	String codeName;
	
	public static UniversityAllJson packUniversityAllJson(Member member) {
		UniversityAllJson universityAllJson = new UniversityAllJson();
		universityAllJson.setCodeName(member.getUniversity().getUniversityCodeName());
		universityAllJson.setNumber(member.getUniversity().getUniversityNumber());
		universityAllJson.setMemberUuid(member.getMemberUuid());
		universityAllJson.setMemberUsername(member.getMemberUsername());
		universityAllJson.setMemberUniversity(member.getUniversity().getUniversityNameTh());
		universityAllJson.setUniversityImg(member.getUniversity().getUniversityImg());
		universityAllJson.setMemberImg(member.getMemberImg());
		return universityAllJson;
	}
	
	public static List<UniversityAllJson> packUniversityAllJson(List<Member> members){
		List<UniversityAllJson> universityAllJsons = new ArrayList<>();
		for(Member member : members) {
			universityAllJsons.add(packUniversityAllJson(member));
		}
		return universityAllJsons;
	}
}
