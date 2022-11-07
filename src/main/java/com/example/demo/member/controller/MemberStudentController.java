package com.example.demo.member.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.member.business.MemberStudentBusiness;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.student.TermClassLevelAllJson;
import com.example.demo.register.json.StudyRegisterStudentSubjectAllJson;
import com.example.demo.university.exception.TermException;

@RestController
@RequestMapping("/member/student")
public class MemberStudentController {
	
	@Autowired
	MemberStudentBusiness memberStudentBusiness;
	
	@GetMapping(value = "/viewAllTermClassLevel/{date}")
	public ResponseEntity<TermClassLevelAllJson> viewAllTermClassLevel(@PathVariable String date) throws MemberException, TermException, ParseException{
		return ResponseEntity.ok(memberStudentBusiness.viewAllTermClassLevel(date));
	}
	
	@GetMapping(value = "/viewSubjectTerm/{termUid}")
	public ResponseEntity <List<StudyRegisterStudentSubjectAllJson>> viewSubjectTerm(@PathVariable String termUid) throws MemberException, TermException {
		return ResponseEntity.ok(memberStudentBusiness.viewSubjectTerm(termUid));
	}
}
