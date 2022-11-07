package com.example.demo.member.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class MemberDegreeLevelException extends BaseException {
	
	private static final long serialVersionUID = -3371503320561323817L;

    protected MemberDegreeLevelException(String code) {
        super("memberDegreeLevel."+code);
    }
    
	protected MemberDegreeLevelException(String code, HttpStatus status) {
        super("memberDegreeLevel."+code, status);
    }
    public static MemberDegreeLevelException memberDegreeLevelIsEmptyOrNull() {
        return new MemberDegreeLevelException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
    
    public static MemberDegreeLevelException memberDegreeLevelNotFound() {
        return new MemberDegreeLevelException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
}
