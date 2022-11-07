package com.example.demo.register.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class StudyRegisterMemberException extends BaseException {
	
	private static final long serialVersionUID = -3371503320561323817L;

    protected StudyRegisterMemberException(String code) {
        super("studyRegisterMember."+code);
    }
    
	protected StudyRegisterMemberException(String code, HttpStatus status) {
        super("studyRegisterMember."+code, status);
    }
	
	public static StudyRegisterMemberException studyRegisterMemberNotFound() {
        return new StudyRegisterMemberException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static StudyRegisterMemberException studyRegisterMemberIsEmptyOrNull() {
        return new StudyRegisterMemberException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	
	public static StudyRegisterMemberException studyRegisterMemberUsed() throws StudyRegisterException {
		return new StudyRegisterMemberException("studyRegisterMember.used",HttpStatus.BAD_REQUEST);
    }
	
	public static StudyRegisterMemberException studyRegisterMemberUsed(String name)  {
		return new StudyRegisterMemberException("studyRegisterMember."+name+".used",HttpStatus.BAD_REQUEST);
    }
	
	public static StudyRegisterMemberException teacherTimeUsed(String name)  {
	    return new StudyRegisterMemberException("teacherTime."+name+".used",HttpStatus.BAD_REQUEST);
	}
	
}
