package com.example.demo.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.address.exception.AddressException;
import com.example.demo.member.business.MemberAdminBusiness;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.personal.MemberPersonalAllJson;
import com.example.demo.member.json.personal.MemberPersonalJson;
import com.example.demo.member.payload.memberPersonal.MemberPersonalInsertPayload;
import com.example.demo.member.payload.memberPersonal.MemberPersonalUpdatePayload;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/member/admin")
public class MemberAdminController {

	@Autowired
	MemberAdminBusiness memberAdminBusiness;
	
	
	@GetMapping(value = "/viewAllMemberPersonal")
	public ResponseEntity<List<MemberPersonalAllJson>> viewAllMemberPersonal(){
		return ResponseEntity.ok(memberAdminBusiness.viewAllMemberPersonal());
	}
	
	@GetMapping(value = "/viewUpdateMemberPersonal/{uid}")
	public ResponseEntity<MemberPersonalJson> viewUpdateMemberPersonal(@PathVariable String uid) throws MemberException{
		return ResponseEntity.ok(memberAdminBusiness.viewMemberPersonal(uid));
	}
	
	@PostMapping(value = "/insertMemberPersonal")
	public ResponseEntity<Void> insertMemberPersonal(@RequestBody MemberPersonalInsertPayload body) throws MemberException, AddressException{
		memberAdminBusiness.insertMemberPersonal(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateMemberPersonal")
	public ResponseEntity<Void> updateMemberPersonal(@RequestBody MemberPersonalUpdatePayload body) throws MemberException, AddressException{
		memberAdminBusiness.updateMemberPersonal(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/deleteMemberPersonal/{uid}")
	public ResponseEntity<Void> deleteMemberPersonal(@PathVariable String uid) throws MemberException, LogException{
		memberAdminBusiness.deleteMemberPersonal(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
//	
//	@PutMapping(value = "/test")
//	public ResponseEntity<Void> updateMemberAdmin(@ModelAttribute MemberAdminUpdatePayload body) throws MemberException, IOException{
//			memberBusiness.updateMemberAdmin(body);
////		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}
	
}
