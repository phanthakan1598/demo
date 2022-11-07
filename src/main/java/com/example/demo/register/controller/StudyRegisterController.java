package com.example.demo.register.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bill.exception.BillProgramException;
import com.example.demo.building.exception.RoomException;
import com.example.demo.course.exception.StudyProgramDetException;
import com.example.demo.course.exception.StudyProgramException;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.register.business.StudyRegisterBusiness;
import com.example.demo.register.exception.DayException;
import com.example.demo.register.exception.EnrollRegisterException;
import com.example.demo.register.exception.StudyRegisterException;
import com.example.demo.register.exception.StudyRegisterMemberException;
import com.example.demo.register.json.CountCreditAllJson;
import com.example.demo.register.json.DayAllJson;
import com.example.demo.register.json.StudyRegisterAllJson;
import com.example.demo.register.json.StudyRegisterMemberAllJson;
import com.example.demo.register.json.StudyRegisterStudentJson;
import com.example.demo.register.json.StudyRegisterTeacherJson;
import com.example.demo.register.json.StudyRegisterUidJson;
import com.example.demo.register.payload.enrollRegister.EnrollRegisterInsertPayload;
import com.example.demo.register.payload.studyRegister.StudyRegisterInsertPayload;
import com.example.demo.register.payload.studyRegister.StudyRegisterUpdatePayload;
import com.example.demo.register.payload.studyRegisterMember.StudyRegisterMemberInsertPayload;
import com.example.demo.register.payload.studyRegisterMember.StudyRegisterMemberUpdatePayload;
import com.example.demo.subject.exception.SubjectException;
import com.example.demo.university.exception.CreditOfTermException;
import com.example.demo.university.exception.TermException;

@RestController
@RequestMapping("/studyRegister")
public class StudyRegisterController {
	
	@Autowired
	StudyRegisterBusiness studyRegisterBusiness;
	
	@GetMapping(value = "/viewAllStudyRegisterMember/{studyRegisterUid}")
	public ResponseEntity<List<StudyRegisterMemberAllJson>> viewAllStudyRegisterMember(@PathVariable String studyRegisterUid) throws StudyRegisterException {
		return ResponseEntity.ok(studyRegisterBusiness.viewAllStudyRegisterMember(studyRegisterUid));
	}
	
