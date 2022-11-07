package com.example.demo.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.repository.MemberRepository;
import com.example.demo.security.model.CustomUserDetails;
import com.example.demo.security.model.UserAuth;

import lombok.Setter;

@Service
@Transactional(readOnly = true)
@Setter
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	public CustomUserDetails loadUserByUsername(String memberUsername) throws UsernameNotFoundException {
		UserAuth user = memberRepository.findOneByMemberUsername(memberUsername, UserAuth.class)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s, not found", memberUsername)));
		
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
//		for (Role userRole : user.)
			roles.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
		
			
		return new CustomUserDetails(user.getMemberUuid(), user.getMemberPassword(), roles, user);
	}
	
	public CustomUserDetails loadUserByUuid(String memberUuid) throws UsernameNotFoundException {
		UserAuth user = memberRepository.findOneByMemberUuid(memberUuid, UserAuth.class)
									.orElseThrow(() -> new UsernameNotFoundException(String.format("UserUUID: %s, not found", memberUuid)));
		
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
		
	
	return new CustomUserDetails(user.getMemberUuid(), user.getMemberPassword(), roles, user);
	}

}
