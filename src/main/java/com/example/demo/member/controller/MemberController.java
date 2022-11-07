package com.example.demo.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.member.business.MemberBusiness;
import com.example.demo.member.exception.LogException;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberBusiness memberBusiness;
	
	@PatchMapping("/logout")
	public ResponseEntity<Void> logout() throws LogException{
		memberBusiness.logout();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
