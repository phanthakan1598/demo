package com.example.demo.member.business;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.address.service.AddressService;
import com.example.demo.file.constant.TypeImage;
import com.example.demo.file.service.FileStorageService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.MemberEditProfile;
import com.example.demo.member.model.Log;
import com.example.demo.member.model.Member;
import com.example.demo.member.payload.SendMailPayload;
import com.example.demo.member.payload.member.EditProfileMemberPayload;
import com.example.demo.member.payload.member.MemberProfilePasswordPayload;
import com.example.demo.member.service.LogService;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.springEmail.EmailSenderService;
import com.example.demo.university.service.UniversityService;

import lombok.Setter;

@Service
@Setter
public class MemberEditProfileBusiness {
	
	@Autowired
	UniversityService universityService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	@Autowired
	LogService logService;
	
	public void editProfileMember(EditProfileMemberPayload body) throws MemberException {
		Member member = memberService.findMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid());
		
		int memberStatusUpdateUsername = body.getMemberStatusUpdateUsername();
		int memberStatusUpdateEmail = body.getMemberStatusUpdateEmail();
		String memberUsername = body.getMemberUsername();
		String memberNameTh = body.getMemberNameTh();
		String memberNameEn = body.getMemberNameEn();
		LocalDate memberBirthday = body.getMemberBirthday();
		String memberTel = body.getMemberTel();
		String memberEmail = body.getMemberEmail();
		
		
		if(memberStatusUpdateUsername != 0) {
			if(memberUsername == null || memberUsername.equals("")) {
				throw MemberException.memberPasswordEmpty();
			}
			if(memberService.getMemberUsername(memberUsername)) {
				throw MemberException.memberUsernameUsed();
			}
		}
		if(memberStatusUpdateEmail != 0) {
			if(memberEmail == null || memberEmail.equals("")) {
				throw MemberException.memberEmailEmpty();
			}
			if(memberService.getMemberEmail(memberEmail)) {
				throw MemberException.memberEmailUsed();
			}
		}
		if(memberNameTh == null || memberNameTh.equals(" ")) {
			throw MemberException.memberNameThEmpty();
		}
		if(memberNameEn == null || memberNameEn.equals(" ")) {
			throw MemberException.memberNameEnEmpty();
		}
		if(Objects.isNull(memberBirthday)) {
			throw MemberException.memberBirthdayEmpty();
		}
		if(memberTel == null || memberTel.equals("")) {
			throw MemberException.memberTelEmpty();
		}
		try {
			if(memberStatusUpdateEmail != 0) {
				SendMailPayload sendMailPayload = new SendMailPayload();
				sendMailPayload.setMemberEmail(memberEmail);
				sendMailPayload.setSubject("[Enova] กรุณายืนยันตัวตนของคุณ");
				sendMailPayload.setBody("หากต้องการยืนยันตัวตนของคุณเให้คลิกที่ลิงค์ http://localhost:3000/verify/"+body.getMemberEmail());
				emailSenderService.sendEmail(sendMailPayload);	
				
				member.setMemberEmail(memberEmail);
				member.setMemberStatus(0);
			}
			
			if(memberStatusUpdateUsername != 0) {
				member.setMemberUsername(memberUsername);
			}
			member.setMemberNameTh(memberNameTh);
			member.setMemberNameEn(memberNameEn);
			member.setMemberBirthday(memberBirthday);
			member.setMemberTel(memberTel);
			if(body.getPic() != null) {
				MultipartFile pic = body.getPic();
				if (Objects.nonNull(pic.getContentType())) {
					String farmImageName = fileStorageService.storeImageFile(pic, TypeImage.MEMBER);
					member.setMemberImg(farmImageName);
				} 
			}
			memberService.saveMember(member);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("แก้ไขข้อมูลโปรไฟล์");
			logService.saveLog(log);
			
		} catch (Exception e) {
//			
			throw MemberException.memberEmailIncorrect();
		}
		
	}
	
	public void changePassword(MemberProfilePasswordPayload body) throws MemberException, LogException {
		String currentPassword = body.getCurrentPassword();
		String newPassword = body.getNewPassword();
		if(currentPassword == null || currentPassword.equals("")) {
			throw MemberException.userEmptyCurrentPassword();
		}
		if(newPassword == null || newPassword.equals("")) {
			throw MemberException.userEmptyNewPassword();
		}
		
		Member member = memberService.findMemberByUuid(jwtAuth.getCurrentUserUuid());
		if(!passwordEncoder.matches(currentPassword, member.getMemberPassword())) {
			throw MemberException.userIncorrectCurrentPassword();
		}
		member.setMemberPassword(passwordEncoder.encode(newPassword));
		memberService.saveMember(member);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลรหัสผ่าน");
		logService.saveLog(log);
		
	}
	
	public MemberEditProfile viewMemberEditProfile () throws MemberException{
		Member member = memberService.findMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid());
		
		return  MemberEditProfile.packMemberEditProfile(member);
	}
	
}
