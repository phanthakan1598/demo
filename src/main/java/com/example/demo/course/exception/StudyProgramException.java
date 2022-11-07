package com.example.demo.course.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class StudyProgramException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected StudyProgramException(String code) {
        super("studyProgram."+code);
    }
    
	protected StudyProgramException(String code, HttpStatus status) {
        super("studyProgram."+code, status);
    }
	
	public static StudyProgramException studyProgramNotFound() {
        return new StudyProgramException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static StudyProgramException studyProgramIsEmptyOrNull() {
        return new StudyProgramException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static StudyProgramException studyProgramNameEmpty() {
        return new StudyProgramException("studyProgramNameEmpty",HttpStatus.EXPECTATION_FAILED);
    }
	public static StudyProgramException studyProgramUsed() {
        return new StudyProgramException("studyProgram.used",HttpStatus.EXPECTATION_FAILED);
    }
	public static StudyProgramException studyProgramNameUsed() {
        return new StudyProgramException("studyProgramName.used",HttpStatus.EXPECTATION_FAILED);
    }
}
