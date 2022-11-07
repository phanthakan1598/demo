package com.example.demo.building.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.building.exception.BuildingTypeException;
import com.example.demo.building.json.BuildingTypeJson;
import com.example.demo.building.model.BuildingType;
import com.example.demo.building.payload.BuildingTypeInsertPayload;
import com.example.demo.building.payload.BuildingTypeUpdatePayload;
import com.example.demo.building.service.BuildingService;
import com.example.demo.building.service.BuildingTypeService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.service.UniversityService;

import lombok.Setter;

@Service
@Setter
public class BuildingTypeBusiness {
	@Autowired
	UniversityService universityService;
	
	@Autowired
	BuildingTypeService buildingTypeService;
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	LogService logService;
	
	public void insertBuildingType(BuildingTypeInsertPayload body)throws BuildingTypeException, LogException{
		String buildingTypeName = body.getBuildingTypeName();
		
		if(buildingTypeName == null || buildingTypeName.equals("")) {
			throw BuildingTypeException.buildingTypeNameEmpty();
		}
		
		BuildingType buildingType = new BuildingType();
		buildingType.setBuildingTypeName(buildingTypeName);
		buildingType.setUniversity(jwtAuth.getCurrentUser().getUniversity());
		buildingTypeService.saveBuildingType(buildingType);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลประเภทอาคาร");
		logService.saveLog(log);
	}
	
	public void updateBuildingType(BuildingTypeUpdatePayload body) throws BuildingTypeException, LogException {
		String buildingTypeName = body.getBuildingTypeName();
		
		if(buildingTypeName == null || buildingTypeName.equals("")) {
			throw BuildingTypeException.buildingTypeNameEmpty();
		}
		
		BuildingType buildingType = buildingTypeService.findBuildingTypeByUuid(body.getBuildingTypeUuid());
		buildingType.setBuildingTypeName(buildingTypeName);
		buildingTypeService.saveBuildingType(buildingType);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลประเภทอาคาร");
		logService.saveLog(log);
		
	}
	
	public void delBuildingType(String uid) throws BuildingTypeException, LogException {
		BuildingType buildingType = buildingTypeService.findBuildingTypeByUuid(uid);
		if(buildingService.buildingTypeBoolean(buildingType)){
			throw BuildingTypeException.buildingTypeUsed();
		}
		buildingTypeService.delBuildingType(buildingType);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลประเภทอาคาร");
		logService.saveLog(log);
		
	}
	
	public List<BuildingTypeJson> viewAllBuildingType() {
		return BuildingTypeJson.packBuildingTypeJson(buildingTypeService.getAllBuildingType(jwtAuth.getCurrentUser().getUniversity()));
	}
	
	public BuildingTypeJson viewBuildingType(String uid) throws BuildingTypeException  {
		return BuildingTypeJson.packBuildingTypeJson(buildingTypeService.findBuildingTypeByUuid(uid));
	}
	
}
