package com.example.demo.university.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.university.exception.RegistrationDateException;
import com.example.demo.university.model.RegistrationDate;
import com.example.demo.university.model.Term;
import com.example.demo.university.repository.RegistrationDateRepository;

import lombok.Setter;

@Service
@Setter
public class RegistrationDateService {
	@Autowired
	RegistrationDateRepository registrationDateRepository;
	
	public RegistrationDate findRegistrationDateByUuid(String uuid) throws RegistrationDateException {
        return registrationDateRepository.findOneByRegistrationDateUuid(uuid).orElseThrow(RegistrationDateException::registrationDateNotFound);
    }
	
	public RegistrationDate findRegistrationDateLevel(int LevClass, int range ,Term term) throws RegistrationDateException {
        return registrationDateRepository.findOneByRegistrationDateLevelClassAndRegistrationDateRangeAndTerm(LevClass,range,term)
        		.orElseThrow(RegistrationDateException::registrationDateNotFound);
    }
	
	public RegistrationDate saveRegistrationDate(RegistrationDate registrationDate) throws RegistrationDateException {
    	if(Objects.isNull(registrationDate)) {
    		throw RegistrationDateException.registrationDateIsEmptyOrNull();
    	}
		return registrationDateRepository.save(registrationDate);
	}
	
	public void delRegistrationDate(RegistrationDate registrationDate) throws RegistrationDateException {
    	if(Objects.isNull(registrationDate)) {
    		throw RegistrationDateException.registrationDateIsEmptyOrNull();
    	}
    	registrationDateRepository.delete(registrationDate);
	}
	
	public List<RegistrationDate> registrationDates(Term term){
		return registrationDateRepository.findByTerm(term);
		
	}
	
	public boolean timeBeginBoolean(int LevClass,Term term,LocalDate begin,LocalDate end){
        return registrationDateRepository.existsByRegistrationDateLevelClassAndTermAndBeginDateBetween(LevClass, term, begin, end);
    }
	
	public boolean timeEndBoolean(int LevClass,Term term,LocalDate begin,LocalDate end){
        return registrationDateRepository.existsByRegistrationDateLevelClassAndTermAndEndDateBetween(LevClass, term, begin, end);
    }
	
	public boolean registrationDateNameBoolean(String registrationDateName,Term term){
        return registrationDateRepository.existsByRegistrationDateNameAndTerm(registrationDateName,term);
    }
	
	public boolean registrationDateRangeAndTermBoolean(int LevClass, int range ,Term term) {
		return registrationDateRepository.existsByRegistrationDateLevelClassAndRegistrationDateRangeAndTerm(LevClass, range, term);
	}
	
	public boolean registrationDateTermBoolean(Term term,int registrationDateLevelClass,LocalDate deteNows, LocalDate deteNowe) {
		return registrationDateRepository.existsByTermAndRegistrationDateLevelClassAndBeginDateLessThanEqualAndEndDateGreaterThanEqual(term, registrationDateLevelClass, deteNows, deteNowe);
	}
	
}
