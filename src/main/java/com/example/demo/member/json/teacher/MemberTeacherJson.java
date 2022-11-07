package com.example.demo.member.json.teacher;

import java.time.LocalDate;
import java.time.Period;

import com.example.demo.member.model.MemberFaculty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberTeacherJson {
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
	String memberFacultyUid;
	String memberFacultyName;
	
	public static MemberTeacherJson packMemberTeacherJson(MemberFaculty memberFaculty) {
		MemberTeacherJson memberJson = new MemberTeacherJson();
		memberJson.setMemberUuid(memberFaculty.getMember().getMemberUuid());
		memberJson.setMemberUsername(memberFaculty.getMember().getMemberUsername());
		memberJson.setMemberNameTh(memberFaculty.getMember().getMemberNameTh());
		memberJson.setMemberNameEn(memberFaculty.getMember().getMemberNameEn());
		memberJson.setMemberBirthday(memberFaculty.getMember().getMemberBirthday());
		memberJson.setMemberAge(Period.between(memberFaculty.getMember().getMemberBirthday(), LocalDate.now()).getYears());
		memberJson.setMemberEmail(memberFaculty.getMember().getMemberEmail());
		memberJson.setMemberTel(memberFaculty.getMember().getMemberTel());
		memberJson.setAddressUuid(memberFaculty.getMember().getAddress().getAddressUuid());
		memberJson.setTambonUuid(memberFaculty.getMember().getAddress().getTambon().getTambonUuid());
		memberJson.setZipCode(memberFaculty.getMember().getAddress().getTambon().getTambonZipCode());
		memberJson.setAmphurUuid(memberFaculty.getMember().getAddress().getTambon().getAmphur().getAmphurUuid());
		memberJson.setProvinceUuid(memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getProvinceUuid());
		memberJson.setRegionUuid(memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getRegion().getRegionUuid());
		
		memberJson.setAddressTh(memberFaculty.getMember().getAddress().getAddressDetailTh()+
								" ต."+memberFaculty.getMember().getAddress().getTambon().getTambonNameTh()+
								" อ."+memberFaculty.getMember().getAddress().getTambon().getAmphur().getAmphurNameTh()+
								" จ."+memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getProvinceNameTh()+
								" "+memberFaculty.getMember().getAddress().getTambon().getTambonZipCode());
		memberJson.setAddressEn(memberFaculty.getMember().getAddress().getAddressDetailEn()+
				" Sub-district: "+memberFaculty.getMember().getAddress().getTambon().getTambonNameEn()+
				" District: "+memberFaculty.getMember().getAddress().getTambon().getAmphur().getAmphurNameEn()+
				" Province: "+memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getProvinceNameEn()+
				" "+memberFaculty.getMember().getAddress().getTambon().getTambonZipCode());
		memberJson.setMemberImg(memberFaculty.getMember().getMemberImg());
		memberJson.setMemberFacultyUid(memberFaculty.getFaculty().getFacultyUuid());
		memberJson.setMemberFacultyName(memberFaculty.getFaculty().getFacultyNameEn());
		return memberJson;
	}
}
