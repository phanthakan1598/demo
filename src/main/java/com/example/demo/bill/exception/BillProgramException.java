package com.example.demo.bill.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class BillProgramException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected BillProgramException(String code) {
        super("billProgram."+code);
    }
    
	protected BillProgramException(String code, HttpStatus status) {
        super("billProgram."+code, status);
    }
	
	public static BillProgramException billProgramNotFound() {
        return new BillProgramException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static BillProgramException billProgramIsEmptyOrNull() {
        return new BillProgramException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	
	public static BillProgramException billProgramUsed() {
        return new BillProgramException("billProgram.used",HttpStatus.BAD_REQUEST);
    }
	
	public static BillProgramException billProgramIncorrect() {
        return new BillProgramException("billProgram.incorrect",HttpStatus.BAD_REQUEST);
    }
}
