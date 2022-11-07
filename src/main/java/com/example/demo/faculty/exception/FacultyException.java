package com.example.demo.faculty.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class FacultyException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected FacultyException(String code) {
        super("faculty."+code);
    }
    
	protected FacultyException(String code, HttpStatus status) {
        super("faculty."+code, status);
    }
	public static FacultyException facultyNameThEmpty() {
		return new FacultyException("facultyNameTh.empty", HttpStatus.BAD_REQUEST);
	}
	public static FacultyException facultyNameEnEmpty() {
		return new FacultyException("facultyNameEn.empty", HttpStatus.BAD_REQUEST);
	}
	public static FacultyException facultyCodeNameEmpty() {
		return new FacultyException("facultyCodeName.empty", HttpStatus.BAD_REQUEST);
	}
	public static FacultyException facultyNameThUsed() {
		return new FacultyException("facultyNameTh.used", HttpStatus.BAD_REQUEST);
	}
	public static FacultyException facultyNameEnUsed() {
		return new FacultyException("facultyNameEn.used", HttpStatus.BAD_REQUEST);
	}
	public static FacultyException facultyCodeNameUsed() {
		return new FacultyException("facultyCodeName.used", HttpStatus.BAD_REQUEST);
	}
	public static FacultyException facultyOver() {
		return new FacultyException("faculty.over", HttpStatus.BAD_REQUEST);
	}
	public static FacultyException facultyNotFound() {
        return new FacultyException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static FacultyException facultyStatusIsEmptyOrNull() {
        return new FacultyException("facultyStatus.emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static FacultyException facultyIsEmptyOrNull() {
        return new FacultyException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
