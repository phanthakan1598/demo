package com.example.demo.building.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.building.model.Building;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BuildingJson {
	String buildingUuid;
	String buildingName;
	String buildingCodeName;
	int floorAmount;
	int roomAmount;
	String buildingTypeUuid;
	String buildingTypeName;
	int buildingStatus;
	
	public static BuildingJson packBuildingJson(Building building) {
		BuildingJson buildingJson = new BuildingJson();
		buildingJson.setBuildingUuid(building.getBuildingUuid());
		buildingJson.setBuildingName(building.getBuildingName());
		buildingJson.setBuildingCodeName(building.getBuildingCodeName());
		buildingJson.setFloorAmount(building.getFloorAmount());
		buildingJson.setRoomAmount(building.getRoomAmount());
		buildingJson.setBuildingTypeUuid(building.getBuildingType().getBuildingTypeUuid());
		buildingJson.setBuildingTypeName(building.getBuildingType().getBuildingTypeName());
		buildingJson.setBuildingStatus(building.getBuildingStatus());
		return buildingJson;
	}
	
	public static List<BuildingJson> packBuildingJson(List<Building> buildings){
		List<BuildingJson> buildingJsons = new ArrayList<>();
		for(Building building : buildings) {
			buildingJsons.add(packBuildingJson(building));
		}
		return buildingJsons;
	}
}
