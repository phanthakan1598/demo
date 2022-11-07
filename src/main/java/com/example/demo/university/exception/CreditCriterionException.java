package com.example.demo.university.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class CreditCriterionException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected CreditCriterionException(String code) {
        super("creditCriterion."+code);
    }
    
	protected CreditCriterionException(String code, HttpStatus status) {
        super("creditCriterion."+code, status);
    }
	
	public static CreditCriterionException creditCriterionNotFound() {
        return new CreditCriterionException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static CreditCriterionException creditCriterionIsEmptyOrNull() {
        return new CreditCriterionException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	public static CreditCriterionException totalMaxEmpty() {
        return new CreditCriterionException("totalMax.empty",HttpStatus.BAD_REQUEST);
    }
	
	
	public static CreditCriterionException creditCriterionProgramUsed() {
        return new CreditCriterionException("creditCriterionProgram.used",HttpStatus.BAD_REQUEST);
    }
	public static CreditCriterionException creditCriterionNameEmpty() {
        return new CreditCriterionException("creditCriterionName.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static CreditCriterionException creditCriterionUuidEmpty() {
        return new CreditCriterionException("creditCriterionUuidEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static CreditCriterionException creditCriterionCreateAtEmpty() {
        return new CreditCriterionException("creditCriterionCreateAtEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static CreditCriterionException creditCriterionUpdatedAtEmpty() {
        return new CreditCriterionException("creditCriterionUpdatedAtEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static CreditCriterionException creditCriterionDegreeLevelUidEmpty() {
        return new CreditCriterionException("creditCriterionDegreeLevelUidEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static CreditCriterionException creditCriterionProgramUidEmpty() {
        return new CreditCriterionException("creditCriterionProgramUidEmpty.empty",HttpStatus.BAD_REQUEST);
    }
}
