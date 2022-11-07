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
import com.example.demo.member.exception.MemberException;
import com.example.demo.subject.business.CourseStructureDetBusiness;
import com.example.demo.subject.exception.CourseStructureDetException;
import com.example.demo.subject.exception.CourseStructureException;
import com.example.demo.subject.exception.SubjectException;
import com.example.demo.subject.json.CourseStructureDetAllJson;
import com.example.demo.subject.json.SubjectCourseStructureAllJson;
import com.example.demo.subject.payload.courseStructureDet.CourseStructureDetInsertPayload;
import com.example.demo.subject.payload.courseStructureDet.CourseStructureDetUpdatePayload;
import com.example.demo.university.exception.DegreeLevelException;

@RestController
@RequestMapping("/courseStructureDet")
public class CourseStructureDetController {
	
	@Autowired
	CourseStructureDetBusiness courseStructureDetBusiness;
	
	@GetMapping(value = "/viewAllSubjectCourseStructure/{degreeUid}")
	public ResponseEntity<List<SubjectCourseStructureAllJson>> viewAllSubjectCourseStructure(@PathVariable String degreeUid) throws DegreeLevelException, CourseStructureException, MemberException, FacultyException{
		return ResponseEntity.ok(courseStructureDetBusiness.viewAllSubjectCourseStructure(degreeUid));
	}
	
	@GetMapping(value = "/viewAllCourseStructureDet/{courseStructureUid}")
	public ResponseEntity<List<CourseStructureDetAllJson>> viewAllCourseStructureDet(@PathVariable String courseStructureUid) throws CourseStructureException{
		return ResponseEntity.ok(courseStructureDetBusiness.viewAllCourseStructureDet(courseStructureUid));
	}

	@GetMapping(value = "/viewCourseStructureDet/{uuid}")
	public ResponseEntity<CourseStructureDetAllJson> viewCourseStructureDet(@PathVariable String uuid) throws CourseStructureDetException {
		return ResponseEntity.ok(courseStructureDetBusiness.viewCourseStructureDet(uuid));
	}
	
	@PostMapping(value = "/insertCourseStructureDet")
	public ResponseEntity<Void> insertCourseStructureDet(@RequestBody CourseStructureDetInsertPayload body) throws CourseStructureException, SubjectException, CourseStructureDetException, LogException{
		courseStructureDetBusiness.insertCourseStructureDet(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateCourseStructureDet")
	public ResponseEntity<Void> updateCourseStructureDet(@RequestBody CourseStructureDetUpdatePayload body) throws CourseStructureDetException, SubjectException, LogException{
		courseStructureDetBusiness.updateCourseStructureDet(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/delCourseStructureDet/{uuid}")
	public ResponseEntity<Void> delCourseStructureDet(@PathVariable String uuid) throws CourseStructureDetException, LogException {
		courseStructureDetBusiness.delCourseStructureDet(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
