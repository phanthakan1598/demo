package com.example.demo.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.BaseException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.payload.SendMailPayload;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.exception.JwtExceptions;
import com.example.demo.security.json.MemberDetailJson;
import com.example.demo.security.payload.Authen;
import com.example.demo.security.payload.Forgotpassword;
import com.example.demo.security.payload.VerifyPayload;
import com.example.demo.security.service.AuthenticationService;
import com.example.demo.springEmail.EmailSenderService;

import io.micrometer.core.instrument.MeterRegistry;
@RestController
@RequestMapping("/authen")
public class AuthenticationController {
	@Autowired
	private MeterRegistry meterRegistry;
	
	@Autowired
    private AuthenticationService authenticationService;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	@Autowired
	MemberService memberService;
	 
	@PostMapping(value = "/login")
    public ResponseEntity<MemberDetailJson> login(@RequestBody Authen body) throws BaseException {
		MemberDetailJson memberDetailJson = authenticationService.login(body.getMemberUsername(), body.getMemberPassword());
       
        meterRegistry.counter("login_count", "status", "200").increment();
        return ResponseEntity.ok(memberDetailJson);
    }
	
	@PostMapping(value = "/refreshToken")
	public ResponseEntity<MemberDetailJson> refreshToken(HttpServletRequest request) throws JwtExceptions {
	    MemberDetailJson memberDetailJson = authenticationService.refreshToken(request);
	    return ResponseEntity.ok(memberDetailJson);
	}
	
	@PostMapping("/sendMail")
	public ResponseEntity<String> sendMail(@RequestBody SendMailPayload payload) {
		emailSenderService.sendEmail(payload);
		return ResponseEntity.ok("sendEmail Success");
	}
	
	@GetMapping(value = "/searchEmail/{memberEmail}")
	public ResponseEntity<Boolean> emailMemberList(@PathVariable String memberEmail){
		return ResponseEntity.ok(memberService.getEmailMember(memberEmail));
	}
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<String> forgotpassword(@RequestBody Forgotpassword payload) throws MemberException{
		memberService.forgotPassword(payload);
		return ResponseEntity.ok("ResetPassSuccess");
	}
	
	@PostMapping("/verify")
	public ResponseEntity<String> verifyIdentity(@RequestBody VerifyPayload payload) throws MemberException{
		memberService.verifyIdentity(payload);
		return ResponseEntity.ok("VerifySuccess");
	}
	
}
