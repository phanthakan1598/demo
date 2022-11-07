package com.example.demo.faculty.controller;

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

import com.example.demo.faculty.business.FacultyBusiness;
import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.json.FacultyJson;
import com.example.demo.faculty.payload.FacultyInsertPayload;
import com.example.demo.faculty.payload.FacultyUpdatePayload;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;

@RestController
@RequestMapping("/faculty/faculty")
public class FacultyController {
	
	@Autowired
	FacultyBusiness facultyBusiness;
	
	@PostMapping(value = "/insertFaculty")
	public ResponseEntity<Void> insertFaculty(@RequestBody FacultyInsertPayload body) throws FacultyException, MemberException, LogException{
		facultyBusiness.insertFaculty(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/viewAllFaculty")
	public ResponseEntity<List<FacultyJson>> viewAllFaculty(){
		return ResponseEntity.ok(facultyBusiness.viewAllFaculty());
	}
	
	@GetMapping(value = "/viewAllFacultyAndGe")
	public ResponseEntity<List<FacultyJson>> viewAllFacultyAndGe(){
		return ResponseEntity.ok(facultyBusiness.viewAllFacultyAndGe());
	}
	
	@PutMapping(value = "/updateFaculty")
	public ResponseEntity<Void> updateFaculty(@RequestBody FacultyUpdatePayload body) throws FacultyException, LogException{
		facultyBusiness.updateFaculty(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewFaculty/{uuid}")
	public ResponseEntity<FacultyJson> viewFaculty(@PathVariable String uuid) throws FacultyException{
		return ResponseEntity.ok(facultyBusiness.viewFaculty(uuid));
	}
	
	@DeleteMapping(value = "/delFaculty/{uuid}")
	public ResponseEntity<Void> delFaculty(@PathVariable String uuid) throws FacultyException, LogException{
		facultyBusiness.delFaculty(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
