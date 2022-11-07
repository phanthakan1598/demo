package com.example.demo.university.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class RegistrationDateException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected RegistrationDateException(String code) {
        super("registrationDate."+code);
    }
    
	protected RegistrationDateException(String code, HttpStatus status) {
        super("registrationDate."+code, status);
    }
	
	public static RegistrationDateException registrationDateNotFound() {
        return new RegistrationDateException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static RegistrationDateException registrationDateIsEmptyOrNull() {
        return new RegistrationDateException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static RegistrationDateException registrationDateUsed() {
        return new RegistrationDateException("registrationDate.used",HttpStatus.BAD_REQUEST);
    }
	public static RegistrationDateException registrationDateRangeEmpty() {
        return new RegistrationDateException("registrationDateRange.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static RegistrationDateException levelClassEmpty() {
        return new RegistrationDateException("levelClass.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static RegistrationDateException typeEmpty() {
        return new RegistrationDateException("type.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static RegistrationDateException registrationDateBeginDateEmpty() {
        return new RegistrationDateException("registrationDateBeginDateEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static RegistrationDateException registrationDateEndDateEmpty() {
        return new RegistrationDateException("registrationDateEndDateEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static RegistrationDateException registrationDateYearEmpty() {
        return new RegistrationDateException("registrationDateYearEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static RegistrationDateException registrationDateTermEmpty() {
        return new RegistrationDateException("registrationDateTermEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static RegistrationDateException registrationDateDegreeLevelUidEmpty() {
        return new RegistrationDateException("registrationDateDegreeLevelUidEmpty.empty",HttpStatus.BAD_REQUEST);
    }
}
