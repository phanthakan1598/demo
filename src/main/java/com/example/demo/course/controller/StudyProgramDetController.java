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

import com.example.demo.course.business.StudyProgramDetBusiness;
import com.example.demo.course.exception.StudyProgramDetException;
import com.example.demo.course.exception.StudyProgramException;
import com.example.demo.course.json.StudyProgramDetAllJson;
import com.example.demo.course.payload.studyProgramDet.StudyProgramDetInsertPayload;
import com.example.demo.course.payload.studyProgramDet.StudyProgramDetUpdatePayload;
import com.example.demo.member.exception.LogException;
import com.example.demo.subject.exception.SubjectException;
import com.example.demo.subject.exception.TypeSubjectException;

@RestController
@RequestMapping("/studyProgramDet")
public class StudyProgramDetController {
	
	@Autowired
	StudyProgramDetBusiness studyProgramDetBusiness;
	
	@GetMapping(value = "/viewAllStudyProgramDet/{uid}")
	public ResponseEntity<List<StudyProgramDetAllJson>> viewAllStudyProgramDet(@PathVariable String uid) throws StudyProgramException, SubjectException {
		return ResponseEntity.ok(studyProgramDetBusiness.viewAllStudyProgramDet(uid));
	}
	
	@GetMapping(value = "/viewStudyProgramDet/{uid}")
	public ResponseEntity<StudyProgramDetAllJson> viewStudyProgramDet(@PathVariable String uid) throws StudyProgramDetException, SubjectException{
		return ResponseEntity.ok(studyProgramDetBusiness.viewStudyProgramDet(uid));
	}
	
	@PostMapping(value = "/insertStudyProgramDet")
	public ResponseEntity<Void> insertStudyProgramDet(@RequestBody StudyProgramDetInsertPayload body) throws TypeSubjectException, StudyProgramException, StudyProgramDetException, SubjectException, LogException {
		studyProgramDetBusiness.insertStudyProgramDet(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateStudyProgramDet")
	public ResponseEntity<Void> updateStudyProgramDet(@RequestBody StudyProgramDetUpdatePayload body) throws StudyProgramDetException, TypeSubjectException, SubjectException, LogException  {
		studyProgramDetBusiness.updateStudyProgramDet(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping(value = "/deleteStudyProgramDet/{uid}")
	public ResponseEntity<Void> deleteStudyProgramDet(@PathVariable String uid) throws StudyProgramDetException, LogException  {
		studyProgramDetBusiness.deleteStudyProgramDet(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
