package com.example.demo.building.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.building.model.Building;
import com.example.demo.building.model.Room;
import com.example.demo.building.model.RoomType;
import com.example.demo.university.model.University;

public interface RoomRepository extends JpaRepository<Room, Long>{
	List<Room> findByRoomTypeUniversityAndBuilding(University university,Building building);
	List<Room> findByBuilding(Building building);
	int countByRoomTypeUniversityAndBuilding(University university,Building building);
	List<Room> countByRoomTypeUniversity(University university);
	boolean existsByRoomType(RoomType roomType);
	boolean existsByBuilding(Building building);
	boolean existsByBuildingAndFloorNumber(Building building, String floorNumber);
	Optional<Room> findOneByRoomUuid(String roomUuid);
	
	Optional<Room> findFirstByBuildingAndFloorNumberOrderByRoomNumberDesc(Building building,String floorNumber);
}
