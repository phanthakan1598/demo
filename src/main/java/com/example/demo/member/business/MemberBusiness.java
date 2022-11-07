package com.example.demo.member.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;

import lombok.Setter;

@Service
@Setter
public class MemberBusiness {
	
	@Autowired
	JWTAuth jwtAuth;

	@Autowired
	LogService logService;
	
	public void logout () throws LogException {
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ออกจากระบบ");
		logService.saveLog(log);

	}
	
}
