package com.example.demo.building.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.building.exception.BuildingException;
import com.example.demo.building.model.Building;
import com.example.demo.building.model.BuildingType;
import com.example.demo.building.repository.BuildingRepository;
import com.example.demo.university.model.University;

import lombok.Setter;

@Service
@Setter
public class BuildingService {
	@Autowired
	BuildingRepository buildingRepository;
	
	public Building findBuildingByUuid(String uuid) throws BuildingException {
        return buildingRepository.findOneByBuildingUuid(uuid).orElseThrow(BuildingException::buildingNotFound);
    }
	
	public Building saveBuilding(Building building) throws BuildingException {
    	if(Objects.isNull(building)) {
    		throw BuildingException.buildingIsEmptyOrNull();
    	}
		return buildingRepository.save(building);
	}
	
	public void delBuilding(Building building) throws BuildingException {
    	if(Objects.isNull(building)) {
    		throw BuildingException.buildingIsEmptyOrNull();
    	}
    	buildingRepository.delete(building);
	}
	
	public boolean buildingTypeBoolean(BuildingType buildingType){
		return buildingRepository.existsByBuildingType(buildingType);
	}
	
	public List<Building> getAllBuilding(University university){
		return buildingRepository.findBybuildingTypeUniversity(university);
	}

}
