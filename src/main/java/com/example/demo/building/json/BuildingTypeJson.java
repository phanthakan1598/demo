package com.example.demo.building.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.building.model.BuildingType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BuildingTypeJson {
	String buildingTypeUuid;
	String buildingTypeName;
	
	public static BuildingTypeJson packBuildingTypeJson(BuildingType buildingType) {
		BuildingTypeJson buildingTypeJson = new BuildingTypeJson();
		buildingTypeJson.setBuildingTypeName(buildingType.getBuildingTypeName());
		buildingTypeJson.setBuildingTypeUuid(buildingType.getBuildingTypeUuid());
		return buildingTypeJson;
	}
	
	public static List<BuildingTypeJson> packBuildingTypeJson(List<BuildingType> buildingTypes){
		List<BuildingTypeJson> buildingTypeJsons = new ArrayList<>();
		for(BuildingType buildingType : buildingTypes) {
			buildingTypeJsons.add(packBuildingTypeJson(buildingType));
		}
		return buildingTypeJsons;
	}
}
