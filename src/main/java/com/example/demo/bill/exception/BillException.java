package com.example.demo.bill.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class BillException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected BillException(String code) {
        super("bill."+code);
    }
    
	protected BillException(String code, HttpStatus status) {
        super("bill."+code, status);
    }
	
	public static BillException billNotFound() {
        return new BillException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static BillException billIsEmptyOrNull() {
        return new BillException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static BillException billNameEmpty() {
        return new BillException("billNameEmpty",HttpStatus.BAD_REQUEST);
    }
	public static BillException billNameUsed() {
        return new BillException("billName.used",HttpStatus.BAD_REQUEST);
    }
}
