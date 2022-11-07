package com.example.demo.register.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class DayException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected DayException(String code) {
        super("day."+code);
    }
    
	protected DayException(String code, HttpStatus status) {
        super("day."+code, status);
    }
	
	public static DayException dayNotFound() {
        return new DayException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
}
