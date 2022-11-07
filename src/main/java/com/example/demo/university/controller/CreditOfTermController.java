package com.example.demo.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.university.business.CreditOfTermBusiness;
import com.example.demo.university.exception.CreditOfTermException;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.json.CreditOfTermAllJson;
import com.example.demo.university.payload.creditOfTerm.CreditOfTermInsertPayload;
import com.example.demo.university.payload.creditOfTerm.CreditOfTermUpdatePayload;

@RestController
@RequestMapping("/creditOfTerm")
public class CreditOfTermController {
	
	@Autowired
	CreditOfTermBusiness creditOfTermBusiness;
	
	@PostMapping(value = "/insertCreditOfTerm")
	public ResponseEntity<Void> insertCreditOfTerm(@RequestBody CreditOfTermInsertPayload body) throws ProgramException, CreditOfTermException, LogException  {
		creditOfTermBusiness.insertCreditOfTerm(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/viewAllCreditOfTerm/{facUid}/{degreeUid}")
	public ResponseEntity<List<CreditOfTermAllJson>> viewAllCreditOfTerm(@PathVariable String facUid,@PathVariable String degreeUid) throws  FacultyException, DegreeLevelException {
		return ResponseEntity.ok(creditOfTermBusiness.viewAllCreditOfTerm(facUid,degreeUid));
	}
	
	@GetMapping(value = "/viewAllCreditOfTermProgram/{proUid}")
	public ResponseEntity<List<CreditOfTermAllJson>> viewAllCreditOfTermProgram(@PathVariable String proUid) throws ProgramException{
		return ResponseEntity.ok(creditOfTermBusiness.viewAllCreditOfTermProgram(proUid));
	}
	
	@GetMapping(value = "/viewCreditOfTerm/{uid}")
	public ResponseEntity<CreditOfTermAllJson> viewCreditOfTerm(@PathVariable String uid) throws CreditOfTermException{
		return ResponseEntity.ok(creditOfTermBusiness.viewCreditOfTerm(uid));
	}
	
	@PutMapping(value = "/updateCreditOfTerm")
	public ResponseEntity<Void> updateCreditOfTerm(@RequestBody CreditOfTermUpdatePayload body) throws ProgramException, CreditOfTermException, LogException {
		creditOfTermBusiness.updateCreditOfTerm(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
