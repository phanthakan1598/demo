package com.example.demo.building.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.building.model.Room;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomJson {
	String roomUuid;
	String roomName;
	String roomCodeName;
	int roomCapacity;
	String roomTypeUuid;
	String roomTypeName;
	int roomStatus;
	String floorNumber;
	String roomNumber;
	String buildingName;
	
	public static RoomJson packRoomJson(Room room) {
		RoomJson roomJson = new RoomJson();
		roomJson.setRoomUuid(room.getRoomUuid());
		roomJson.setRoomName(room.getRoomName());
		roomJson.setRoomCodeName(room.getRoomCodeName());
		roomJson.setRoomCapacity(room.getRoomCapacity());
		roomJson.setRoomTypeUuid(room.getRoomType().getRoomTypeUuid());
		roomJson.setRoomTypeName(room.getRoomType().getRoomTypeName());
		roomJson.setRoomStatus(room.getRoomStatus());
		roomJson.setFloorNumber(room.getFloorNumber());
		roomJson.setRoomNumber(room.getRoomNumber());
		roomJson.setBuildingName(room.getBuilding().getBuildingName());
		return roomJson;
	}
	
	public static List<RoomJson> packRoomJson(List<Room> rooms){
		List<RoomJson> roomJsons = new ArrayList<>();
		for(Room room : rooms) {
			roomJsons.add(packRoomJson(room));
		}
		return roomJsons;
	}
}
