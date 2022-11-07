package com.example.demo.building.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.building.model.RoomType;
import com.example.demo.university.model.University;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long>{
	List<RoomType> findByUniversity(University university);
	Optional<RoomType> findOneByRoomTypeUuid(String roomTypeUuid);
}
