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
import com.example.demo.university.business.CalendarBusiness;
import com.example.demo.university.exception.CalendarException;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.exception.TermException;
import com.example.demo.university.json.CalendarAllJson;
import com.example.demo.university.json.TermAllJson;
import com.example.demo.university.payload.calendar.CalendarInsertPayload;
import com.example.demo.university.payload.calendar.CalendarUpdatePayload;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
	
	@Autowired
	CalendarBusiness calendarBusiness;
	

	@GetMapping(value = "/viewTerm/{degreeUid}/{year}")
	public ResponseEntity<List<TermAllJson>> viewAllMemberPersonal(@PathVariable String degreeUid,@PathVariable int year) throws DegreeLevelException{
		return ResponseEntity.ok(calendarBusiness.viewTerm(degreeUid,year));
	}
	
	@GetMapping(value = "/viewAllCalendar/{uid}")
	public ResponseEntity<List<CalendarAllJson>> viewAllCalendar(@PathVariable String uid) throws TermException{
		return ResponseEntity.ok(calendarBusiness.viewTerm(uid));
	}
	
	@PostMapping(value = "/insertCalendar")
	public ResponseEntity<Void> insertCalendar(@RequestBody CalendarInsertPayload body) throws DegreeLevelException, CalendarException, TermException, LogException {
		calendarBusiness.insertCalendar(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateCalendar")
	public ResponseEntity<Void> updateCalendar(@RequestBody CalendarUpdatePayload body) throws DegreeLevelException, CalendarException, TermException, LogException {
		calendarBusiness.updateCalendar(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewCalendar/{uid}")
	public ResponseEntity<CalendarAllJson> viewCalendar(@PathVariable String uid) throws CalendarException{
		return ResponseEntity.ok(calendarBusiness.viewCalendar(uid));
	}
	
	@DeleteMapping(value = "/deleteCalendar/{uid}")
	public ResponseEntity<Void> deleteCalendar(@PathVariable String uid)  throws DegreeLevelException, CalendarException, LogException {
		calendarBusiness.deleteCalendar(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
