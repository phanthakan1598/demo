package com.example.demo.address.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.address.model.Tambon;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TambonJson {
	private String tambonUuid;
	private int tambonZipCode;
	private String tambonNameTh;
	private String tambonNameEn;
	
	public static TambonJson packTambonJson(Tambon tambon) {
		TambonJson tambonJson = new TambonJson();
		tambonJson.setTambonUuid(tambon.getTambonUuid());
		tambonJson.setTambonZipCode(tambon.getTambonZipCode());
		tambonJson.setTambonNameTh(tambon.getTambonNameTh());
		tambonJson.setTambonNameEn(tambon.getTambonNameEn());
		
		return tambonJson;
	}
	
	public static List<TambonJson> packTambonJson(List<Tambon> tambons){
		List<TambonJson> tambonJsons = new ArrayList<>();
		for(Tambon tambon : tambons) {
			tambonJsons.add(packTambonJson(tambon));
		}
		return tambonJsons;
	}
}
