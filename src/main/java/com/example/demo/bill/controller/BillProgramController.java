package com.example.demo.bill.controller;

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

import com.example.demo.bill.business.BillProgramBusiness;
import com.example.demo.bill.exception.BillException;
import com.example.demo.bill.exception.BillProgramException;
import com.example.demo.bill.json.BillProgramAllJson;
import com.example.demo.bill.payload.billProgram.BillProgramInsertPayload;
import com.example.demo.bill.payload.billProgram.BillProgramUpdatePayload;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.member.exception.LogException;
import com.example.demo.university.exception.DegreeLevelException;

@RestController
@RequestMapping("/billProgram")
public class BillProgramController {
	
	@Autowired
	BillProgramBusiness billProgramBusiness;
	
	@PostMapping(value = "/insertBillProgram")
	public ResponseEntity<Void> insertBillProgram(@RequestBody BillProgramInsertPayload body) throws BillProgramException, BillException, ProgramException, LogException {
		billProgramBusiness.insertBillProgram(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateBillProgram")
	public ResponseEntity<Void> updateBillProgram(@RequestBody BillProgramUpdatePayload body) throws BillProgramException, BillException, ProgramException, LogException  {
		billProgramBusiness.updateBillProgram(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/deleteBillProgram/{uid}")
	public ResponseEntity<Void> deleteBillProgram(@PathVariable String uid) throws BillProgramException, LogException {
		billProgramBusiness.delBillProgram(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewAllBillProgram/{uid}")
	public ResponseEntity<List<BillProgramAllJson>> viewAllBillProgram(@PathVariable String uid) throws DegreeLevelException{
		return ResponseEntity.ok(billProgramBusiness.viewAllBillProgram(uid));
	}
	
	@GetMapping(value = "/viewBillProgram/{uid}")
	public ResponseEntity<BillProgramAllJson> viewBillProgram(@PathVariable String uid) throws BillProgramException{
		return ResponseEntity.ok(billProgramBusiness.viewBillProgram(uid));
	}
	
	
}
