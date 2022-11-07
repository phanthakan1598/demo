package com.example.demo.university.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class CreditOfTermException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected CreditOfTermException(String code) {
        super("creditOfTerm."+code);
    }
    
	protected CreditOfTermException(String code, HttpStatus status) {
        super("creditOfTerm."+code, status);
    }
	
	public static CreditOfTermException creditOfTermNotFound() {
        return new CreditOfTermException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static CreditOfTermException creditOfTermIsEmptyOrNull() {
        return new CreditOfTermException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	
	public static CreditOfTermException creditOfTermCreditMinEmpty() {
        return new CreditOfTermException("creditOfTermCreditMinEmpty",HttpStatus.BAD_REQUEST);
    }
	
	public static CreditOfTermException creditOfTermCreditMaxEmpty() {
        return new CreditOfTermException("creditOfTermCreditMaxEmpty",HttpStatus.BAD_REQUEST);
    }
	
	public static CreditOfTermException creditIncorrect() {
	    return new CreditOfTermException("credit.incorrect",HttpStatus.EXPECTATION_FAILED);
	}
	
	public static CreditOfTermException creditOfTermCreditProgramUsed() {
        return new CreditOfTermException("creditOfTermCreditProgram.used",HttpStatus.BAD_REQUEST);
    }
	
	public static CreditOfTermException creditOfTermYearEmpty() {
        return new CreditOfTermException("creditOfTermYearEmpty",HttpStatus.BAD_REQUEST);
    }
	
	public static CreditOfTermException creditOfTermEmptyEmpty() {
        return new CreditOfTermException("creditOfTermEmptyEmpty",HttpStatus.BAD_REQUEST);
    }
}
