package com.example.demo.building.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class BuildingException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected BuildingException(String code) {
        super("building."+code);
    }
    
	protected BuildingException(String code, HttpStatus status) {
        super("building."+code, status);
    }
	
	public static BuildingException buildingNameEmpty() {
		return new BuildingException("buildingName.empty", HttpStatus.BAD_REQUEST);
	}
	public static BuildingException buildingCodeNameEmpty() {
		return new BuildingException("buildingCodeName.empty", HttpStatus.BAD_REQUEST);
	}
	public static BuildingException buildingNotFound() {
        return new BuildingException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static BuildingException floorAmountIsEmptyOrNull() {
        return new BuildingException("floorAmount.emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static BuildingException roomAmountIsEmptyOrNull() {
        return new BuildingException("roomAmount.emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static BuildingException buildingUsed() {
        return new BuildingException("building.used",HttpStatus.EXPECTATION_FAILED);
    }
	public static BuildingException buildingStatusIsEmptyOrNull() {
        return new BuildingException("buildingStatus.emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static BuildingException buildingIsEmptyOrNull() {
        return new BuildingException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
