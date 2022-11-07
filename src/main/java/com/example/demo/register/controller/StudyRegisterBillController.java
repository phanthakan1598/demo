package com.example.demo.register.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bill.exception.BillProgramException;
import com.example.demo.bill.exception.CreditPriceException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.register.business.StudyRegisterBillBusiness;
import com.example.demo.register.json.StudyRegisterBillAllJson;
import com.example.demo.university.exception.TermException;

@RestController
@RequestMapping("/studyRegister/bill")
public class StudyRegisterBillController {
	@Autowired
	StudyRegisterBillBusiness studyRegisterBillBusiness;
	
	@GetMapping(value = "/viewStudyRegisterBill/{termUid}")
	public ResponseEntity <List<StudyRegisterBillAllJson>> viewStudyRegisterBill(@PathVariable String termUid) throws MemberException, TermException, CreditPriceException, BillProgramException{
		return ResponseEntity.ok(studyRegisterBillBusiness.viewStudyRegisterBill(termUid));
	}
}
