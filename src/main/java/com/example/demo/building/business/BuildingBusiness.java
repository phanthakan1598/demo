package com.example.demo.building.business;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.building.exception.BuildingException;
import com.example.demo.building.exception.BuildingTypeException;
import com.example.demo.building.json.BuildingJson;
import com.example.demo.building.model.Building;
import com.example.demo.building.model.BuildingType;
import com.example.demo.building.payload.BuildingInsertPayload;
import com.example.demo.building.payload.BuildingUpdatePayload;
import com.example.demo.building.service.BuildingService;
import com.example.demo.building.service.BuildingTypeService;
import com.example.demo.building.service.RoomService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.service.UniversityService;

import lombok.Setter;

@Service
@Setter
public class BuildingBusiness {
	@Autowired
	UniversityService universityService;
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	BuildingTypeService buildingTypeService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	LogService logService;
	
	public void insertBuilding(BuildingInsertPayload body)throws BuildingException, BuildingTypeException, LogException{
		String buildingName = body.getBuildingName();
		String buildingCodeName = body.getBuildingCodeName();
		int floorAmount = body.getFloorAmount();
		int roomAmount = body.getRoomAmount();
		BuildingType buildingType = buildingTypeService.findBuildingTypeByUuid(body.getBuildingTypeUuid());
		
		if(buildingName == null || buildingName.equals("")) {
			throw BuildingException.buildingNameEmpty();
		}
		if(buildingCodeName == null || buildingCodeName.equals("")) {
			throw BuildingException.buildingCodeNameEmpty();
		}
		if(Objects.isNull(floorAmount) || floorAmount == 0) {
			throw BuildingException.floorAmountIsEmptyOrNull();
		}
		if(Objects.isNull(roomAmount) || roomAmount == 0) {
			throw BuildingException.roomAmountIsEmptyOrNull();
		}
		
		Building building = new Building();
		building.setBuildingName(buildingName);
		building.setBuildingCodeName(buildingCodeName);
		building.setFloorAmount(floorAmount);
		building.setRoomAmount(roomAmount);
		building.setBuildingType(buildingType);
		building.setBuildingStatus(1);
		buildingService.saveBuilding(building);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลอาคาร");
		logService.saveLog(log);
	}
	
	public void updateBuilding(BuildingUpdatePayload body) throws BuildingException, BuildingTypeException, LogException {
		String buildingName = body.getBuildingName();
		String buildingCodeName = body.getBuildingCodeName();
		int buildingStatus = body.getBuildingStatus();
		BuildingType buildingType = buildingTypeService.findBuildingTypeByUuid(body.getBuildingTypeUuid());
		
		if(buildingName == null || buildingName.equals("")) {
			throw BuildingException.buildingNameEmpty();
		}
		if(buildingCodeName == null || buildingCodeName.equals("")) {
			throw BuildingException.buildingCodeNameEmpty();
		}
		if(Objects.isNull(buildingStatus)) {
			throw BuildingException.buildingStatusIsEmptyOrNull();
		}
		
		Building building = buildingService.findBuildingByUuid(body.getBuildingUuid());
		building.setBuildingName(buildingName);
		building.setBuildingCodeName(buildingCodeName);
		building.setBuildingType(buildingType);
		building.setBuildingStatus(buildingStatus);
		buildingService.saveBuilding(building);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลอาคาร");
		logService.saveLog(log);
		
	}
	
	public void delBuilding(String uid) throws BuildingException, LogException {
		Building building = buildingService.findBuildingByUuid(uid);
		if(roomService.buildingBoolean(building)){
			throw BuildingException.buildingUsed();
		}
		buildingService.delBuilding(building);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลอาคาร");
		logService.saveLog(log);
		
	}
	
	public List<BuildingJson> viewAllBuilding() {
		return BuildingJson.packBuildingJson(buildingService.getAllBuilding(jwtAuth.getCurrentUser().getUniversity()));
	}
	
	public BuildingJson viewBuilding(String uid) throws BuildingException  {
		return BuildingJson.packBuildingJson(buildingService.findBuildingByUuid(uid));
	}
	
}
