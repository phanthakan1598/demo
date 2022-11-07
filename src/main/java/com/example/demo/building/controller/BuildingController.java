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

import com.example.demo.building.business.BuildingBusiness;
import com.example.demo.building.exception.BuildingException;
import com.example.demo.building.exception.BuildingTypeException;
import com.example.demo.building.json.BuildingJson;
import com.example.demo.building.payload.BuildingInsertPayload;
import com.example.demo.building.payload.BuildingUpdatePayload;
import com.example.demo.member.exception.LogException;

@RestController
@RequestMapping("/building/building")
public class BuildingController {
	
	@Autowired
	BuildingBusiness buildingBusiness;
	
	@PostMapping(value = "/insertBuilding")
	public ResponseEntity<Void> insertBuilding(@RequestBody BuildingInsertPayload body) throws BuildingException, BuildingTypeException, LogException{
		buildingBusiness.insertBuilding(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/viewAllBuilding")
	public ResponseEntity<List<BuildingJson>> viewAllBuilding(){
		return ResponseEntity.ok(buildingBusiness.viewAllBuilding());
	}
	
	@PutMapping(value = "/updateBuilding")
	public ResponseEntity<Void> updateBuilding(@RequestBody BuildingUpdatePayload body) throws BuildingException, BuildingTypeException, LogException{
		buildingBusiness.updateBuilding(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewBuilding/{uuid}")
	public ResponseEntity<BuildingJson> viewBuilding(@PathVariable String uuid) throws BuildingException{
		return ResponseEntity.ok(buildingBusiness.viewBuilding(uuid));
	}
	
	@DeleteMapping(value = "/delBuilding/{uuid}")
	public ResponseEntity<BuildingJson> delBuilding(@PathVariable String uuid) throws BuildingException, LogException{
		buildingBusiness.delBuilding(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
