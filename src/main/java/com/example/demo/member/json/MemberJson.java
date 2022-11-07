package com.example.demo.member.json;

import java.time.LocalDate;
import java.time.Period;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberFaculty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberJson {
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
	String roleUid;
	String roleName;
	String amphurUuid;
	String provinceUuid;
	String regionUuid;
	String addressTh;
	String addressEn;
	String memberImg;
	String Fac;
	String Pro;
	String FacUid;
	String ProUid;
	String MemberFac;
	
	public static MemberJson packMemberFacProJson(MemberFaculty memberFaculty) {
		MemberJson memberFacProJson = new MemberJson();
		
		memberFacProJson.setMemberUuid(memberFaculty.getMember().getMemberUuid());
		memberFacProJson.setMemberUsername(memberFaculty.getMember().getMemberUsername());
		memberFacProJson.setMemberNameTh(memberFaculty.getMember().getMemberNameTh());
		memberFacProJson.setMemberNameEn(memberFaculty.getMember().getMemberNameEn());
		memberFacProJson.setMemberBirthday(memberFaculty.getMember().getMemberBirthday().plusYears(543));
		memberFacProJson.setMemberAge(Period.between(memberFaculty.getMember().getMemberBirthday().plusYears(543), LocalDate.now()).getYears());
		memberFacProJson.setMemberEmail(memberFaculty.getMember().getMemberEmail());
		memberFacProJson.setMemberTel(memberFaculty.getMember().getMemberTel());
		memberFacProJson.setAddressUuid(memberFaculty.getMember().getAddress().getAddressUuid());
		memberFacProJson.setTambonUuid(memberFaculty.getMember().getAddress().getTambon().getTambonUuid());
		memberFacProJson.setZipCode(memberFaculty.getMember().getAddress().getTambon().getTambonZipCode());
		memberFacProJson.setAmphurUuid(memberFaculty.getMember().getAddress().getTambon().getAmphur().getAmphurUuid());
		memberFacProJson.setProvinceUuid(memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getProvinceUuid());
		memberFacProJson.setRegionUuid(memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getRegion().getRegionUuid());
		
		memberFacProJson.setAddressTh(memberFaculty.getMember().getAddress().getAddressDetailTh()+
								" ต."+memberFaculty.getMember().getAddress().getTambon().getTambonNameTh()+
								" อ."+memberFaculty.getMember().getAddress().getTambon().getAmphur().getAmphurNameTh()+
								" จ."+memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getProvinceNameTh()+
								" "+memberFaculty.getMember().getAddress().getTambon().getTambonZipCode());
		memberFacProJson.setAddressEn(memberFaculty.getMember().getAddress().getAddressDetailEn()+
				" Sub-district: "+memberFaculty.getMember().getAddress().getTambon().getTambonNameEn()+
				" District: "+memberFaculty.getMember().getAddress().getTambon().getAmphur().getAmphurNameEn()+
				" Province: "+memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getProvinceNameEn()+
				" "+memberFaculty.getMember().getAddress().getTambon().getTambonZipCode());
		memberFacProJson.setMemberImg(memberFaculty.getMember().getMemberImg());
		
		memberFacProJson.setFacUid(memberFaculty.getFaculty().getFacultyUuid());
		memberFacProJson.setProUid(memberFaculty.getProgram().getProgramUuid());
		memberFacProJson.setFac(memberFaculty.getFaculty().getFacultyNameEn());
		memberFacProJson.setPro(memberFaculty.getProgram().getProgramNameEn());
		
		memberFacProJson.setMemberFac(memberFaculty.getMemberFacultyUuid());
		memberFacProJson.setRoleUid(memberFaculty.getMember().getRole().getRoleUuid());
		memberFacProJson.setRoleName(memberFaculty.getMember().getRole().getRoleName());
		return memberFacProJson;
	}
	
	public static MemberJson packMemberFacJson(MemberFaculty memberFaculty) {
		MemberJson memberFacJson = new MemberJson();
		memberFacJson.setMemberUuid(memberFaculty.getMember().getMemberUuid());
		memberFacJson.setMemberUsername(memberFaculty.getMember().getMemberUsername());
		memberFacJson.setMemberNameTh(memberFaculty.getMember().getMemberNameTh());
		memberFacJson.setMemberNameEn(memberFaculty.getMember().getMemberNameEn());
		memberFacJson.setMemberBirthday(memberFaculty.getMember().getMemberBirthday());
		memberFacJson.setMemberAge(Period.between(memberFaculty.getMember().getMemberBirthday(), LocalDate.now()).getYears());
		memberFacJson.setMemberEmail(memberFaculty.getMember().getMemberEmail());
		memberFacJson.setMemberTel(memberFaculty.getMember().getMemberTel());
		memberFacJson.setAddressUuid(memberFaculty.getMember().getAddress().getAddressUuid());
		memberFacJson.setTambonUuid(memberFaculty.getMember().getAddress().getTambon().getTambonUuid());
		memberFacJson.setZipCode(memberFaculty.getMember().getAddress().getTambon().getTambonZipCode());
		memberFacJson.setAmphurUuid(memberFaculty.getMember().getAddress().getTambon().getAmphur().getAmphurUuid());
		memberFacJson.setProvinceUuid(memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getProvinceUuid());
		memberFacJson.setRegionUuid(memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getRegion().getRegionUuid());
		
		memberFacJson.setAddressTh(memberFaculty.getMember().getAddress().getAddressDetailTh()+
								" ต."+memberFaculty.getMember().getAddress().getTambon().getTambonNameTh()+
								" อ."+memberFaculty.getMember().getAddress().getTambon().getAmphur().getAmphurNameTh()+
								" จ."+memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getProvinceNameTh()+
								" "+memberFaculty.getMember().getAddress().getTambon().getTambonZipCode());
		memberFacJson.setAddressEn(memberFaculty.getMember().getAddress().getAddressDetailEn()+
				" Sub-district: "+memberFaculty.getMember().getAddress().getTambon().getTambonNameEn()+
				" District: "+memberFaculty.getMember().getAddress().getTambon().getAmphur().getAmphurNameEn()+
				" Province: "+memberFaculty.getMember().getAddress().getTambon().getAmphur().getProvince().getProvinceNameEn()+
				" "+memberFaculty.getMember().getAddress().getTambon().getTambonZipCode());
		memberFacJson.setMemberImg(memberFaculty.getMember().getMemberImg());
		
		memberFacJson.setMemberFac(memberFaculty.getMemberFacultyUuid());
		memberFacJson.setFac(memberFaculty.getFaculty().getFacultyUuid());
		memberFacJson.setFac(memberFaculty.getFaculty().getFacultyNameEn());
		memberFacJson.setRoleUid(memberFaculty.getMember().getRole().getRoleUuid());
		memberFacJson.setRoleName(memberFaculty.getMember().getRole().getRoleName());
		return memberFacJson;
	}
	
	public static MemberJson packMemberJson(Member member) {
		MemberJson memberJson = new MemberJson();
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
		memberJson.setRoleUid(member.getRole().getRoleUuid());
		memberJson.setRoleName(member.getRole().getRoleName());
		return memberJson;
	}
}
