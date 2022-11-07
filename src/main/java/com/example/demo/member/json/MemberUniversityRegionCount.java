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
public class MemberUniversityRegionCount {
	
	String universityRegionName;
//	String
	
//	int memberUniversityRegionNorthern;
//	int memberUniversityRegionCentral;
//	int memberUniversityRegionNortheastern;
//	int memberUniversityRegionWestern;
//	int memberUniversityRegionEastern;
//	int memberUniversityRegionSouthern;
//	
	public static MemberUniversityRegionCount packMemberUniversityRegionCount(Member member) {
		MemberUniversityRegionCount memberUniversityRegionCount = new MemberUniversityRegionCount();
		memberUniversityRegionCount.setUniversityRegionName(member.getUniversity().getTambon().getAmphur().getProvince().getRegion().getRegionNameEn());
//		memberUniversityRegionCount.setMemberUuid(member.getMemberUuid());
//		memberUniversityRegionCount.setMemberUsername(member.getMemberUsername());
//		memberUniversityRegionCount.setMemberRole(member.getRole().getRoleName());
//		memberUniversityRegionCount.setMemberUniversity(member.getUniversity().getUniversityNameTh());
//		memberUniversityRegionCount.setMemberStatus(member.getMemberStatus());
		return memberUniversityRegionCount;
	}
	
	public static List<MemberUniversityRegionCount> packMemberUniversityRegionCount(List<Member> members){
		List<MemberUniversityRegionCount> memberUniversityRegionCounts = new ArrayList<>();
		for(Member member : members) {
			memberUniversityRegionCounts.add(packMemberUniversityRegionCount(member));
			
		}
		
		
		
		
//		Map<MemberUniversityRegionCount, Long> counted = memberUniversityRegionCounts.stream()
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
//		counted.forEach((name, count) -> System.out.println(name + ">" + count));
		
//		System.out.println("counted : "+counted);
		
		return memberUniversityRegionCounts;
	}
	
	
}
