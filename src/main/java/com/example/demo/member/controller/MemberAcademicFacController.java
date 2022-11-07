package com.example.demo.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.member.business.MemberAcademicFacBusiness;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.teacher.MemberTeacherAllJson;

@RestController
@RequestMapping("/member/academicFac")
public class MemberAcademicFacController {
	
	@Autowired
	MemberAcademicFacBusiness memberAcademicFacBusiness;
	
	@GetMapping(value = "/viewAllTeacherFaculty")
	public ResponseEntity<List<MemberTeacherAllJson>> viewAllTeacherFaculty() throws MemberException{
		return ResponseEntity.ok(memberAcademicFacBusiness.viewAllTeacherFaculty());
	}
	
	
}
