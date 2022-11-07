package com.example.demo.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Log;
import com.example.demo.member.model.MemberFaculty;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.service.LogService;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.exception.AuthenException;
import com.example.demo.security.exception.JwtExceptions;
import com.example.demo.security.json.MemberDetailJson;
import com.example.demo.security.model.CustomUserDetails;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Setter
@AllArgsConstructor 
@Slf4j
public class AuthenticationService {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtilService jwtUtilService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	LogService logService;
	
	public MemberDetailJson login(String username ,String password) throws AuthenException, JwtExceptions, MemberException, LogException {
		MemberDetailJson memberDetailJson = new MemberDetailJson();
		
		if(username == null || username.isEmpty()) {
        	throw AuthenException.authenUsernameEmpty();
        }
        
        if(password == null ||password.isEmpty()) {
        	throw AuthenException.authenPasswordEmpty();
        }
        
        try {
        	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));	
        }catch (BadCredentialsException e) {
        	log.info("User [{}] login failed.");
        	throw AuthenException.authenIncorrect();
		}
		

		final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String accessToken = jwtUtilService.generateToken(userDetails.getUser().getMemberUuid());
        String refreshToken = jwtUtilService.generateRefreshToken(userDetails.getUser().getMemberUuid());
        String dir =  System.getProperty("user.dir");
        
        memberDetailJson.setMemberUuid(userDetails.getUser().getMemberUuid());
        memberDetailJson.setMemberNameTh(userDetails.getUser().getMemberNameTh());
        memberDetailJson.setMemberNameEn(userDetails.getUser().getMemberNameEn());
        memberDetailJson.setMemberUsername(userDetails.getUser().getMemberUsername());
        memberDetailJson.setMemberImg(userDetails.getUser().getMemberImg());
        
        memberDetailJson.setUniversityUuid(userDetails.getUser().getUniversity().getUniversityUuid());
        memberDetailJson.setUniversityNameTh(userDetails.getUser().getUniversity().getUniversityNameTh());
        memberDetailJson.setUniversityNameEn(userDetails.getUser().getUniversity().getUniversityNameEn());
        memberDetailJson.setUniversityImg(userDetails.getUser().getUniversity().getUniversityImg());
        
        memberDetailJson.setMemberStatus(userDetails.getUser().getMemberStatus());
        memberDetailJson.setRoleName(userDetails.getUser().getRole().getRoleName());
        if(userDetails.getUser().getRole().getRoleName().equals("ROLE_STUDENT")) {
        	final MemberFaculty memFac = memberService.findMemberFacultyMemberByUuid(userDetails.getUser().getMemberUuid());
        	memberDetailJson.setFacName(memFac.getFaculty().getFacultyNameEn());
        	memberDetailJson.setProName(memFac.getProgram().getProgramNameEn());
        	memberDetailJson.setDegreeName(memFac.getProgram().getDegreeLevel().getName());
        	memberDetailJson.setDegreeNameUid(memFac.getProgram().getDegreeLevel().getDegreeLevelUuid());
        	memberDetailJson.setSId(memFac.getStudentId());
        }
        memberDetailJson.setAccessToken(accessToken);
        memberDetailJson.setRefreshToken(refreshToken);
        
        memberDetailJson.setDir(dir);
        
//        System.out.println();??
        
//        Log log2 = new Log();
//        log2.setMemberName(userDetails.getUser().getMemberUsername());
//        log2.setLogEvent(System.getProperty("user.dir"));	
//        logService.saveLog(log2);
        
        Log log = new Log();
        log.setMemberName(userDetails.getUser().getMemberUsername());
        log.setLogEvent("เข้าสู่ระบบ");	
        logService.saveLog(log);

        return  memberDetailJson;
	}
	
	public MemberDetailJson refreshToken(HttpServletRequest request) throws JwtExceptions {
        MemberDetailJson memberDetailJson = new MemberDetailJson();
        String authToken = request.getHeader("Authorization");
        
        if(authToken == null) {
        	throw JwtExceptions.jwtEmpty();
        }
        
        String[] authTokenTemp = authToken.split(" ");
        
        if(authTokenTemp.length < 2) {
        	throw JwtExceptions.jwtBearerMissing();
        }
        
        authToken = authTokenTemp[1];
        
        if (StringUtils.hasText(authToken)) {;

                String memberUUID = jwtUtilService.extractUserUuid(authToken);
                String accessJwtToken = jwtUtilService.generateToken(memberUUID);
                String refreshJwtToken = jwtUtilService.generateRefreshToken(memberUUID);

                memberDetailJson.setMemberUuid(memberUUID);
                memberDetailJson.setAccessToken(accessJwtToken);
                memberDetailJson.setRefreshToken(refreshJwtToken);
                
                log.info("User UUID:[{}] is refresh JWT Token", memberUUID);
        }

        return memberDetailJson;
	}
}
