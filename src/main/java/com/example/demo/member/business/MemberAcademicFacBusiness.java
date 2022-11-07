package com.example.demo.member.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.teacher.MemberTeacherAllJson;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.util.JWTAuth;

import lombok.Setter;

@Service
@Setter
public class MemberAcademicFacBusiness {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	public List<MemberTeacherAllJson> viewAllTeacherFaculty() throws MemberException{
		Faculty faculty = memberService.findMemberFacultyMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid()).getFaculty();
		return  MemberTeacherAllJson.packMemberTeacherAllJson(memberService.getAllTeacherFaculty(faculty));
	}
	
}
