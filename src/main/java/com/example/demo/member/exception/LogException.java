package com.example.demo.member.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class LogException extends BaseException {
	
	private static final long serialVersionUID = -3371503320561323817L;

    protected LogException(String code) {
        super("log."+code);
    }
    
	protected LogException(String code, HttpStatus status) {
        super("log."+code, status);
    }
    public static LogException logIsEmptyOrNull() {
        return new LogException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
    
    public static LogException logNotFound() {
        return new LogException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
}
