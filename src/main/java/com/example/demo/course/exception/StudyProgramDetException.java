package com.example.demo.course.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class StudyProgramDetException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected StudyProgramDetException(String code) {
        super("studyProgramDet."+code);
    }
    
	protected StudyProgramDetException(String code, HttpStatus status) {
        super("studyProgramDet."+code, status);
    }
	public static StudyProgramDetException studyProgramDetNotFound() {
        return new StudyProgramDetException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static StudyProgramDetException studyProgramDetIsEmptyOrNull() {
        return new StudyProgramDetException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	
	public static StudyProgramDetException studyProgramDetTermEmpty() {
        return new StudyProgramDetException("studyProgramDetTerm.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static StudyProgramDetException studyProgramDetLevelClassEmpty() {
        return new StudyProgramDetException("studyProgramDetLevelClass.empty",HttpStatus.BAD_REQUEST);
    }
	public static StudyProgramDetException studyProgramDetUsed() {
        return new StudyProgramDetException("studyProgramDet.used",HttpStatus.BAD_REQUEST);
    }
	public static StudyProgramDetException studyProgramDetIncorrect() {
        return new StudyProgramDetException("studyProgramDet.incorrect",HttpStatus.BAD_REQUEST);
    }
}
