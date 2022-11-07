package com.example.demo.university.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class DegreeLevelException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected DegreeLevelException(String code) {
        super("degreeLevel."+code);
    }
    
	protected DegreeLevelException(String code, HttpStatus status) {
        super("degreeLevel."+code, status);
    }
	
	public static DegreeLevelException degreeLevelNameEmpty() {
		return new DegreeLevelException("degreeLevelName.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static DegreeLevelException degreeLevelUuidEmpty() {
		return new DegreeLevelException("degreeLevelUuid.empty", HttpStatus.BAD_REQUEST);
	}
	
	
	public static DegreeLevelException degreeLevelNotFound() {
        return new DegreeLevelException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static DegreeLevelException degreeLevelIsEmptyOrNull() {
        return new DegreeLevelException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
