package com.example.demo.building.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.building.model.RoomType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomTypeJson {
	String roomTypeUuid;
	String roomTypeName;
	
	public static RoomTypeJson packRoomTypeJson(RoomType roomType) {
		RoomTypeJson roomTypeJson = new RoomTypeJson();
		roomTypeJson.setRoomTypeName(roomType.getRoomTypeName());
		roomTypeJson.setRoomTypeUuid(roomType.getRoomTypeUuid());
		return roomTypeJson;
	}
	
	public static List<RoomTypeJson> packRoomTypeJson(List<RoomType> roomTypes){
		List<RoomTypeJson> roomTypeJsons = new ArrayList<>();
		for(RoomType roomType : roomTypes) {
			roomTypeJsons.add(packRoomTypeJson(roomType));
		}
		return roomTypeJsons;
	}
}
