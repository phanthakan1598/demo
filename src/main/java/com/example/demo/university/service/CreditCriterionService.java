//package com.example.demo.university.service;
//
//import java.util.List;
//import java.util.Objects;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.faculty.model.Faculty;
//import com.example.demo.faculty.model.Program;
//import com.example.demo.university.exception.CreditCriterionException;
//import com.example.demo.university.model.CreditCriterion;
//import com.example.demo.university.model.DegreeLevel;
//import com.example.demo.university.repository.CreditCriterionRepository;
//
//import lombok.Setter;
//
//@Service
//@Setter
//public class CreditCriterionService {
//	@Autowired
//	CreditCriterionRepository creditCriterionRepository;
//	
//	public CreditCriterion findCreditCriterionByUuid(String uuid) throws CreditCriterionException {
//        return creditCriterionRepository.findOneByCreditCriterionUuid(uuid).orElseThrow(CreditCriterionException::creditCriterionNotFound);
//    }
//	
//	public CreditCriterion saveCreditCriterion(CreditCriterion creditCriterion) throws CreditCriterionException {
//    	if(Objects.isNull(creditCriterion)) {
//    		throw CreditCriterionException.creditCriterionIsEmptyOrNull();
//    	}
//		return creditCriterionRepository.save(creditCriterion);
//	}
//	
//	public void delCreditCriterion(CreditCriterion creditCriterion) throws CreditCriterionException {
//    	if(Objects.isNull(creditCriterion)) {
//    		throw CreditCriterionException.creditCriterionIsEmptyOrNull();
//    	}
//    	creditCriterionRepository.delete(creditCriterion);
//	}
//	
//	public boolean creditCriterionsProgramBoolean(Program program) {
//		return creditCriterionRepository.existsByProgram(program);
//	}
//	
//	public List<CreditCriterion> listCreditCriterions(Faculty faculty,DegreeLevel degreeLevel){
//		return creditCriterionRepository.findByProgramFacultyAndProgramDegreeLevelOrderByUpdatedAtDesc(faculty, degreeLevel);
//	}
//}
