package com.example.demo.building.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.building.exception.BuildingException;
import com.example.demo.building.exception.RoomException;
import com.example.demo.building.exception.RoomTypeException;
import com.example.demo.building.json.BuildingSelectJson;
import com.example.demo.building.json.RoomJson;
import com.example.demo.building.model.Building;
import com.example.demo.building.model.Room;
import com.example.demo.building.model.RoomType;
import com.example.demo.building.payload.RoomInsertPayload;
import com.example.demo.building.payload.RoomUpdatePayload;
import com.example.demo.building.service.BuildingService;
import com.example.demo.building.service.RoomService;
import com.example.demo.building.service.RoomTypeService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.model.University;
import com.example.demo.university.service.UniversityService;

import lombok.Setter;

@Service
@Setter
public class RoomBusiness {
	@Autowired
	UniversityService universityService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	RoomTypeService roomTypeService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	LogService logService;
	
	public void insertRoom(RoomInsertPayload body)throws RoomException, BuildingException, RoomTypeException, LogException{
		String roomName = body.getRoomName();
		String floorNumber = body.getFloorNumber();
		String roomCapacity = body.getRoomCapacity();
		Building building = buildingService.findBuildingByUuid(body.getBuildingUuid());
		RoomType roomType = roomTypeService.findRoomTypeByUuid(body.getRoomTypeUuid());
		
		
		int maxRoom = building.getRoomAmount();
		List<Room> rooms = roomService.getRoomBuilding(building);
		
		if(maxRoom == rooms.size()){
			throw RoomException.roomOver();
		}
		
		
//		System.out.println("rooms : "+rooms.size());
		
		if(roomName == null || roomName.equals("")) {
			throw RoomException.roomNameEmpty();
		}
		
		if(floorNumber == null || floorNumber.equals("")) {
			throw RoomException.floorNumberEmpty();
		}
		if(roomCapacity == null || roomCapacity.equals("")) {
			throw RoomException.roomCapacityIsEmptyOrNull();
		}
		
		
		String roomNumber = null;
		
		if(roomService.BuildingFloorBoolean(building,floorNumber)) {
			
//			System.out.println("มี");
			
			int roomMaxNum = Integer.parseInt(roomService.findBuildingFloorNumber(building, floorNumber).getRoomNumber());
			roomNumber = Integer.toString(roomMaxNum + 1);
			
		}else {
//			System.out.println("ไม่มี");
			roomNumber = "1";
		}
		
		
//		
//		System.out.println("roomCodeName : "+ roomCodeName);
//		System.out.println("maxRoom : "+ maxRoom);
//		System.out.println("roomName : "+ roomName);
//		System.out.println("roomNumber : "+ roomNumber);
//		System.out.println("floorNumber : "+ floorNumber);
//		System.out.println("roomCapacity : "+ roomCapacity);
		
		if (Integer.parseInt(roomNumber) > 99) {
			throw RoomException.roomOver();
		}
		
		if (roomNumber.length() < 2) {
			roomNumber = 0+roomNumber;
		}
		
		String roomCodeName = building.getBuildingCodeName() + floorNumber + roomNumber;
		
		
		Room room = new Room();
		room.setRoomName(roomName);
		room.setRoomCodeName(roomCodeName);
		room.setFloorNumber(floorNumber);
		room.setRoomNumber(roomNumber);
		room.setRoomCapacity(Integer.parseInt(roomCapacity) );
		room.setBuilding(building);
		room.setRoomType(roomType);
		room.setRoomStatus(1);
		
		roomService.saveRoom(room);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลห้อง");
		logService.saveLog(log);
	}
	
	public void updateRoom(RoomUpdatePayload body) throws RoomException, RoomTypeException, LogException {
		String roomName = body.getRoomName();
		int roomStatus = body.getRoomStatus();
		
		RoomType roomType = roomTypeService.findRoomTypeByUuid(body.getRoomTypeUuid());
		
		if(roomName == null || roomName.equals("")) {
			throw RoomException.roomNameEmpty();
		}
		if(Objects.isNull(roomStatus)) {
			throw RoomException.roomStatusIsEmptyOrNull();
		}
		
		Room room = roomService.findRoomByUuid(body.getRoomUuid());
		room.setRoomName(roomName);
		room.setRoomType(roomType);
		room.setRoomStatus(roomStatus);
		
		roomService.saveRoom(room);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลห้อง");
		logService.saveLog(log);

		
	}
	
	public void delRoom(String uid) throws RoomException, LogException {
		Room room = roomService.findRoomByUuid(uid);
		roomService.delRoom(room);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลห้อง");
		logService.saveLog(log);
		
	}
	
	public List<BuildingSelectJson> viewAllbuildingSelect() throws BuildingException {
		University university = jwtAuth.getCurrentUser().getUniversity();
		List<Building> buildings = buildingService.getAllBuilding(university);
		ArrayList<List<Room>> rooms = new ArrayList<>();
		
		for(Building building : buildings) {
			rooms.add(roomService.getAllRoom(university, building));
		}
		
		return BuildingSelectJson.packBuildingJson(buildings, rooms);
		
	}
	
	public List<RoomJson> viewAllRoom(String uid) throws BuildingException {
		University university = jwtAuth.getCurrentUser().getUniversity();
		Building building = buildingService.findBuildingByUuid(uid);
		return RoomJson.packRoomJson(roomService.getAllRoom(university,building));
	}
	
//	public List<BuildingSelectJson> viewAllBuildingSelcet() {
//		return BuildingSelectJson.packBuildingJson(buildingService.getAllBuilding(jwtAuth.getCurrentUser().getUniversity()));
//	}
	
	public RoomJson viewRoom(String uid) throws RoomException  {
		return RoomJson.packRoomJson(roomService.findRoomByUuid(uid));
	}
	
}
