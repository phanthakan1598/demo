package com.example.demo.university.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.university.exception.TermException;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.Term;
import com.example.demo.university.model.University;
import com.example.demo.university.repository.TermRepository;

import lombok.Setter;

@Service
@Setter
public class TermService {
	
	@Autowired
	TermRepository termRepository;
	
	public Term findTermByUuid(String uuid) throws TermException {
        return termRepository.findOneByTermUuid(uuid).orElseThrow(TermException::termNotFound);
    }
	
	public Term findTermCheck(DegreeLevel degreeLevel,int year,int term) throws TermException {
        return termRepository.findOneByDegreeLevelAndYearAndTerm(degreeLevel,year,term).orElseThrow(TermException::termNotFound);
    }
	
	public Term findTermStudent(DegreeLevel degreeLevel,LocalDate deteNows, LocalDate deteNowe) throws TermException {
        return termRepository.findOneByDegreeLevelAndBeginDateLessThanEqualAndEndDateGreaterThanEqual(degreeLevel, deteNows, deteNowe).orElseThrow(TermException::termNotFound);
    }
	
	public Boolean booleanTermStudent(DegreeLevel degreeLevel,LocalDate deteNows, LocalDate deteNowe) {
		return termRepository.existsByDegreeLevelAndBeginDateLessThanEqualAndEndDateGreaterThanEqual(degreeLevel, deteNows, deteNowe);
	}
	
//	public Term findTermStudent(DegreeLevel degreeLevel,LocalDate deteNows, LocalDate deteNowe) throws TermException {
//      return termRepository.findOneByDegreeLevelAndBeginDateBeforeAndEndDateAfter(degreeLevel, deteNows, deteNowe).orElseThrow(TermException::termNotFound);
//  }
//	public Term findTermStudent(DegreeLevel degreeLevel,LocalDate deteNows) throws TermException {
//        return termRepository.findOneByDegreeLevelAndBeginDateBefore(degreeLevel, deteNows).orElseThrow(TermException::termNotFound);
//    }
	
//	public Term findTermStudent(DegreeLevel degreeLevel,LocalDate deteNows) throws TermException {
//        return termRepository.findOneByDegreeLevelAndBeginDateGreaterThanEqual(degreeLevel, deteNows).orElseThrow(TermException::termNotFound);
//    }
	
	public Term saveTerm(Term term) throws TermException {
    	if(Objects.isNull(term)) {
    		throw TermException.termEmptyOrNull();
    	}
		return termRepository.save(term);
	}
	
	public void delTerm(Term term) throws TermException {
    	if(Objects.isNull(term)) {
    		throw TermException.termEmptyOrNull();
    	}
    	termRepository.delete(term);
	}
	
	public Boolean booleanTeam(DegreeLevel degreeLevel , int year , int term) {
		return termRepository.existsByDegreeLevelAndYearAndTerm(degreeLevel,year,term);
	}
	
	public Boolean booleanTeamTimeUsed(DegreeLevel degreeLevel,int year,LocalDate begin,LocalDate end,LocalDate begin2,LocalDate end2) {
		return termRepository.existsByDegreeLevelAndYearAndBeginDateBetweenOrEndDateBetween(degreeLevel,year,begin,end,begin2,end2);
	}
	
	public Boolean booleanTeamTimeBeginUsed(DegreeLevel degreeLevel,int year,LocalDate begin,LocalDate end) {
		return termRepository.existsByDegreeLevelAndYearAndBeginDateBetween(degreeLevel,year,begin,end);
	}
	
	public Boolean booleanTeamTimeEndDateUsed(DegreeLevel degreeLevel,int year,LocalDate begin,LocalDate end) {
		return termRepository.existsByDegreeLevelAndYearAndEndDateBetween(degreeLevel,year,begin,end);
	}
	
	public Boolean booleanTeamTimeBeginUsedUpdate(long termId, DegreeLevel degreeLevel,int year,LocalDate begin,LocalDate end) {
		return termRepository.existsByTermIdNotAndDegreeLevelAndYearAndBeginDateBetween(termId, degreeLevel,year,begin,end);
	}
	
	public Boolean booleanTeamTimeEndDateUsedUpdate(long termId, DegreeLevel degreeLevel,int year,LocalDate begin,LocalDate end) {
		return termRepository.existsByTermIdNotAndDegreeLevelAndYearAndEndDateBetween(termId, degreeLevel,year,begin,end);
	}
	
	public List<Term> listTerms (DegreeLevel degreeLevel){
		return termRepository.findByDegreeLevelOrderByYearDescTermAsc(degreeLevel);
	}
	
	
	public List<Term> listTermDegree (DegreeLevel degreeLevel,int year){
		return termRepository.findByDegreeLevelAndYearOrderByTermAsc(degreeLevel,year);
	}
	
	public List<Term> listTermUniversity (University university,int year, int term){
		return termRepository.findByDegreeLevelUniversityAndYearAndTerm(university, year, term);
	}
	
//	
//	public List<Term> listTerms (University university,int yearNow,int yearNext,int yearNot){
//		return termRepository.findByUniversityAndYearBetweenAndYearNotOrderByTermAsc(university,yearNow,yearNext,yearNot);
//	}
}
