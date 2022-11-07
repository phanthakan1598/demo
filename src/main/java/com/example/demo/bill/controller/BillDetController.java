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

import com.example.demo.bill.business.BillDetBusiness;
import com.example.demo.bill.exception.BillDetException;
import com.example.demo.bill.exception.BillException;
import com.example.demo.bill.json.BillDetAllJson;
import com.example.demo.bill.payload.billDet.BillDetInsertPayload;
import com.example.demo.bill.payload.billDet.BillDetUpdatePayload;
import com.example.demo.member.exception.LogException;

@RestController
@RequestMapping("/billDet")
public class BillDetController {
	
	@Autowired
	BillDetBusiness billDetBusiness;
	
	@PostMapping(value = "/insertBillDet")
	public ResponseEntity<Void> insertBillDet(@RequestBody BillDetInsertPayload body) throws BillDetException, BillException, LogException {
		billDetBusiness.insertBillDet(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateBillDet")
	public ResponseEntity<Void> updateBillDet(@RequestBody BillDetUpdatePayload body) throws BillDetException, BillException, LogException{
		billDetBusiness.updateBillDet(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewAllBillDet/{uid}")
	public ResponseEntity<List<BillDetAllJson>> viewAllBillDet(@PathVariable String uid) throws BillException {
		return ResponseEntity.ok(billDetBusiness.viewAllBillDet(uid));
	}
	
	@GetMapping(value = "/viewBillDet/{uid}")
	public ResponseEntity<BillDetAllJson> viewBillDet(@PathVariable String uid) throws BillDetException {
		return ResponseEntity.ok(billDetBusiness.viewBillDet(uid));
	}
	
	@DeleteMapping(value = "/deleteBillDet/{uid}")
	public ResponseEntity<Void> deleteBillDet(@PathVariable String uid) throws BillDetException, LogException {
		billDetBusiness.delBillDet(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
