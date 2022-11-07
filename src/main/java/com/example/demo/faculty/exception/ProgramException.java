package com.example.demo.faculty.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class ProgramException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected ProgramException(String code) {
        super("program."+code);
    }
    
	protected ProgramException(String code, HttpStatus status) {
        super("program."+code, status);
    }
	public static ProgramException programNameThEmpty() {
		return new ProgramException("programNameTh.empty", HttpStatus.BAD_REQUEST);
	}
	public static ProgramException programNameEnEmpty() {
		return new ProgramException("programNameEn.empty", HttpStatus.BAD_REQUEST);
	}
	public static ProgramException programCodeNameEmpty() {
		return new ProgramException("programCodeName.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static ProgramException programNameThUsed() {
		return new ProgramException("programNameTh.used", HttpStatus.BAD_REQUEST);
	}
	public static ProgramException programNameEnUsed() {
		return new ProgramException("programNameEn.used", HttpStatus.BAD_REQUEST);
	}
	public static ProgramException programCodeNameUsed() {
		return new ProgramException("programCodeName.used", HttpStatus.BAD_REQUEST);
	}
	
	public static ProgramException programNotFound() {
        return new ProgramException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static ProgramException programStatusIsEmptyOrNull() {
        return new ProgramException("programStatus.emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static ProgramException programIsEmptyOrNull() {
        return new ProgramException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
