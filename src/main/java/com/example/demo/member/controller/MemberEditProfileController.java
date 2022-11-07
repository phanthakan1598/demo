package com.example.demo.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.member.business.MemberEditProfileBusiness;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.MemberEditProfile;
import com.example.demo.member.payload.member.EditProfileMemberPayload;
import com.example.demo.member.payload.member.MemberProfilePasswordPayload;

@RestController
@RequestMapping("/member")
public class MemberEditProfileController {

	@Autowired
	MemberEditProfileBusiness memberEditProfileBusiness;
	
	@PutMapping(value = "/editProfileMember")
	public ResponseEntity<Void> editProfileMember(@ModelAttribute EditProfileMemberPayload body) throws MemberException{
		memberEditProfileBusiness.editProfileMember(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<Void> changePassword(@RequestBody MemberProfilePasswordPayload body) throws MemberException, LogException { //ZCB-64
		memberEditProfileBusiness.changePassword(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = "/viewEditProfileMember")
	public ResponseEntity<MemberEditProfile> viewEditProfileMember() throws MemberException{
		return ResponseEntity.ok(memberEditProfileBusiness.viewMemberEditProfile());
	}
	
}
