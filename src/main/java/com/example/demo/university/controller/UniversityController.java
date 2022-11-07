package com.example.demo.university.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.address.exception.AddressException;
import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.university.business.UniversityBusiness;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.exception.UniversityException;
import com.example.demo.university.json.UniversityJson;
import com.example.demo.university.payload.university.UniversityInsertPayload;
import com.example.demo.university.payload.university.UniversityUpdatePayload;

//import com.example.demo.address.exception.AddressException;
//import com.example.demo.faculty.exception.FacultyException;
//import com.example.demo.faculty.exception.ProgramException;
//import com.example.demo.member.exception.LogException;
//import com.example.demo.member.exception.MemberException;
//import com.example.demo.university.business.UniversityBusiness;
//import com.example.demo.university.exception.DegreeLevelException;
//import com.example.demo.university.exception.UniversityException;
//import com.example.demo.university.json.UniversityJson;
//import com.example.demo.university.payload.university.UniversityInsertPayload;
//import com.example.demo.university.payload.university.UniversityUpdatePayload;

@RestController
@RequestMapping("/university")
public class UniversityController {
	
	@Autowired
	UniversityBusiness universityBusiness;
	
	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/insertUniversity")
	public ResponseEntity<Void> insertUniversity(@ModelAttribute UniversityInsertPayload body) throws IOException, UniversityException, AddressException, MemberException, DegreeLevelException, FacultyException, ProgramException, LogException{
		universityBusiness.insertUniversity(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping(value = "/updateUniversity")
	public ResponseEntity<Void> updateUniversity(@ModelAttribute UniversityUpdatePayload body) throws IOException, UniversityException, AddressException, MemberException, LogException{
		universityBusiness.updateUniversity(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewUniversity")
	public ResponseEntity<UniversityJson> viewUpdateMemberPersonal() throws UniversityException{
		return ResponseEntity.ok(universityBusiness.viewUniversity());
	}
}
