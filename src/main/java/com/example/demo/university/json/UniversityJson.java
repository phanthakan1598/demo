package com.example.demo.university.json;

import com.example.demo.university.model.University;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UniversityJson {
	private String universityNameTh;
	private String universityNameEn;
	private String universityAddress;
	int zipCode;
	String tambonUuid;
	String amphurUuid;
	String provinceUuid;
	String regionUuid;
	String universityImg;
	String addressTh;
	String number;
	String codeName;
	
	public static UniversityJson packUniversityJson(University university) {
		UniversityJson universityJson = new UniversityJson();
		universityJson.setNumber(university.getUniversityNumber());
		universityJson.setCodeName(university.getUniversityCodeName());
		universityJson.setUniversityNameTh(university.getUniversityNameTh());
		universityJson.setUniversityNameEn(university.getUniversityNameEn());
		universityJson.setUniversityAddress(university.getUniversityAddress());
		universityJson.setRegionUuid(university.getTambon().getAmphur().getProvince().getRegion().getRegionUuid());
		universityJson.setProvinceUuid(university.getTambon().getAmphur().getProvince().getProvinceUuid());
		universityJson.setAmphurUuid(university.getTambon().getAmphur().getAmphurUuid());
		universityJson.setTambonUuid(university.getTambon().getTambonUuid());
		universityJson.setZipCode(university.getTambon().getTambonZipCode());
		universityJson.setUniversityImg(university.getUniversityImg());
		
		universityJson.setAddressTh(university.getUniversityAddress()+
				" ต."+university.getTambon().getTambonNameTh()+
				" อ."+university.getTambon().getAmphur().getAmphurNameTh()+
				" จ."+university.getTambon().getAmphur().getProvince().getProvinceNameTh()+
				" "+university.getTambon().getTambonZipCode());
		return universityJson;
	}
}
