package com.example.demo.building.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.building.exception.RoomTypeException;
import com.example.demo.building.json.RoomTypeJson;
import com.example.demo.building.model.RoomType;
import com.example.demo.building.payload.RoomTypeInsertPayload;
import com.example.demo.building.payload.RoomTypeUpdatePayload;
import com.example.demo.building.service.RoomService;
import com.example.demo.building.service.RoomTypeService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.service.UniversityService;

import lombok.Setter;

@Service
@Setter
public class RoomTypeBusiness {
	@Autowired
	UniversityService universityService;
	
	@Autowired
	RoomTypeService roomTypeService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	LogService logService;
	
	public void insertRoomType(RoomTypeInsertPayload body)throws RoomTypeException, LogException{
		String roomTypeName = body.getRoomTypeName();
		
		if(roomTypeName == null || roomTypeName.equals("")) {
			throw RoomTypeException.roomTypeNameEmpty();
		}
		
		RoomType roomType = new RoomType();
		roomType.setRoomTypeName(roomTypeName);
		roomType.setUniversity(jwtAuth.getCurrentUser().getUniversity());
		roomTypeService.saveRoomType(roomType);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลประเภทห้อง");
		logService.saveLog(log);
	}
	
	public void updateRoomType(RoomTypeUpdatePayload body) throws RoomTypeException, LogException {
		String roomTypeName = body.getRoomTypeName();
		
		if(roomTypeName == null || roomTypeName.equals("")) {
			throw RoomTypeException.roomTypeNameEmpty();
		}
		
		RoomType roomType = roomTypeService.findRoomTypeByUuid(body.getRoomTypeUuid());
		roomType.setRoomTypeName(roomTypeName);
		roomTypeService.saveRoomType(roomType);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลประเภทห้อง");
		logService.saveLog(log);
		
	}
	
	public void delRoomType(String uid) throws RoomTypeException, LogException {
		RoomType roomType = roomTypeService.findRoomTypeByUuid(uid);
		if(roomService.roomTypeBoolean(roomType)){
			throw RoomTypeException.roomTypeUsed();
		}
		roomTypeService.delRoomType(roomType);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลประเภทห้อง");
		logService.saveLog(log);
		
	}
	
	public List<RoomTypeJson> viewAllRoomType() {
		return RoomTypeJson.packRoomTypeJson(roomTypeService.getAllRoomType(jwtAuth.getCurrentUser().getUniversity()));
	}
	
	public RoomTypeJson viewRoomType(String uid) throws RoomTypeException  {
		return RoomTypeJson.packRoomTypeJson(roomTypeService.findRoomTypeByUuid(uid));
	}
	
}
