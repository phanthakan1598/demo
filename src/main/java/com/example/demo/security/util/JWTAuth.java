package com.example.demo.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Member;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.model.UserAuth;

import lombok.Setter;

@Component
@Setter
public class JWTAuth implements JWTAuthImpl {

	@Autowired
	MemberService memberService;
	
	@Override
	public UserAuth getCurrentUser() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 return (UserAuth) authentication.getCredentials();
	}
	
	public long getCurrentUserId() {
		return getCurrentUser().getMemberId();
	}
	public String getCurrentUserUuid() {
		return getCurrentUser().getMemberUuid();
	}
	
	public Member getCurrentMember() throws MemberException {
		 return memberService.findMemberByUuid(getCurrentUser().getMemberUuid());
	}
}
