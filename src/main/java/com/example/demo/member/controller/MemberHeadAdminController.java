package com.example.demo.member.controller;

import java.util.List;
import java.util.Map;

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
import com.example.demo.member.business.MemberHeadAdminBusiness;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.LogJson;
import com.example.demo.member.json.UniversityAllJson;
import com.example.demo.member.json.admin.MemberAdminAllJson;
import com.example.demo.member.json.admin.MemberAdminJson;
import com.example.demo.member.payload.memberAdmin.MemberAdminInsertPayload;
import com.example.demo.member.payload.memberAdmin.MemberAdminUpdatePayload;
import com.example.demo.university.exception.UniversityException;


@Secured("ROLE_HEAD_ADMIN")
@RestController
@RequestMapping("/member/hadmin")
public class MemberHeadAdminController {
	
	@Autowired
	MemberHeadAdminBusiness memberHeadAdminBusiness;
	
	@GetMapping(value = "/viewAllLog")
	public ResponseEntity<List<LogJson>> viewAllLog(){
		return ResponseEntity.ok(memberHeadAdminBusiness.viewAllLog());
	}
	
	@GetMapping(value = "/viewAllMemberAdmin")
	public ResponseEntity<List<MemberAdminAllJson>> viewAllMemberAdmin(){
		return ResponseEntity.ok(memberHeadAdminBusiness.viewAllMemberAdmin());
	}
	
	@GetMapping(value = "/viewAllUniversity")
	public ResponseEntity<List<UniversityAllJson>> viewAllUniversity(){
		return ResponseEntity.ok(memberHeadAdminBusiness.viewAllListUniversity());
	}
	
	@PostMapping(value = "/insertMemberAdmin")
	public ResponseEntity<Void> insertMemberAdmin(@RequestBody MemberAdminInsertPayload body) throws MemberException, AddressException{
		memberHeadAdminBusiness.insertMemberAdmin(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateMemberAdmin")
	public ResponseEntity<Void> updateMemberAdmin(@RequestBody MemberAdminUpdatePayload body) throws MemberException, AddressException{
		memberHeadAdminBusiness.updateMemberAdmin(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/deleteMemberAdmin/{uid}")
	public ResponseEntity<Void> deleteMemberAdmin(@PathVariable String uid) throws MemberException, UniversityException, LogException{
		memberHeadAdminBusiness.deleteMemberAdmin(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewUpdateMemberAdmin/{uid}")
	public ResponseEntity<MemberAdminJson> viewUpdateMemberAdmin(@PathVariable String uid) throws MemberException{
		return ResponseEntity.ok(memberHeadAdminBusiness.viewMemberAdmin(uid));
	}
	
	@GetMapping(value = "/countAllUniversity")
	public ResponseEntity<Integer> countAllUniversity() throws MemberException{
		return ResponseEntity.ok(memberHeadAdminBusiness.countMemberUniversity());
	}
	
	@GetMapping(value = "/countRegionUniversity")
	public ResponseEntity<Map<String, Long>> countRegionUniversity() throws MemberException{
		return ResponseEntity.ok(memberHeadAdminBusiness.viewAllUniversity());
	}
	
}