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

import com.example.demo.bill.business.BillBusiness;
import com.example.demo.bill.exception.BillException;
import com.example.demo.bill.json.BillAllJson;
import com.example.demo.bill.payload.bill.BillInsertPayload;
import com.example.demo.bill.payload.bill.BillUpdatePayload;
import com.example.demo.member.exception.LogException;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.exception.TermException;

@RestController
@RequestMapping("/bill")
public class BillController {
	
	@Autowired
	BillBusiness billBusiness;
	
	@PostMapping(value = "/insertBill")
	public ResponseEntity<Void> insertBill(@RequestBody BillInsertPayload body) throws BillException, DegreeLevelException, LogException{
		billBusiness.insertBill(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateBill")
	public ResponseEntity<Void> updateBill(@RequestBody BillUpdatePayload body) throws BillException, LogException {
		billBusiness.updateBill(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewAllBill/{uid}")
	public ResponseEntity<List<BillAllJson>> viewAllBill(@PathVariable String uid) throws TermException, DegreeLevelException{
		return ResponseEntity.ok(billBusiness.viewAllBill(uid));
	}
	
	@GetMapping(value = "/viewBill/{uid}")
	public ResponseEntity<BillAllJson> viewBill(@PathVariable String uid) throws BillException{
		return ResponseEntity.ok(billBusiness.viewBill(uid));
	}
	
	@DeleteMapping(value = "/deleteBill/{uid}")
	public ResponseEntity<Void> deleteBill(@PathVariable String uid) throws BillException, LogException {
		billBusiness.deleteBill(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
