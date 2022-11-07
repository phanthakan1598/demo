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
public class EmailMemberJson {
	private String memberEmail;


	public static EmailMemberJson packEmailMemberJson(Member member) {
		EmailMemberJson emailMemberJson = new EmailMemberJson();
		emailMemberJson.setMemberEmail(member.getMemberEmail());
		
		return emailMemberJson;
	}
	
	public static List<EmailMemberJson> packEmailMemberJson(List<Member> members){
		List<EmailMemberJson> emailMemberJsons = new ArrayList<>();
		for(Member member : members) {
			emailMemberJsons.add(packEmailMemberJson(member));
		}
		return emailMemberJsons;
	}

}