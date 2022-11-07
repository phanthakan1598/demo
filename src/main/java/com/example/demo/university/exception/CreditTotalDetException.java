package com.example.demo.university.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class CreditTotalDetException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected CreditTotalDetException(String code) {
        super("creditTotalDet."+code);
    }
    
	protected CreditTotalDetException(String code, HttpStatus status) {
        super("creditTotalDet."+code, status);
    }
	
	public static CreditTotalDetException creditTotalDetNotFound() {
        return new CreditTotalDetException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static CreditTotalDetException creditTotalDetIsEmptyOrNull() {
        return new CreditTotalDetException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
