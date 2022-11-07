package com.example.demo.building.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class BuildingTypeException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected BuildingTypeException(String code) {
        super("buildingType."+code);
    }
    
	protected BuildingTypeException(String code, HttpStatus status) {
        super("buildingType."+code, status);
    }
	
	public static BuildingTypeException buildingTypeUsed() {
        return new BuildingTypeException("buildingType.used",HttpStatus.EXPECTATION_FAILED);
    }
	public static BuildingTypeException buildingTypeNameEmpty() {
		return new BuildingTypeException("buildingTypeName.empty", HttpStatus.BAD_REQUEST);
	}
	public static BuildingTypeException buildingTypeNotFound() {
        return new BuildingTypeException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static BuildingTypeException buildingTypeIsEmptyOrNull() {
        return new BuildingTypeException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
