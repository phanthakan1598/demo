package com.example.demo.springEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.member.payload.SendMailPayload;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(SendMailPayload payload) {
			SimpleMailMessage message= new SimpleMailMessage();
			message.setFrom("enova.reg123@gmail.com");
			message.setTo(payload.getMemberEmail());
			message.setText(payload.getBody());
			message.setSubject(payload.getSubject());
			
			mailSender.send(message);
	}

	
}
