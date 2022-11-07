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
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.member.exception.LogException;
import com.example.demo.subject.business.CourseStructureBusiness;
import com.example.demo.subject.exception.CourseStructureDetException;
import com.example.demo.subject.exception.CourseStructureException;
import com.example.demo.subject.json.CourseStructureAllJson;
import com.example.demo.subject.payload.courseStructure.CourseStructureInsertPayload;
import com.example.demo.subject.payload.courseStructure.CourseStructureUpdatePayload;
import com.example.demo.university.exception.DegreeLevelException;

@RestController
@RequestMapping("/courseStructure")
public class CourseStructureController {
	
	@Autowired
	CourseStructureBusiness courseStructureBusiness;
	
	@GetMapping(value = "/viewAllCourseStructure/{degreeLevelUid}/{facUid}")
	public ResponseEntity<List<CourseStructureAllJson>> viewAllCourseStructure(@PathVariable String degreeLevelUid ,@PathVariable String facUid) throws DegreeLevelException, FacultyException{
		return ResponseEntity.ok(courseStructureBusiness.viewAllCourseStructure(degreeLevelUid,facUid));
	}

	@GetMapping(value = "/viewCourseStructure/{uuid}")
	public ResponseEntity<CourseStructureAllJson> viewCourseStructure(@PathVariable String uuid) throws CourseStructureException {
		return ResponseEntity.ok(courseStructureBusiness.viewCourseStructure(uuid));
	}
	
	@PostMapping(value = "/insertCourseStructure")
	public ResponseEntity<Void> insertCourseStructure(@RequestBody CourseStructureInsertPayload body) throws ProgramException, CourseStructureException, CourseStructureDetException, LogException{
		courseStructureBusiness.insertCourseStructure(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateCourseStructure")
	public ResponseEntity<Void> updateCourseStructure(@RequestBody CourseStructureUpdatePayload body) throws CourseStructureException, LogException{
		courseStructureBusiness.updateCourseStructure(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/delCourseStructure/{uuid}")
	public ResponseEntity<Void> delCourseStructure(@PathVariable String uuid) throws CourseStructureException, LogException {
		courseStructureBusiness.delCourseStructure(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
