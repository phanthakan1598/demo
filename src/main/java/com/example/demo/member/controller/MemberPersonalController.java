package com.example.demo.member.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.address.exception.AddressException;
import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.member.business.MemberPersonalBusiness;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.academicFac.MemberAcademicFacAllJson;
import com.example.demo.member.json.academicFac.MemberAcademicFacJson;
import com.example.demo.member.json.academicGe.MemberAcademicGeAllJson;
import com.example.demo.member.json.academicGe.MemberAcademicGeJson;
import com.example.demo.member.json.course.MemberCourseAllJson;
import com.example.demo.member.json.course.MemberCourseJson;
import com.example.demo.member.json.student.MemberStudentAllJson;
import com.example.demo.member.json.student.MemberStudentJson;
import com.example.demo.member.json.teacher.MemberTeacherAllJson;
import com.example.demo.member.json.teacher.MemberTeacherJson;
import com.example.demo.member.payload.memberAcademicFac.MemberAcademicFacInsertPayload;
import com.example.demo.member.payload.memberAcademicFac.MemberAcademicFacUpdatePayload;
import com.example.demo.member.payload.memberAcademicGe.MemberAcademicGeInsertPayload;
import com.example.demo.member.payload.memberAcademicGe.MemberAcademicGeUpdatePayload;
import com.example.demo.member.payload.memberCourse.MemberCourseInsertPayload;
import com.example.demo.member.payload.memberCourse.MemberCourseUpdatePayload;
import com.example.demo.member.payload.memberStudent.MemberStudentCsvInsertPayload;
import com.example.demo.member.payload.memberStudent.MemberStudentInsertPayload;
import com.example.demo.member.payload.memberStudent.MemberStudentUpdatePayload;
import com.example.demo.member.payload.memberTeacher.MemberTeacherInsertPayload;
import com.example.demo.member.payload.memberTeacher.MemberTeacherUpdatePayload;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.exception.UniversityException;

@RestController
@RequestMapping("/member/personal")
public class MemberPersonalController {
	
	@Autowired
	MemberPersonalBusiness memberPersonalBusiness;
	
