package com.example.demo.university.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class UniversityException extends BaseException {
	
	private static final long serialVersionUID = -3371503320561323817L;

    protected UniversityException(String code) {
        super("university."+code);
    }
    
	protected UniversityException(String code, HttpStatus status) {
        super("university."+code, status);
    }
	
	public static UniversityException universityCodeNameEmpty() {
		return new UniversityException("universityCodeName.empty", HttpStatus.BAD_REQUEST);
	}
	public static UniversityException universityNameThEmpty() {
		return new UniversityException("universityNameTh.empty", HttpStatus.BAD_REQUEST);
	}
	public static UniversityException universityNameEnEmpty() {
		return new UniversityException("universityNameEn.empty", HttpStatus.BAD_REQUEST);
	}
	public static UniversityException universityAddressEmpty() {
		return new UniversityException("universityAddress.empty", HttpStatus.BAD_REQUEST);
	}
	public static UniversityException tambonUuidEmpty() {
		return new UniversityException("tambonUuid.empty", HttpStatus.BAD_REQUEST);
	}
	public static UniversityException universityNameThUsed() {
		return new UniversityException("universityNameTh.used", HttpStatus.BAD_REQUEST);
	}
	public static UniversityException universityNameEnUsed() {
		return new UniversityException("universityNameEn.used", HttpStatus.BAD_REQUEST);
	}
	public static UniversityException universityCodeNameUsed() {
		return new UniversityException("universityCodeName.used", HttpStatus.BAD_REQUEST);
	}
	public static UniversityException universityNotFound() {
        return new UniversityException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static UniversityException universityIsEmptyOrNull() {
        return new UniversityException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static UniversityException universityOver() {
		return new UniversityException("university.over", HttpStatus.BAD_REQUEST);
	}
	public static UniversityException ForbiddenError() {
        return new UniversityException("forbiddenError",HttpStatus.EXPECTATION_FAILED);
    }

	
}
