package com.example.demo.building.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.building.model.BuildingType;
import com.example.demo.university.model.University;

public interface BuildingTypeRepository extends JpaRepository<BuildingType, Long>{
	List<BuildingType> findByUniversity(University university);
	Optional<BuildingType> findOneByBuildingTypeUuid(String buildingTypeUuid);
}
