package com.example.demo.subject.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class SubjectException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected SubjectException(String code) {
        super("subject."+code);
    }
    
	protected SubjectException(String code, HttpStatus status) {
        super("subject."+code, status);
    }
	
	public static SubjectException subjectNotFound() {
        return new SubjectException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static SubjectException subjectIsEmptyOrNull() {
        return new SubjectException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static SubjectException subjectCodeEmpty() {
        return new SubjectException("subjectCode.empty",HttpStatus.EXPECTATION_FAILED);
    }
	public static SubjectException subjectNameEmpty() {
        return new SubjectException("subjectName.empty",HttpStatus.EXPECTATION_FAILED);
    }
	public static SubjectException subjectCreditEmpty() {
        return new SubjectException("subjectCredit.empty",HttpStatus.EXPECTATION_FAILED);
    }
	public static SubjectException subjectNameUsed() {
        return new SubjectException("subjectName.used",HttpStatus.EXPECTATION_FAILED);
    }
	public static SubjectException subjectCodeUsed() {
        return new SubjectException("subjectCode.used",HttpStatus.EXPECTATION_FAILED);
    }
	public static SubjectException subjectOvar() {
        return new SubjectException("subject.over",HttpStatus.EXPECTATION_FAILED);
    }
	
}
