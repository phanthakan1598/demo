package com.example.demo.address.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.address.model.Amphur;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AmphurJson {
	private String amphurUuid;
	private String amphurNameTh;
	private String amphurNameEn;


	public static AmphurJson packAmphurJson(Amphur amphur) {
		AmphurJson amphurJson = new AmphurJson();
		amphurJson.setAmphurUuid(amphur.getAmphurUuid());
		amphurJson.setAmphurNameTh(amphur.getAmphurNameTh());
		amphurJson.setAmphurNameEn(amphur.getAmphurNameEn());
		
		return amphurJson;
	}
	
	public static List<AmphurJson> packAmphurJson(List<Amphur> amphurs){
		List<AmphurJson> amphurJsons = new ArrayList<>();
		for(Amphur amphur : amphurs) {
			amphurJsons.add(packAmphurJson(amphur));
		}
		return amphurJsons;
	}

}
