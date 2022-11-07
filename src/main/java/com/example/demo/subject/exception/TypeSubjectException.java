package com.example.demo.subject.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class TypeSubjectException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected TypeSubjectException(String code) {
        super("typeSubject."+code);
    }
    
	protected TypeSubjectException(String code, HttpStatus status) {
        super("typeSubject."+code, status);
    }
	
	public static TypeSubjectException typeSubjectNotFound() {
        return new TypeSubjectException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
}