	@PostMapping(value = "/insertMemberStudentCsv")
	public ResponseEntity<Void> insertMemberStudentCsv(@ModelAttribute MemberStudentCsvInsertPayload body) throws IOException, UniversityException, AddressException, MemberException, DegreeLevelException, FacultyException, ProgramException, LogException{
		memberPersonalBusiness.insertMemberStudentCsv(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/viewAllMemberCourse")
	public ResponseEntity<List<MemberCourseAllJson>> viewAllMemberPersonal(){
		return ResponseEntity.ok(memberPersonalBusiness.viewAllMemberCourse());
	}
	
	@GetMapping(value = "/viewMemberCourse/{uid}")
	public ResponseEntity<MemberCourseJson> viewMemberCourse(@PathVariable String uid) throws MemberException{
		return ResponseEntity.ok(memberPersonalBusiness.viewMemberCourse(uid));
	}
	
	@PostMapping(value = "/insertMemberCourse")
	public ResponseEntity<Void> insertMemberCourse(@RequestBody MemberCourseInsertPayload body) throws MemberException, AddressException{
		memberPersonalBusiness.insertMemberCourse(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateMemberCourse")
	public ResponseEntity<Void> updateMemberCourse(@RequestBody MemberCourseUpdatePayload body) throws MemberException, AddressException{
		memberPersonalBusiness.updateMemberCourse(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/deleteMemberCourse/{uid}")
	public ResponseEntity<Void> deleteMemberCourse(@PathVariable String uid) throws MemberException, LogException{
		memberPersonalBusiness.deleteMemberCourse(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
//--------------------------------------------------------------------------------------------------------------------
	
	@GetMapping(value = "/viewAllMemberAcademicGe")
	public ResponseEntity<List<MemberAcademicGeAllJson>> viewAllMemberAcademicGe(){
		return ResponseEntity.ok(memberPersonalBusiness.viewAllMemberAcademicGe());
	}
	
	@GetMapping(value = "/viewMemberAcademicGe/{uid}")
	public ResponseEntity<MemberAcademicGeJson> viewMemberAcademicGe(@PathVariable String uid) throws MemberException{
		return ResponseEntity.ok(memberPersonalBusiness.viewMemberAcademicGe(uid));
	}
	
	@PostMapping(value = "/insertMemberAcademicGe")
	public ResponseEntity<Void> insertMemberAcademicGe(@RequestBody MemberAcademicGeInsertPayload body) throws MemberException, AddressException{
		memberPersonalBusiness.insertMemberAcademicGe(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateMemberAcademicGe")
	public ResponseEntity<Void> updateMemberAcademicGe(@RequestBody MemberAcademicGeUpdatePayload body) throws MemberException, AddressException{
		memberPersonalBusiness.updateMemberAcademicGe(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/deleteMemberAcademicGe/{uid}")
	public ResponseEntity<Void> deleteMemberAcademicGe(@PathVariable String uid) throws MemberException, LogException{
		memberPersonalBusiness.deleteMemberAcademicGe(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
//--------------------------------------------------------------------------------------------------------------------

	@GetMapping(value = "/viewAllMemberAcademicFac")
	public ResponseEntity<List<MemberAcademicFacAllJson>> viewAllMemberAcademicFac(){
		return ResponseEntity.ok(memberPersonalBusiness.viewAllMemberAcademicFac());
	}
	
	@GetMapping(value = "/viewMemberAcademicFac/{uid}")
	public ResponseEntity<MemberAcademicFacJson> viewMemberAcademicFac(@PathVariable String uid) throws MemberException{
		return ResponseEntity.ok(memberPersonalBusiness.viewMemberAcademicFac(uid));
	}
	
	@PostMapping(value = "/insertMemberAcademicFac")
	public ResponseEntity<Void> insertMemberAcademicFac(@RequestBody MemberAcademicFacInsertPayload body) throws MemberException, AddressException, FacultyException{
		memberPersonalBusiness.insertMemberAcademicFac(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateMemberAcademicFac")
	public ResponseEntity<Void> updateMemberAcademicFac(@RequestBody MemberAcademicFacUpdatePayload body) throws MemberException, AddressException, FacultyException{
		memberPersonalBusiness.updateMemberAcademicFac(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/deleteMemberAcademicFac/{uid}")
	public ResponseEntity<Void> deleteMemberAcademicFac(@PathVariable String uid) throws MemberException, LogException{
		memberPersonalBusiness.deleteMemberAcademicFac(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

//--------------------------------------------------------------------------------------------------------------------

	@GetMapping(value = "/viewAllMemberTeacher")
	public ResponseEntity<List<MemberTeacherAllJson>> viewAllMemberTeacher(){
		return ResponseEntity.ok(memberPersonalBusiness.viewAllMemberTeacher());
	}	
	
	@GetMapping(value = "/viewMemberTeacher/{uid}")
	public ResponseEntity<MemberTeacherJson> viewMemberTeacher(@PathVariable String uid) throws MemberException{
		return ResponseEntity.ok(memberPersonalBusiness.viewMemberTeacher(uid));
	}
	
	@PostMapping(value = "/insertMemberTeacher")
	public ResponseEntity<Void> insertMemberTeacher(@RequestBody MemberTeacherInsertPayload body) throws MemberException, AddressException, FacultyException, ProgramException{
		memberPersonalBusiness.insertMemberTeacher(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateMemberTeacher")
	public ResponseEntity<Void> updateMemberTeacher(@RequestBody MemberTeacherUpdatePayload body) throws MemberException, AddressException, FacultyException, ProgramException{
		memberPersonalBusiness.updateMemberTeacher(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/deleteMemberTeacher/{uid}")
	public ResponseEntity<Void> deleteMemberTeacher(@PathVariable String uid) throws MemberException, LogException{
		memberPersonalBusiness.deleteMemberTeacher(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
//--------------------------------------------------------------------------------------------------------------------

	@GetMapping(value = "/viewAllMemberStudent")
	public ResponseEntity<List<MemberStudentAllJson>> viewAllMemberStudent(){
		return ResponseEntity.ok(memberPersonalBusiness.viewAllMemberStudent());
	}	
	
	@GetMapping(value = "/viewMemberStudent/{uid}")
	public ResponseEntity<MemberStudentJson> viewMemberStudent(@PathVariable String uid) throws MemberException{
		return ResponseEntity.ok(memberPersonalBusiness.viewMemberStudent(uid));
	}
	
	@PostMapping(value = "/insertMemberStudent")
	public ResponseEntity<Void> insertMemberStudent(@RequestBody MemberStudentInsertPayload body) throws MemberException, AddressException, FacultyException, ProgramException{
		memberPersonalBusiness.insertMemberStudent(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateMemberStudent")
	public ResponseEntity<Void> updateMemberStudent(@RequestBody MemberStudentUpdatePayload body) throws MemberException, AddressException, FacultyException, ProgramException{
		memberPersonalBusiness.updateMemberStudent(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/deleteMemberStudent/{uid}")
	public ResponseEntity<Void> deleteMemberStudent(@PathVariable String uid) throws MemberException, LogException{
		memberPersonalBusiness.deleteMemberStudent(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
