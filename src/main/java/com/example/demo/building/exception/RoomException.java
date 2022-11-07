package com.example.demo.building.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class RoomException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected RoomException(String code) {
        super("room."+code);
    }
    
	protected RoomException(String code, HttpStatus status) {
        super("room."+code, status);
    }
	public static RoomException roomOver() {
		return new RoomException("room.over", HttpStatus.BAD_REQUEST);
	}
	
	public static RoomException roomNameEmpty() {
		return new RoomException("roomName.empty", HttpStatus.BAD_REQUEST);
	}
	public static RoomException floorNumberEmpty() {
		return new RoomException("floorNumber.empty", HttpStatus.BAD_REQUEST);
	}
	public static RoomException roomNotFound() {
        return new RoomException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static RoomException roomCapacityIsEmptyOrNull() {
        return new RoomException("roomCapacity.emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static RoomException roomStatusIsEmptyOrNull() {
        return new RoomException("roomStatus.emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static RoomException roomIsEmptyOrNull() {
        return new RoomException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
