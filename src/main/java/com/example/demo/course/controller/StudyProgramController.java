package com.example.demo.course.controller;

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

import com.example.demo.course.business.StudyProgramBusiness;
import com.example.demo.course.exception.StudyProgramException;
import com.example.demo.course.json.StudyProgramAllJson;
import com.example.demo.course.payload.studyProgram.StudyProgramInsertPayload;
import com.example.demo.course.payload.studyProgram.StudyProgramUpdatePayload;
import com.example.demo.member.exception.LogException;
import com.example.demo.subject.exception.CourseStructureException;
import com.example.demo.university.exception.DegreeLevelException;

@RestController
@RequestMapping("/studyProgram")
public class StudyProgramController {
	
	@Autowired
	StudyProgramBusiness studyProgramBusiness;
	
	@GetMapping(value = "/viewAllStudyProgram/{uid}")
	public ResponseEntity<List<StudyProgramAllJson>> viewAllStudyProgram(@PathVariable String uid) throws DegreeLevelException{
		return ResponseEntity.ok(studyProgramBusiness.viewAllStudyProgram(uid));
	}
	
	@GetMapping(value = "/viewStudyProgram/{uid}")
	public ResponseEntity<StudyProgramAllJson> viewStudyProgram(@PathVariable String uid) throws StudyProgramException{
		return ResponseEntity.ok(studyProgramBusiness.viewStudyProgram(uid));
	}
	
	@PostMapping(value = "/insertStudyProgram")
	public ResponseEntity<Void> insertStudyProgram(@RequestBody StudyProgramInsertPayload body) throws StudyProgramException, CourseStructureException, LogException {
		studyProgramBusiness.insertStudyProgram(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateStudyProgram")
	public ResponseEntity<Void> updateStudyProgram(@RequestBody StudyProgramUpdatePayload body) throws StudyProgramException, LogException  {
		studyProgramBusiness.updateStudyProgram(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping(value = "/deleteStudyProgram/{uid}")
	public ResponseEntity<Void> deleteStudyProgram(@PathVariable String uid) throws StudyProgramException, LogException  {
		studyProgramBusiness.deleteStudyProgram(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
