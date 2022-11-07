package com.example.demo.address.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class AddressException extends BaseException {
	
	private static final long serialVersionUID = -3371503320561323817L;

    protected AddressException(String code) {
        super("address."+code);
    }
    
	protected AddressException(String code, HttpStatus status) {
        super("address."+code, status);
    }
	
	public static AddressException addressDetailUidEmpty() {
		return new AddressException("addressDetailUid.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static AddressException addressDetailThEmpty() {
		return new AddressException("addressDetailTh.empty", HttpStatus.BAD_REQUEST);
	}
	public static AddressException addressDetailEnEmpty() {
		return new AddressException("addressDetailEn.empty", HttpStatus.BAD_REQUEST);
	}
    
    public static AddressException addressNotFound() {
        return new AddressException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
    
    public static AddressException tambonNotFound() {
        return new AddressException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
    
    public static AddressException addressIsEmptyOrNull() {
        return new AddressException("address",HttpStatus.EXPECTATION_FAILED);
    }
    
	
}
