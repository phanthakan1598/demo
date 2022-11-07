package com.example.demo.building.payload;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomInsertPayload {
	private String roomName;
	private String floorNumber;
	private String roomCapacity;
	private String buildingUuid;
	private String roomTypeUuid;
	
}
