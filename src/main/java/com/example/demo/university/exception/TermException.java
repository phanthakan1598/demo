package com.example.demo.university.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class TermException extends BaseException {
	
	private static final long serialVersionUID = -3371503320561323817L;

    protected TermException(String code) {
        super("term."+code);
    }
    
	protected TermException(String code, HttpStatus status) {
        super("term."+code, status);
    }
	
//	public static TermException degreeLevelNameEmpty() {
//		return new TermException("degreeLevelName.empty", HttpStatus.BAD_REQUEST);
//	}
	
//	public static TermException degreeLevelUuidEmpty() {
//		return new TermException("degreeLevelUuid.empty", HttpStatus.BAD_REQUEST);
//	}
//	
	public static TermException termTimeUsed() {
	    return new TermException("termTime.uesd",HttpStatus.EXPECTATION_FAILED);
	}
	public static TermException termUsed() {
	    return new TermException("term.uesd",HttpStatus.EXPECTATION_FAILED);
	}
	public static TermException termIncorrect() {
	    return new TermException("term.incorrect",HttpStatus.EXPECTATION_FAILED);
	}
	public static TermException termEmpty() {
	    return new TermException("term.empty",HttpStatus.EXPECTATION_FAILED);
	}
	public static TermException beginDateEmpty() {
	    return new TermException("beginDate.empty",HttpStatus.EXPECTATION_FAILED);
	}
	public static TermException endDateEmpty() {
	    return new TermException("endDate.empty",HttpStatus.EXPECTATION_FAILED);
	}
	public static TermException yearEmpty() {
	    return new TermException("year.empty",HttpStatus.EXPECTATION_FAILED);
	}
	public static TermException termNotFound() {
        return new TermException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static TermException termEmptyOrNull() {
        return new TermException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }

}
