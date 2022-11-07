package com.example.demo.register.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class EnrollRegisterException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected EnrollRegisterException(String code) {
        super("enrollRegister."+code);
    }
    
	protected EnrollRegisterException(String code, HttpStatus status) {
        super("enrollRegister."+code, status);
    }
	
	public static EnrollRegisterException enrollRegisterNotFound() {
        return new EnrollRegisterException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static EnrollRegisterException enrollRegisterIsEmptyOrNull() {
        return new EnrollRegisterException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	
	public static EnrollRegisterException enrollRegisterSubjectUsed() {
        return new EnrollRegisterException("enrollRegisterSubject.used",HttpStatus.BAD_REQUEST);
    }
	
	public static EnrollRegisterException enrollRegisterSubjectTimeUsed() {
        return new EnrollRegisterException("enrollRegisterSubjectTimeUsed.used",HttpStatus.BAD_REQUEST);
    }
}