	@PostMapping(value = "/insertStudyRegisterMember")
	public ResponseEntity<Void> insertStudyRegisterMember(@RequestBody StudyRegisterMemberInsertPayload body) throws StudyRegisterException, MemberException, StudyRegisterMemberException, LogException {
		studyRegisterBusiness.insertStudyRegisterMember(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateStudyRegisterMember")
	public ResponseEntity<Void> updateStudyRegisterMember(@RequestBody StudyRegisterMemberUpdatePayload body)  throws StudyRegisterException, MemberException, StudyRegisterMemberException, LogException {
		studyRegisterBusiness.updateStudyRegisterMember(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	
	@DeleteMapping(value = "/delStudyRegisterMember/{uid}")
	public ResponseEntity<Void> delStudyRegisterMember(@PathVariable String uid) throws EnrollRegisterException, StudyRegisterException, LogException, StudyRegisterMemberException {
		studyRegisterBusiness.delStudyRegisterMember(uid);
		return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewStudyRegisterTeacher/{termUid}")
	public ResponseEntity<List<StudyRegisterTeacherJson>> viewStudyRegisterTeacher(@PathVariable String termUid) throws MemberException, TermException{
		return ResponseEntity.ok(studyRegisterBusiness.viewStudyRegisterTeacher(termUid));
	}
	
	@GetMapping(value = "/viewAllCountCredit/{termUid}")
	public ResponseEntity<CountCreditAllJson> viewAllCountCredit(@PathVariable String termUid) throws MemberException, StudyProgramException, CreditOfTermException, TermException {
		return ResponseEntity.ok(studyRegisterBusiness.viewAllCountCredit(termUid));
	}
	
	@GetMapping(value = "/viewStudyRegisterStudent/{termUid}")
	public ResponseEntity<List<StudyRegisterStudentJson>> viewStudyRegisterStudent(@PathVariable String termUid) throws TermException, MemberException{
		return ResponseEntity.ok(studyRegisterBusiness.viewStudyRegisterStudent(termUid));
	}
	
	@GetMapping(value = "/viewStudyRegisterStudentTable/{termUid}")
	public ResponseEntity<List<StudyRegisterStudentJson>> viewStudyRegisterStudentTable(@PathVariable String termUid) throws TermException, MemberException{
		return ResponseEntity.ok(studyRegisterBusiness.viewStudyRegisterStudentTable(termUid));
	}
	
	@PostMapping(value = "/insertEnrollRegister")
	public ResponseEntity<Void> insertEnrollRegister(@RequestBody EnrollRegisterInsertPayload body) throws StudyRegisterException, MemberException, EnrollRegisterException, BillProgramException, LogException {
		studyRegisterBusiness.insertEnrollRegister(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/saveEnrollRegister/{uid}")
	public ResponseEntity<Void> saveEnrollRegister(@PathVariable String uid) throws EnrollRegisterException, StudyRegisterException, LogException {
		studyRegisterBusiness.saveEnrollRegister(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/delEnrollRegister/{uid}")
	public ResponseEntity<Void> delEnrollRegister(@PathVariable String uid) throws EnrollRegisterException, StudyRegisterException, LogException {
		studyRegisterBusiness.delEnrollRegister(uid);
		return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
//	@GetMapping(value = "/viewStudyRegisterStudyRegisterMember/{dayUid}/{memberUid}")
//	public ResponseEntity<List<StudyRegisterStudyRegisterMemberJson>> viewStudyRegisterStudyRegisterMember(@PathVariable String dayUid,@PathVariable String memberUid) throws CourseGroupDetException, GeSubjectGroupDetException, DayException, MemberException, TermException{
//		return ResponseEntity.ok(studyRegisterBusiness.viewStudyRegisterStudyRegisterMember(dayUid,memberUid));
//	}
//	
//	@GetMapping(value = "/viewAllStudyRegisterStudyRegisterMember/{dayUid}")
//	public ResponseEntity<List<List<StudyRegisterStudyRegisterMemberJson>>> viewAllStudyRegisterStudyRegisterMember(@PathVariable String dayUid) throws CourseGroupDetException, GeSubjectGroupDetException, DayException, MemberException, TermException{
//		return ResponseEntity.ok(studyRegisterBusiness.viewAllStudyRegisterStudyRegisterMember(dayUid));
//	}
	
	
//	@GetMapping(value = "/viewStudyRegisterStudyRegisterMember")
//	public ResponseEntity<List<StudyRegister>> viewStudyRegisterStudyRegisterMember() throws CourseGroupDetException, GeSubjectGroupDetException, DayException, MemberException{
//		return ResponseEntity.ok(studyRegisterBusiness.viewStudyRegisterStudyRegisterMember());
//	}
	
	@GetMapping(value = "/viewAllStudyRegister/{termUid}")
	public ResponseEntity<List<StudyRegisterAllJson>>  viewAllStudyRegister(@PathVariable String termUid) throws TermException {
		return ResponseEntity.ok(studyRegisterBusiness.viewAllStudyRegister(termUid));
	}
	
	@GetMapping(value = "/viewAllStudyRegisterFac/{termUid}")
	public ResponseEntity<List<StudyRegisterAllJson>>  viewAllStudyRegisterFac(@PathVariable String termUid) throws TermException, MemberException {
		return ResponseEntity.ok(studyRegisterBusiness.viewAllStudyRegisterFac(termUid));
	}
	
	@GetMapping(value = "/viewStudyRegister/{uid}")
	public ResponseEntity<StudyRegisterAllJson>  viewStudyRegister(@PathVariable String uid) throws StudyRegisterException {
		return ResponseEntity.ok(studyRegisterBusiness.viewStudyRegister(uid));
	}
	
	@GetMapping(value = "/viewAllDay")
	public ResponseEntity<List<DayAllJson>> viewAllDay(){
		return ResponseEntity.ok(studyRegisterBusiness.viewAllDay());
	}
	
	@PostMapping(value = "/insertStudyRegister")
	public ResponseEntity<StudyRegisterUidJson> insertStudyRegister(@RequestBody StudyRegisterInsertPayload body) throws StudyProgramDetException, MemberException, StudyRegisterException, TermException, RoomException, DayException, SubjectException, LogException{
		
//		return new ResponseEntity<>(HttpStatus.CREATED);
		return ResponseEntity.ok(studyRegisterBusiness.insertStudyRegister(body));
	}
//	
//	@GetMapping(value = "/viewAllStudyRegister")
//	public ResponseEntity<List<StudyRegisterJson>> viewAllStudyRegister(){
//		return ResponseEntity.ok(studyRegisterBusiness.viewAllStudyRegister());
//	}
//	
	@PutMapping(value = "/updateStudyRegister")
	public ResponseEntity<Void> updateStudyRegister(@RequestBody StudyRegisterUpdatePayload body) throws StudyRegisterException, SubjectException, MemberException, RoomException, DayException, StudyProgramDetException, LogException{
		studyRegisterBusiness.updateStudyRegister(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
//	
//	@GetMapping(value = "/viewStudyRegister/{uuid}")
//	public ResponseEntity<StudyRegisterJson> viewStudyRegister(@PathVariable String uuid) throws StudyRegisterException{
//		return ResponseEntity.ok(studyRegisterBusiness.viewStudyRegister(uuid));
//	}
//	
	@DeleteMapping(value = "/delStudyRegister/{uuid}")
	public ResponseEntity<Void> delStudyRegister(@PathVariable String uuid) throws StudyRegisterException, LogException{
		studyRegisterBusiness.delStudyRegister(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
