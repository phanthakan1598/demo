package com.example.demo.register.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class StudyRegisterException extends BaseException {
	

	
	private static final long serialVersionUID = -3371503320561323817L;

    protected StudyRegisterException(String code) {
        super("studyRegister."+code);
    }
    
	protected StudyRegisterException(String code, HttpStatus status) {
        super("studyRegister."+code, status);
    }
	
	public static StudyRegisterException studyRegisterNotFound() {
        return new StudyRegisterException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static StudyRegisterException capacityTotalOver() {
        return new StudyRegisterException("capacityTotal.over",HttpStatus.BAD_REQUEST);
    }
	
	public static StudyRegisterException capacitySubjectTotalOver(String subject) {
        return new StudyRegisterException(subject+".capacityTotal.over",HttpStatus.BAD_REQUEST);
    }
	
	public static StudyRegisterException studyRegisterIsEmptyOrNull() {
        return new StudyRegisterException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static StudyRegisterException capacityMaxEmpty() {
        return new StudyRegisterException("capacityMax.empty",HttpStatus.BAD_REQUEST);
    }
	
	
	public static StudyRegisterException studyRegisterIncorrect() {
		return new StudyRegisterException("studyRegister.incorrect", HttpStatus.BAD_REQUEST);
	}
	
	public static StudyRegisterException studyRegisterTermNotFound() {
		return new StudyRegisterException("studyRegisterTerm.notFound", HttpStatus.BAD_REQUEST);
	}
	
	public static StudyRegisterException timeIncorrect() {
	    return new StudyRegisterException("time.incorrect",HttpStatus.BAD_REQUEST);
	}
	
	public static StudyRegisterException capacityIncorrect() {
	    return new StudyRegisterException("capacity.incorrect",HttpStatus.BAD_REQUEST);
	}
	
	public static StudyRegisterException timeUsed() {
	    return new StudyRegisterException("time.used",HttpStatus.BAD_REQUEST);
	}
	
	public static StudyRegisterException teacherTimeUsed() throws StudyRegisterException {
	    return new StudyRegisterException("teacherTime.used",HttpStatus.BAD_REQUEST);
	}
	
	public static StudyRegisterException subjectStudyProgramDetNotFound() {
	    return new StudyRegisterException("subjectStudyProgramDet.notFound",HttpStatus.BAD_REQUEST);
	}
	
	
}
