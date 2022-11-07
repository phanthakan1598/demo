package com.example.demo.building.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.building.model.Building;
import com.example.demo.building.model.Room;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BuildingSelectJson {
	
	String buildingUuid;
	String buildingName;
	String buildingCodeName;
	int floorAmount;
	int roomAmount;
	int roomEmptyAmount;
//	List<RoomJson> roomJsons;
	
	public static BuildingSelectJson packBuildingJson(Building building ,ArrayList<List<Room>> room, int index) {
		
		BuildingSelectJson buildingJson = new BuildingSelectJson();
		buildingJson.setBuildingUuid(building.getBuildingUuid());
		buildingJson.setBuildingName(building.getBuildingName());
		buildingJson.setBuildingCodeName(building.getBuildingCodeName());
		buildingJson.setFloorAmount(building.getFloorAmount());
		buildingJson.setRoomAmount(building.getRoomAmount());
		buildingJson.setRoomEmptyAmount(building.getRoomAmount() - room.get(index).size());
		
		return buildingJson;
	}
	
	public static List<BuildingSelectJson> packBuildingJson(List<Building> buildings,ArrayList<List<Room>> rooms){
		List<BuildingSelectJson> buildingSelectJsons = new ArrayList<>();
		int index = 0;
		for(Building building : buildings) {
			buildingSelectJsons.add(packBuildingJson(building, rooms, index));
			index++;
		}
		return buildingSelectJsons;
	}
	
}
