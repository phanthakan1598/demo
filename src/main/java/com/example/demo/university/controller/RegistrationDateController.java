package com.example.demo.university.controller;

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

import com.example.demo.member.exception.LogException;
import com.example.demo.university.business.RegistrationDateBusiness;
import com.example.demo.university.exception.CalendarException;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.exception.RegistrationDateException;
import com.example.demo.university.exception.TermException;
import com.example.demo.university.json.RegistrationDateAllJson;
import com.example.demo.university.payload.registrationDate.RegistrationDateInsertPayload;
import com.example.demo.university.payload.registrationDate.RegistrationDateUpdatePayload;

@RestController
@RequestMapping("/registrationDate")
public class RegistrationDateController {
	
	@Autowired
	RegistrationDateBusiness registrationDateBusiness;
	
	@PostMapping(value = "/insertRegistrationDate")
	public ResponseEntity<Void> insertRegistrationDate(@RequestBody RegistrationDateInsertPayload body) throws DegreeLevelException, RegistrationDateException, TermException, CalendarException, LogException {
		registrationDateBusiness.insertRegistrationDate(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateRegistrationDate")
	public ResponseEntity<Void> updateRegistrationDate(@RequestBody RegistrationDateUpdatePayload body) throws DegreeLevelException, RegistrationDateException, TermException, CalendarException, LogException {
		registrationDateBusiness.updateRegistrationDate(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/viewAllRegistrationDate/{termUuid}")
	public ResponseEntity<List<RegistrationDateAllJson>> viewAllRegistrationDate(@PathVariable String termUuid) throws TermException{
		return ResponseEntity.ok(registrationDateBusiness.viewRegistrationDateAll(termUuid));
	}
	
	@GetMapping(value = "/viewRegistrationDate/{uid}")
	public ResponseEntity<RegistrationDateAllJson> viewRegistrationDate(@PathVariable String uid) throws RegistrationDateException{
		return ResponseEntity.ok(registrationDateBusiness.viewRegistrationDate(uid));
	}
	
	@DeleteMapping(value = "/deleteRegistrationDate/{uid}")
	public ResponseEntity<Void> deleteRegistrationDate(@PathVariable String uid)  throws RegistrationDateException, CalendarException, LogException {
		registrationDateBusiness.deleteRegistrationDate(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/registrationDateBoolean/{termUid}/{levelClass}/{date}")
	public ResponseEntity<Boolean> registrationDateBoolean(@PathVariable String termUid,@PathVariable String levelClass,@PathVariable String date) throws TermException {
		return ResponseEntity.ok(registrationDateBusiness.registrationDateBoolean(termUid, levelClass, date));
	}
}
