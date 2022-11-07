package com.example.demo.building.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class RoomTypeException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected RoomTypeException(String code) {
        super("roomType."+code);
    }
    
	protected RoomTypeException(String code, HttpStatus status) {
        super("roomType."+code, status);
    }
	public static RoomTypeException roomTypeUsed() {
		return new RoomTypeException("roomType.used", HttpStatus.BAD_REQUEST);
	}
	public static RoomTypeException roomTypeNameEmpty() {
		return new RoomTypeException("buildingTypeName.empty", HttpStatus.BAD_REQUEST);
	}
	public static RoomTypeException roomTypeNotFound() {
        return new RoomTypeException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static RoomTypeException roomTypeIsEmptyOrNull() {
        return new RoomTypeException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
