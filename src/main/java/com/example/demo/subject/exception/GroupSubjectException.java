package com.example.demo.subject.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class GroupSubjectException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected GroupSubjectException(String code) {
        super("groupSubject."+code);
    }
    
	protected GroupSubjectException(String code, HttpStatus status) {
        super("groupSubject."+code, status);
    }
	
	public static GroupSubjectException groupSubjectUidEmpty() {
		return new GroupSubjectException("groupSubjectUid.empty", HttpStatus.BAD_REQUEST);
	}
	public static GroupSubjectException groupSubjectNameEmpty() {
		return new GroupSubjectException("groupSubjectName.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static GroupSubjectException groupSubjectTypeEmpty() {
		return new GroupSubjectException("groupSubjectType.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static GroupSubjectException groupSubjectNameUsed() {
		return new GroupSubjectException("groupSubjectName.used", HttpStatus.BAD_REQUEST);
	}
	public static GroupSubjectException groupSubjectCourseUidEmpty() {
		return new GroupSubjectException("groupSubjectCourseUid.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static GroupSubjectException groupSubjectNotFound() {
        return new GroupSubjectException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static GroupSubjectException groupSubjectIsEmptyOrNull() {
        return new GroupSubjectException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
