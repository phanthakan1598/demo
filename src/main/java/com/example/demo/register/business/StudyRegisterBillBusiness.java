package com.example.demo.register.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.exception.BillProgramException;
import com.example.demo.bill.exception.CreditPriceException;
import com.example.demo.bill.model.Bill;
import com.example.demo.bill.model.BillDet;
import com.example.demo.bill.service.BillDetService;
import com.example.demo.bill.service.BillProgramService;
import com.example.demo.bill.service.CreditPriceService;
import com.example.demo.faculty.model.Program;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.service.MemberService;
import com.example.demo.register.json.StudyRegisterBillAllJson;
import com.example.demo.register.model.EnrollRegister;
import com.example.demo.register.service.EnrollRegisterService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.exception.TermException;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.Term;
import com.example.demo.university.service.TermService;

import lombok.Setter;

@Service
@Setter
public class StudyRegisterBillBusiness {
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BillProgramService billProgramService;
	
	@Autowired
	TermService termService;
	
	@Autowired
	EnrollRegisterService enrollRegisterService;
	
	@Autowired
	CreditPriceService creditPriceService;
	
	@Autowired
	BillDetService billDetService;
	
	public List<StudyRegisterBillAllJson> viewStudyRegisterBill(String termUid) throws MemberException, TermException, CreditPriceException, BillProgramException {
		Term term = termService.findTermByUuid(termUid);
		Program program = memberService.findMemberFacultyMemberByUuid(jwtAuth.getCurrentUserUuid()).getProgram();
		DegreeLevel degreeLevel = program.getDegreeLevel();
		float creditPrice = creditPriceService.findCreditPriceByDegree(degreeLevel).getCreditPrice();
		Bill bill = billProgramService.findProgram(program).getBill();
		List<BillDet> billDets = billDetService.billDets(bill);
//		List <BillDet> billDets = new ArrayList<>();
//		List <Term> terms = termService.listTerms(degreeLevel);
		List <EnrollRegister> enrollRegisters = enrollRegisterService.enrollRegistersTable(jwtAuth.getCurrentMember(),term);
		
		int CreditTerm = 0;
		
		for (EnrollRegister enrollRegister : enrollRegisters) {
			if(enrollRegister.getStudyRegister().getTerm().equals(term)) {
				CreditTerm = CreditTerm + enrollRegister.getStudyRegister().getSubject().getCredit();
				
			}
		}
		BillDet billDet = new BillDet();
		billDet.setBill(bill);
		billDet.setName("ค่าหน่วยกิต ( " +creditPrice+" X "+CreditTerm+" )");
		creditPrice = creditPrice*CreditTerm;
		billDet.setPrice(creditPrice);
		
		billDets.add(billDet);
		
		
		
//		BillProgram billProgram = 
		
		
		return StudyRegisterBillAllJson.studyRegisterBillAllJsons(billDets);
	}
}
