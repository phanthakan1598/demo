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

import com.example.demo.building.business.RoomBusiness;
import com.example.demo.building.exception.BuildingException;
import com.example.demo.building.exception.RoomException;
import com.example.demo.building.exception.RoomTypeException;
import com.example.demo.building.json.BuildingSelectJson;
import com.example.demo.building.json.RoomJson;
import com.example.demo.building.payload.RoomInsertPayload;
import com.example.demo.building.payload.RoomUpdatePayload;
import com.example.demo.member.exception.LogException;

@RestController
@RequestMapping("/building/room")
public class RoomController {
	
	@Autowired
	RoomBusiness roomBusiness;
	
	@PostMapping(value = "/insertRoom")
	public ResponseEntity<Void> insertRoom(@RequestBody RoomInsertPayload body) throws RoomException, BuildingException, RoomTypeException, LogException {
		roomBusiness.insertRoom(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/viewAllbuildingSelect")
	public ResponseEntity<List<BuildingSelectJson>> viewAllbuildingSelect() throws BuildingException{
		return ResponseEntity.ok(roomBusiness.viewAllbuildingSelect());
	}
	
	@GetMapping(value = "/viewAllRoom/{uid}")
	public ResponseEntity<List<RoomJson>> viewAllRoom(@PathVariable String uid) throws BuildingException{
		return ResponseEntity.ok(roomBusiness.viewAllRoom(uid));
	}
	
	@PutMapping(value = "/updateRoom")
	public ResponseEntity<Void> updateRoom(@RequestBody RoomUpdatePayload body) throws RoomException, RoomTypeException, LogException{
		roomBusiness.updateRoom(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewRoom/{uuid}")
	public ResponseEntity<RoomJson> viewRoom(@PathVariable String uuid) throws RoomException{
		return ResponseEntity.ok(roomBusiness.viewRoom(uuid));
	}
	
	@DeleteMapping(value = "/delRoom/{uuid}")
	public ResponseEntity<RoomJson> delRoom(@PathVariable String uuid) throws RoomException, LogException{
		roomBusiness.delRoom(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
