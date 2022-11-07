package com.example.demo.building.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.building.exception.RoomTypeException;
import com.example.demo.building.model.RoomType;
import com.example.demo.building.repository.RoomTypeRepository;
import com.example.demo.university.model.University;

import lombok.Setter;

@Service
@Setter
public class RoomTypeService {
	@Autowired
	RoomTypeRepository roomTypeRepository;
	
	public RoomType findRoomTypeByUuid(String uuid) throws RoomTypeException {
        return roomTypeRepository.findOneByRoomTypeUuid(uuid).orElseThrow(RoomTypeException::roomTypeNotFound);
    }
	
	public RoomType saveRoomType(RoomType roomType) throws RoomTypeException {
    	if(Objects.isNull(roomType)) {
    		throw RoomTypeException.roomTypeIsEmptyOrNull();
    	}
		return roomTypeRepository.save(roomType);
	}
	
	public void delRoomType(RoomType roomType) throws RoomTypeException {
    	if(Objects.isNull(roomType)) {
    		throw RoomTypeException.roomTypeIsEmptyOrNull();
    	}
    	roomTypeRepository.delete(roomType);
	}
	
	public List<RoomType> getAllRoomType(University university){
		return roomTypeRepository.findByUniversity(university);
	}

}
