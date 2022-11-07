//package com.example.demo.university.business;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.course.exception.CourseException;
//import com.example.demo.course.exception.CourseGroupException;
//import com.example.demo.course.exception.CourseProgramException;
//import com.example.demo.member.exception.MemberException;
//import com.example.demo.member.service.MemberService;
//import com.example.demo.university.exception.CalendarException;
//import com.example.demo.university.exception.CreditCriterionException;
//import com.example.demo.university.exception.CreditTotalException;
//import com.example.demo.university.exception.UniversityException;
//import com.example.demo.university.model.CreditTotal;
//import com.example.demo.university.payload.creditTotal.CreditTotalInsertPayload;
//import com.example.demo.university.payload.creditTotal.CreditTotalUpdatePayload;
//import com.example.demo.university.service.CreditCriterionService;
//import com.example.demo.university.service.CreditTotalService;
//
//import lombok.Setter;
//
//@Service
//@Setter
//public class CreditTotalBusiness {
//	
//	@Autowired
//	CreditTotalService creditTotalService;
//	
//	public void insertCreditTotal(CreditTotalInsertPayload body) throws CourseException, UniversityException, CourseProgramException, MemberException, CreditTotalException, CreditCriterionException {
//		
//		String memberUid = body.getMemberUid();
//		String total = body.getTotal();
//		String creditCriterionUid = body.getCreditCriterionUid();
//		
//		if(memberUid == null || memberUid.equals("")) {
//			throw CreditTotalException.creditTotalMemberUidEmpty();
//		}
//		
//		if(total == null || total.equals("")) {
//			throw CreditTotalException.creditTotalTotalEmpty();
//		}
//		
//		if(creditCriterionUid == null || creditCriterionUid.equals("")) {
//			throw CreditTotalException.creditCriterionEmpty();
//		}
//		
//		CreditTotal creditTotal = new CreditTotal();
//		
//		creditTotal.setMember(new MemberService().findMemberByUuid(memberUid));
//		creditTotal.setTotal(Integer.parseInt(total));
//		creditTotal.setCreditCriterion(new CreditCriterionService().findCreditCriterionByUuid(creditCriterionUid));
//
//		creditTotalService.saveCreditTotal(creditTotal);
//		
//	}
//	
//	public void updateCreditTotal(CreditTotalUpdatePayload body) throws MemberException, CalendarException, CourseException, CourseProgramException, CreditTotalException, CreditCriterionException {
//		
//		String memberUid = body.getMemberUid();
//		String total = body.getTotal();
//		String creditCriterionUid = body.getCreditCriterionUid();
//		
//		if(memberUid == null || memberUid.equals("")) {
//			throw CreditTotalException.creditTotalMemberUidEmpty();
//		}
//		
//		if(total == null || total.equals("")) {
//			throw CreditTotalException.creditTotalTotalEmpty();
//		}
//		
//		if(creditCriterionUid == null || creditCriterionUid.equals("")) {
//			throw CreditTotalException.creditCriterionEmpty();
//		}
//		
//		CreditTotal creditTotal = creditTotalService.findCreditTotalByUuid(body.getCreditTotalUid());
//		
//		creditTotal.setMember(new MemberService().findMemberByUuid(memberUid));
//		creditTotal.setTotal(Integer.parseInt(total));
//		creditTotal.setCreditCriterion(new CreditCriterionService().findCreditCriterionByUuid(creditCriterionUid));
//
//		creditTotalService.saveCreditTotal(creditTotal);
//		
//	}
//
//	public void deleteCreditTotal(String uuid) throws CalendarException, CourseException, CourseProgramException, CreditTotalException, CourseGroupException {
//		
//		if(uuid == null || uuid.isEmpty()) {
//			throw CreditTotalException.creditTotalIsEmptyOrNull();
//		}
//		
//		CreditTotal creditTotal = creditTotalService.findCreditTotalByUuid(uuid);
//		
//		creditTotalService.delCreditTotal(creditTotal);
//		
//	}
//
//}
