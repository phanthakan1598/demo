package com.example.demo.building.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.building.exception.BuildingTypeException;
import com.example.demo.building.model.BuildingType;
import com.example.demo.building.repository.BuildingTypeRepository;
import com.example.demo.university.model.University;

import lombok.Setter;

@Service
@Setter
public class BuildingTypeService {
	@Autowired
	BuildingTypeRepository buildingTypeRepository;
	
	public BuildingType findBuildingTypeByUuid(String uuid) throws BuildingTypeException {
        return buildingTypeRepository.findOneByBuildingTypeUuid(uuid).orElseThrow(BuildingTypeException::buildingTypeNotFound);
    }
	
	public BuildingType saveBuildingType(BuildingType buildingType) throws BuildingTypeException {
    	if(Objects.isNull(buildingType)) {
    		throw BuildingTypeException.buildingTypeIsEmptyOrNull();
    	}
		return buildingTypeRepository.save(buildingType);
	}
	
	public void delBuildingType(BuildingType buildingType) throws BuildingTypeException {
    	if(Objects.isNull(buildingType)) {
    		throw BuildingTypeException.buildingTypeIsEmptyOrNull();
    	}
    	buildingTypeRepository.delete(buildingType);
	}
	
	public List<BuildingType> getAllBuildingType(University university){
		return buildingTypeRepository.findByUniversity(university);
	}

}
