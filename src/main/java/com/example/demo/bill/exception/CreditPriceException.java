package com.example.demo.bill.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class CreditPriceException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected CreditPriceException(String code) {
        super("creditPrice."+code);
    }
    
	protected CreditPriceException(String code, HttpStatus status) {
        super("creditPrice."+code, status);
    }
	
	public static CreditPriceException creditPriceNotFound() {
        return new CreditPriceException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static CreditPriceException creditPriceIsEmptyOrNull() {
        return new CreditPriceException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static CreditPriceException creditPriceEmpty() {
        return new CreditPriceException("creditPrice.empty",HttpStatus.BAD_REQUEST);
    }
	public static CreditPriceException creditPriceUsed() {
        return new CreditPriceException("creditPrice.used",HttpStatus.BAD_REQUEST);
    }
}
