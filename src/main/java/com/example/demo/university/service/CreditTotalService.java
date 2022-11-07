//package com.example.demo.university.service;
//
//import java.util.Objects;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.course.exception.CourseException;
//import com.example.demo.course.exception.CourseGroupException;
//import com.example.demo.course.exception.CourseProgramException;
//import com.example.demo.university.exception.CreditTotalException;
//import com.example.demo.university.model.CreditTotal;
//import com.example.demo.university.repository.CreditTotalRepository;
//
//import lombok.Setter;
//
//@Service
//@Setter
//public class CreditTotalService {
//	@Autowired
//	CreditTotalRepository creditTotalRepository;
//	
//	public CreditTotal findCreditTotalByUuid(String uuid) throws CreditTotalException {
//        return creditTotalRepository.findOneByCreditTotalUuid(uuid).orElseThrow(CreditTotalException::creditTotalNotFound);
//    }
//	
//	public CreditTotal saveCreditTotal(CreditTotal creditTotal) throws CreditTotalException {
//    	if(Objects.isNull(creditTotal)) {
//    		throw CreditTotalException.creditTotalIsEmptyOrNull();
//    	}
//		return creditTotalRepository.save(creditTotal);
//	}
//	
//	public void delCreditTotal(CreditTotal creditTotal) throws CourseGroupException, CourseException, CourseProgramException, CreditTotalException {
//    	if(Objects.isNull(creditTotal)) {
//    		throw CreditTotalException.creditTotalNotFound();
//    	}
//    	creditTotalRepository.delete(creditTotal);
//	}
//}
