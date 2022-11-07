package com.example.demo.address.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.address.model.Province;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProvinceJson {
	private String provinceUuid;
	private String provinceNameTh;
	private String provinceNameEn;
	
	public static ProvinceJson packProvinceJson(Province province) {
		ProvinceJson provinceJson = new ProvinceJson();
		provinceJson.setProvinceUuid(province.getProvinceUuid());
		provinceJson.setProvinceNameTh(province.getProvinceNameTh());
		provinceJson.setProvinceNameEn(province.getProvinceNameEn());
		
		return provinceJson;
	}
	
	public static List<ProvinceJson> packProvinceJson(List<Province> provinces){
		List<ProvinceJson> provinceJsons = new ArrayList<>();
		for(Province province : provinces) {
			provinceJsons.add(packProvinceJson(province));
		}
		return provinceJsons;
	}

}
