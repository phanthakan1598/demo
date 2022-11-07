package com.example.demo.building.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.building.business.BuildingTypeBusiness;
import com.example.demo.building.exception.BuildingTypeException;
import com.example.demo.building.json.BuildingTypeJson;
import com.example.demo.building.payload.BuildingTypeInsertPayload;
import com.example.demo.building.payload.BuildingTypeUpdatePayload;
import com.example.demo.member.exception.LogException;

@RestController
@RequestMapping("/building/buildingType")
public class BuildingTypeController {
	
	@Autowired
	BuildingTypeBusiness buildingTypeBusiness;
	
	@PostMapping(value = "/insertBuildingType")
	public ResponseEntity<Void> insertBuildingType(@RequestBody BuildingTypeInsertPayload body) throws BuildingTypeException, LogException{
		buildingTypeBusiness.insertBuildingType(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/viewAllBuildingType")
	public ResponseEntity<List<BuildingTypeJson>> viewAllBuildingType(){
		return ResponseEntity.ok(buildingTypeBusiness.viewAllBuildingType());
	}
	
	@PutMapping(value = "/updateBuildingType")
	public ResponseEntity<Void> updateBuildingType(@RequestBody BuildingTypeUpdatePayload body) throws BuildingTypeException, LogException{
		buildingTypeBusiness.updateBuildingType(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewBuildingType/{uuid}")
	public ResponseEntity<BuildingTypeJson> viewBuildingType(@PathVariable String uuid) throws BuildingTypeException{
		return ResponseEntity.ok(buildingTypeBusiness.viewBuildingType(uuid));
	}
	
	@DeleteMapping(value = "/delBuildingType/{uuid}")
	public ResponseEntity<BuildingTypeJson> delBuildingType(@PathVariable String uuid) throws BuildingTypeException, LogException{
		buildingTypeBusiness.delBuildingType(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
