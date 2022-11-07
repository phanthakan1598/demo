package com.example.demo.university.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class CreditTotalException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected CreditTotalException(String code) {
        super("creditTotal."+code);
    }
    
	protected CreditTotalException(String code, HttpStatus status) {
        super("creditTotal."+code, status);
    }
	
	public static CreditTotalException creditTotalNotFound() {
        return new CreditTotalException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static CreditTotalException creditTotalIsEmptyOrNull() {
        return new CreditTotalException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	
	public static CreditTotalException creditTotalMemberUidEmpty() {
        return new CreditTotalException("creditTotalMemberUidEmpty",HttpStatus.EXPECTATION_FAILED);
    }
	
	public static CreditTotalException creditTotalTotalEmpty() {
        return new CreditTotalException("creditTotalTotalEmpty",HttpStatus.EXPECTATION_FAILED);
    }
	
	public static CreditTotalException creditCriterionEmpty() {
        return new CreditTotalException("creditCriterionEmpty",HttpStatus.EXPECTATION_FAILED);
    }
}
