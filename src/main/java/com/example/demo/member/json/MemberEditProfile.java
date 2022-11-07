package com.example.demo.member.json;

import java.time.LocalDate;
import java.time.Period;

import com.example.demo.member.model.Member;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberEditProfile {
	String memberUsername;
	String memberNameTh;
	String memberNameEn;
	LocalDate memberBirthday;
	int memberAge;
	String memberEmail;
	String memberTel;
	String addressTh;
	String addressEn;
	String memberImg;
	
	public static MemberEditProfile packMemberEditProfile(Member member) {
		MemberEditProfile memberJson = new MemberEditProfile();
		memberJson.setMemberUsername(member.getMemberUsername());
		memberJson.setMemberNameTh(member.getMemberNameTh());
		memberJson.setMemberNameEn(member.getMemberNameEn());
		memberJson.setMemberBirthday(member.getMemberBirthday());
		memberJson.setMemberAge(Period.between(member.getMemberBirthday(), LocalDate.now()).getYears());
		memberJson.setMemberEmail(member.getMemberEmail());
		memberJson.setMemberTel(member.getMemberTel());
		memberJson.setAddressTh(member.getAddress().getAddressDetailTh()+
								" ต."+member.getAddress().getTambon().getTambonNameTh()+
								" อ."+member.getAddress().getTambon().getAmphur().getAmphurNameTh()+
								" จ."+member.getAddress().getTambon().getAmphur().getProvince().getProvinceNameTh()+
								" "+member.getAddress().getTambon().getTambonZipCode());
		memberJson.setAddressEn(member.getAddress().getAddressDetailEn()+
				" Sub-district: "+member.getAddress().getTambon().getTambonNameEn()+
				" District: "+member.getAddress().getTambon().getAmphur().getAmphurNameEn()+
				" Province: "+member.getAddress().getTambon().getAmphur().getProvince().getProvinceNameEn()+
				" "+member.getAddress().getTambon().getTambonZipCode());
		memberJson.setMemberImg(member.getMemberImg());
		return memberJson;
	}
}
