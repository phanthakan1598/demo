package com.example.demo.address.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.address.model.Region;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegionJson {
	private String regionUuid;
	private String regionNameTh;
	private String regionNameEn;
	
	public static RegionJson packRegionJson(Region region) {
		RegionJson regionJson = new RegionJson();
		regionJson.setRegionUuid(region.getRegionUuid());
		regionJson.setRegionNameTh(region.getRegionNameTh());
		regionJson.setRegionNameEn(region.getRegionNameEn());
		return regionJson;
	}
	
	public static List<RegionJson> packRegionJson(List<Region> regions){
		List<RegionJson> regionJsons = new ArrayList<>();
		for(Region region : regions) {
			regionJsons.add(packRegionJson(region));
		}
		return regionJsons;
	}
}
