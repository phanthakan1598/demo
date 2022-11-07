package com.example.demo.bill.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class BillDetException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected BillDetException(String code) {
        super("billDet."+code);
    }
    
	protected BillDetException(String code, HttpStatus status) {
        super("billDet."+code, status);
    }
	public static BillDetException billDetNameEmpty() {
        return new BillDetException("billDetName.empty",HttpStatus.EXPECTATION_FAILED);
    }
	public static BillDetException billDetNameUsed() {
        return new BillDetException("billDetName.used",HttpStatus.BAD_REQUEST);
    }
	public static BillDetException billDetPriceIsEmptyOrNull() {
        return new BillDetException("billDetPrice.emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static BillDetException billDetNotFound() {
        return new BillDetException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static BillDetException billDetIsEmptyOrNull() {
        return new BillDetException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
