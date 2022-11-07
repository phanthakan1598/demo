package com.example.demo.building.payload;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BuildingInsertPayload {
	private String buildingName;
	private String buildingCodeName;
	private int floorAmount;
	private int roomAmount;
	private String buildingTypeUuid;
	
}
