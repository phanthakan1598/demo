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

import com.example.demo.faculty.business.ProgramBusiness;
import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.faculty.json.ProgramJson;
import com.example.demo.faculty.payload.ProgramInsertPayload;
import com.example.demo.faculty.payload.ProgramUpdatePayload;
import com.example.demo.member.exception.LogException;
import com.example.demo.university.exception.DegreeLevelException;

@RestController
//@Secured({ "ROLE_PERSONAL", "ROLE_COURSE" })
@RequestMapping("/faculty/program")
public class ProgramController {
	
	@Autowired
	ProgramBusiness programBusiness;
	
	@PostMapping(value = "/insertProgram")
	public ResponseEntity<Void> insertProgram(@RequestBody ProgramInsertPayload body) throws ProgramException, FacultyException, DegreeLevelException, LogException{
		programBusiness.insertProgram(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/viewAllProgram/{facUid}/{degreeUid}")
	public ResponseEntity<List<ProgramJson>> viewAllProgram(@PathVariable String facUid,@PathVariable String degreeUid) throws FacultyException, DegreeLevelException{
		return ResponseEntity.ok(programBusiness.viewAllProgram(facUid,degreeUid));
	}
	
	@PutMapping(value = "/updateProgram")
	public ResponseEntity<Void> updateProgram(@RequestBody ProgramUpdatePayload body) throws ProgramException, LogException{
		programBusiness.updateProgram(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewProgram/{uuid}")
	public ResponseEntity<ProgramJson> viewProgram(@PathVariable String uuid) throws ProgramException{
		return ResponseEntity.ok(programBusiness.viewProgram(uuid));
	}
	
	@DeleteMapping(value = "/delProgram/{uuid}")
	public ResponseEntity<Void> delProgram(@PathVariable String uuid) throws ProgramException, LogException{
		programBusiness.delProgram(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
