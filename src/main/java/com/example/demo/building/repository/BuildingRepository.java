package com.example.demo.building.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.building.model.Building;
import com.example.demo.building.model.BuildingType;
import com.example.demo.university.model.University;

public interface BuildingRepository extends JpaRepository<Building, Long>{
	List<Building> findBybuildingTypeUniversity(University university);
	boolean existsByBuildingType(BuildingType buildingType);
	Optional<Building> findOneByBuildingUuid(String buildingUuid);
}
