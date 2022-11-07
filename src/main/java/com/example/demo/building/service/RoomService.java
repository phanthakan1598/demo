package com.example.demo.building.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.building.exception.RoomException;
import com.example.demo.building.model.Building;
import com.example.demo.building.model.Room;
import com.example.demo.building.model.RoomType;
import com.example.demo.building.repository.RoomRepository;
import com.example.demo.university.model.University;

import lombok.Setter;

@Service
@Setter
public class RoomService {
	@Autowired
	RoomRepository roomRepository;
	
	public boolean getRoom(Building building){
        return roomRepository.existsByBuilding(building);
    }
	
	public Room findRoomByUuid(String uuid) throws RoomException {
        return roomRepository.findOneByRoomUuid(uuid).orElseThrow(RoomException::roomNotFound);
    }
	
	public Room findBuildingFloorNumber(Building building,String floorNumber) throws RoomException {
        return roomRepository.findFirstByBuildingAndFloorNumberOrderByRoomNumberDesc(building, floorNumber).orElseThrow(RoomException::roomNotFound);
    }
	
	public Room saveRoom(Room room) throws RoomException {
    	if(Objects.isNull(room)) {
    		throw RoomException.roomIsEmptyOrNull();
    	}
		return roomRepository.save(room);
	}
	
	public void delRoom(Room room) throws RoomException {
    	if(Objects.isNull(room)) {
    		throw RoomException.roomIsEmptyOrNull();
    	}
    	roomRepository.delete(room);
	}
	
	public int countRoom(University university,Building building){
		return roomRepository.countByRoomTypeUniversityAndBuilding(university, building);
	}
	
	public  List<Room> countAllRoom(University university){
		return roomRepository.countByRoomTypeUniversity(university);
	}
	
	public List<Room> getAllRoom(University university,Building building){
		return roomRepository.findByRoomTypeUniversityAndBuilding(university,building);
	}
	
	public List<Room> getRoomBuilding(Building building){
		return roomRepository.findByBuilding(building);
	}
	
	public boolean roomTypeBoolean(RoomType roomType){
		return roomRepository.existsByRoomType(roomType);
	}
	
	public boolean buildingBoolean(Building building){
		return roomRepository.existsByBuilding(building);
	}
	
	
	public boolean BuildingFloorBoolean(Building building, String floorNumber){
		return roomRepository.existsByBuildingAndFloorNumber(building,floorNumber);
	}
	

}
