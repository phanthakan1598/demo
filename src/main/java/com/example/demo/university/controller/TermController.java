package com.example.demo.university.controller;

import java.io.IOException;
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

import com.example.demo.address.exception.AddressException;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.university.business.TermBusiness;
import com.example.demo.university.exception.CalendarException;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.exception.TermException;
import com.example.demo.university.exception.UniversityException;
import com.example.demo.university.json.TermAllJson;
import com.example.demo.university.payload.term.TermInsertPayload;
import com.example.demo.university.payload.term.TermUpdatePayload;

@RestController
@RequestMapping("/term")
public class TermController {
	
	@Autowired
	TermBusiness termBusiness;
	
	@PostMapping(value = "/insertTerm")
	public ResponseEntity<Void> insertTerm(@RequestBody TermInsertPayload body) throws IOException, TermException, AddressException, MemberException, DegreeLevelException, UniversityException, LogException{
		termBusiness.insertTerm(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateTerm")
	public ResponseEntity<Void> updateTerm(@RequestBody TermUpdatePayload body) throws IOException, TermException, AddressException, MemberException, LogException{
		termBusiness.updateTerm(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewAllTerm/{uid}")
	public ResponseEntity<List<TermAllJson>> viewAllTerm(@PathVariable String uid) throws TermException, DegreeLevelException{
		return ResponseEntity.ok(termBusiness.terms(uid));
	}
	
	@GetMapping(value = "/viewTerm/{uid}")
	public ResponseEntity<TermAllJson> viewTerm(@PathVariable String uid) throws TermException{
		return ResponseEntity.ok(termBusiness.term(uid));
	}
	
	@DeleteMapping(value = "/deleteTerm/{uid}")
	public ResponseEntity<Void> deleteTerm(@PathVariable String uid)  throws DegreeLevelException, CalendarException, TermException, LogException {
		termBusiness.deleteTerm(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
