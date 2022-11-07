package com.example.demo.subject.controller;

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

import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.member.exception.LogException;
import com.example.demo.subject.business.GroupSubjectBusiness;
import com.example.demo.subject.exception.GroupSubjectException;
import com.example.demo.subject.exception.TypeSubjectException;
import com.example.demo.subject.json.GroupSubjectAllJson;
import com.example.demo.subject.json.TypeSubjectAllJson;
import com.example.demo.subject.payload.groupSubject.GroupSubjectInsertPayload;
import com.example.demo.subject.payload.groupSubject.GroupSubjectUpdatePayload;
import com.example.demo.university.exception.DegreeLevelException;

@RestController
@RequestMapping("/groupSubject")
public class GroupSubjectController {
	
	@Autowired
	GroupSubjectBusiness groupSubjectBusiness;
	
	@GetMapping(value = "/viewAllTypeSubject")
	public ResponseEntity<List<TypeSubjectAllJson>> viewAllTypeSubject(){
		return ResponseEntity.ok(groupSubjectBusiness.viewAllTypeSubject());
	}
	
	@GetMapping(value = "/viewAllGroupSubjectMajor/{degreeLevelUid}/{facUid}")
	public ResponseEntity<List<GroupSubjectAllJson>> viewAllGroupSubjectMajor(@PathVariable String degreeLevelUid ,@PathVariable String facUid) throws DegreeLevelException, FacultyException{
		return ResponseEntity.ok(groupSubjectBusiness.viewAllGroupSubjectMajor(degreeLevelUid,facUid));
	}
	
	@GetMapping(value = "/viewAllGroupSubjectGe/{degreeLevelUid}")
	public ResponseEntity<List<GroupSubjectAllJson>> viewAllGroupSubjectGe(@PathVariable String degreeLevelUid) throws DegreeLevelException, FacultyException{
		return ResponseEntity.ok(groupSubjectBusiness.viewAllGroupSubjectGe(degreeLevelUid));
	}
	
	@GetMapping(value = "/viewGroupSubject/{uuid}")
	public ResponseEntity<GroupSubjectAllJson> viewGroupSubject(@PathVariable String uuid) throws GroupSubjectException {
		return ResponseEntity.ok(groupSubjectBusiness.viewGroupSubject(uuid));
	}
	
	@PostMapping(value = "/insertGroupSubject")
	public ResponseEntity<Void> insertGroupSubject(@RequestBody GroupSubjectInsertPayload body) throws GroupSubjectException, DegreeLevelException, TypeSubjectException, FacultyException, LogException {
		groupSubjectBusiness.insertGroupSubject(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateGroupSubject")
	public ResponseEntity<Void> updateGroupSubject(@RequestBody GroupSubjectUpdatePayload body) throws GroupSubjectException, LogException{
		groupSubjectBusiness.updateGroupSubject(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/delGroupSubject/{uuid}")
	public ResponseEntity<Void> delGroupSubject(@PathVariable String uuid) throws GroupSubjectException, LogException {
		groupSubjectBusiness.delGroupSubject(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
