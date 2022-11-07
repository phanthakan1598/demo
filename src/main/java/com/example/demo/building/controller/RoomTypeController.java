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

import com.example.demo.building.business.RoomTypeBusiness;
import com.example.demo.building.exception.RoomTypeException;
import com.example.demo.building.json.RoomTypeJson;
import com.example.demo.building.payload.RoomTypeInsertPayload;
import com.example.demo.building.payload.RoomTypeUpdatePayload;
import com.example.demo.member.exception.LogException;

@RestController
@RequestMapping("/building/roomType")
public class RoomTypeController {
	
	@Autowired
	RoomTypeBusiness roomTypeBusiness;
	
	@PostMapping(value = "/insertRoomType")
	public ResponseEntity<Void> insertRoomType(@RequestBody RoomTypeInsertPayload body) throws RoomTypeException, LogException{
		roomTypeBusiness.insertRoomType(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/viewAllRoomType")
	public ResponseEntity<List<RoomTypeJson>> viewAllRoomType(){
		return ResponseEntity.ok(roomTypeBusiness.viewAllRoomType());
	}
	
	@PutMapping(value = "/updateRoomType")
	public ResponseEntity<Void> updateRoomType(@RequestBody RoomTypeUpdatePayload body) throws RoomTypeException, LogException{
		roomTypeBusiness.updateRoomType(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewRoomType/{uuid}")
	public ResponseEntity<RoomTypeJson> viewRoomType(@PathVariable String uuid) throws RoomTypeException{
		return ResponseEntity.ok(roomTypeBusiness.viewRoomType(uuid));
	}
	
	@DeleteMapping(value = "/delRoomType/{uuid}")
	public ResponseEntity<RoomTypeJson> delRoomType(@PathVariable String uuid) throws RoomTypeException, LogException{
		roomTypeBusiness.delRoomType(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
