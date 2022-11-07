package com.example.demo.university.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.university.exception.CreditOfTermException;
import com.example.demo.university.exception.CreditTotalException;
import com.example.demo.university.model.CreditOfTerm;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.repository.CreditOfTermRepository;

import lombok.Setter;

@Service
@Setter
public class CreditOfTermService {
	@Autowired
	CreditOfTermRepository creditOfTermRepository;
	
	public CreditOfTerm findCreditOfTermByUuid(String uuid) throws CreditOfTermException {
        return creditOfTermRepository.findOneByCreditOfTermUuid(uuid).orElseThrow(CreditOfTermException::creditOfTermNotFound);
    }
	
	public CreditOfTerm findCreditOfTermProgram(Program program) throws CreditOfTermException {
        return creditOfTermRepository.findOneByProgram(program).orElseThrow(CreditOfTermException::creditOfTermNotFound);
    }
	
	public CreditOfTerm saveCreditOfTerm(CreditOfTerm creditOfTerm) throws CreditOfTermException {
    	if(Objects.isNull(creditOfTerm)) {
    		throw CreditOfTermException.creditOfTermIsEmptyOrNull();
    	}
		return creditOfTermRepository.save(creditOfTerm);
	}
	
	public void delCreditOfTerm(CreditOfTerm creditOfTerm) throws CreditTotalException, CreditOfTermException {
    	if(Objects.isNull(creditOfTerm)) {
    		throw CreditOfTermException.creditOfTermNotFound();
    	}
    	creditOfTermRepository.delete(creditOfTerm);
	}
	
	public Boolean booleanCreditOfTermProgram(Program program) {
		return creditOfTermRepository.existsByProgram(program);
	}
	
	public List<CreditOfTerm> listCreditOfTerms(Faculty faculty,DegreeLevel degreeLevel){
		return creditOfTermRepository.findByProgramFacultyAndProgramDegreeLevel(faculty, degreeLevel);
	}
	
	public List<CreditOfTerm> listCreditOfTermsProgram(Program program){
		return creditOfTermRepository.findByProgram(program);
	}
}
