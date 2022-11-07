package com.example.demo.member.json.personal;

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
public class MemberPersonalJson {
	String memberUuid;
	String memberUsername;
	String memberNameTh;
	String memberNameEn;
	LocalDate memberBirthday;
	int memberAge;
	String memberEmail;
	String memberTel;
	String addressUuid;
	String tambonUuid;
	int zipCode;
	String amphurUuid;
	String provinceUuid;
	String regionUuid;
	String addressTh;
	String addressEn;
	String memberImg;
	
	public static MemberPersonalJson packMemberPersonalJson(Member member) {
		MemberPersonalJson memberJson = new MemberPersonalJson();
		memberJson.setMemberUuid(member.getMemberUuid());
		memberJson.setMemberUsername(member.getMemberUsername());
		memberJson.setMemberNameTh(member.getMemberNameTh());
		memberJson.setMemberNameEn(member.getMemberNameEn());
		memberJson.setMemberBirthday(member.getMemberBirthday());
		memberJson.setMemberAge(Period.between(member.getMemberBirthday(), LocalDate.now()).getYears());
		memberJson.setMemberEmail(member.getMemberEmail());
		memberJson.setMemberTel(member.getMemberTel());
		memberJson.setAddressUuid(member.getAddress().getAddressUuid());
		memberJson.setTambonUuid(member.getAddress().getTambon().getTambonUuid());
		memberJson.setZipCode(member.getAddress().getTambon().getTambonZipCode());
		memberJson.setAmphurUuid(member.getAddress().getTambon().getAmphur().getAmphurUuid());
		memberJson.setProvinceUuid(member.getAddress().getTambon().getAmphur().getProvince().getProvinceUuid());
		memberJson.setRegionUuid(member.getAddress().getTambon().getAmphur().getProvince().getRegion().getRegionUuid());
		
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
