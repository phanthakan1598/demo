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

import com.example.demo.bill.business.CreditPriceBusiness;
import com.example.demo.bill.exception.CreditPriceException;
import com.example.demo.bill.json.CreditPriceAllJson;
import com.example.demo.bill.payload.creditPrice.CreditPriceInsertPayload;
import com.example.demo.bill.payload.creditPrice.CreditPriceUpdatePayload;
import com.example.demo.member.exception.LogException;
import com.example.demo.university.exception.DegreeLevelException;

@RestController
@RequestMapping("/creditPrice")
public class CreditPriceController {
	
	@Autowired
	CreditPriceBusiness creditPriceBusiness;
	
	@GetMapping(value = "/viewAllCreditPrice")
	public ResponseEntity<List<CreditPriceAllJson>> viewAllCreditPrice() {
		return ResponseEntity.ok(creditPriceBusiness.viewAllCreditPrice());
	}
	
	@GetMapping(value = "/viewCreditPrice/{uid}")
	public ResponseEntity<CreditPriceAllJson> viewCreditPrice(@PathVariable String uid) throws CreditPriceException{
		return ResponseEntity.ok(creditPriceBusiness.viewCreditPrice(uid));
	}
	
	@PostMapping(value = "/insertCreditPrice")
	public ResponseEntity<Void> insertCreditPrice(@RequestBody CreditPriceInsertPayload body) throws CreditPriceException, DegreeLevelException, LogException{
		creditPriceBusiness.insertCreditPrice(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateCreditPrice")
	public ResponseEntity<Void> updateCreditPrice(@RequestBody CreditPriceUpdatePayload body) throws CreditPriceException, LogException {
		creditPriceBusiness.updateCreditPrice(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/deleteCreditPrice/{uid}")
	public ResponseEntity<Void> deleteCreditPrice(@PathVariable String uid) throws CreditPriceException, LogException {
		creditPriceBusiness.deleteCreditPrice(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
