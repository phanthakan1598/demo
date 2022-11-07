package com.example.demo.subject.controller;

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
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.subject.business.SubjectBusiness;
import com.example.demo.subject.exception.CourseStructureDetException;
import com.example.demo.subject.exception.GroupSubjectException;
import com.example.demo.subject.exception.SubjectException;
import com.example.demo.subject.json.SubjectAllJson;
import com.example.demo.subject.payload.subject.StudentUploadPayload;
import com.example.demo.subject.payload.subject.SubjectInsertPayload;
import com.example.demo.subject.payload.subject.SubjectUpdatePayload;
import com.example.demo.subject.payload.subject.SubjectUploadPayload;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	
	@Autowired
	SubjectBusiness subjectBusiness;
	
	@PostMapping(value = "/uploadStudent")
	public ResponseEntity<Void> uploadStudent(@ModelAttribute StudentUploadPayload body) throws IOException, GroupSubjectException, SubjectException, LogException, CourseStructureDetException, NumberFormatException, AddressException, FacultyException, ProgramException, MemberException {
		subjectBusiness.uploadStudent(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/uploadSubject")
	public ResponseEntity<Void> uploadSubject(@ModelAttribute SubjectUploadPayload body) throws IOException, GroupSubjectException, SubjectException, LogException, CourseStructureDetException {
		subjectBusiness.uploadSubject(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/viewAllSubject/{groupSubjectUid}")
	public ResponseEntity<List<SubjectAllJson>> viewAllSubject(@PathVariable String groupSubjectUid) throws GroupSubjectException {
		return ResponseEntity.ok(subjectBusiness.viewAllSubject(groupSubjectUid));
	}
	
	@GetMapping(value = "/viewSubject/{uuid}")
	public ResponseEntity<SubjectAllJson> viewSubject(@PathVariable String uuid) throws SubjectException {
		return ResponseEntity.ok(subjectBusiness.viewSubject(uuid));
	}
	
	@PostMapping(value = "/insertSubject")
	public ResponseEntity<Void> insertSubject(@RequestBody SubjectInsertPayload body) throws GroupSubjectException, SubjectException, CourseStructureDetException, LogException  {
		subjectBusiness.insertSubject(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateSubject")
	public ResponseEntity<Void> updateSubject(@RequestBody SubjectUpdatePayload body) throws SubjectException, LogException{
		subjectBusiness.updateSubject(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/delSubject/{uuid}")
	public ResponseEntity<Void> delSubject(@PathVariable String uuid) throws SubjectException, LogException {
		subjectBusiness.delSubject(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
