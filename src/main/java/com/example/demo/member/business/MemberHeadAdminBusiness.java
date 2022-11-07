package com.example.demo.member.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.address.exception.AddressException;
import com.example.demo.address.model.Address;
import com.example.demo.address.model.Tambon;
import com.example.demo.address.service.AddressService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.LogJson;
import com.example.demo.member.json.MemberUniversityRegionCount;
import com.example.demo.member.json.UniversityAllJson;
import com.example.demo.member.json.admin.MemberAdminAllJson;
import com.example.demo.member.json.admin.MemberAdminJson;
import com.example.demo.member.model.Log;
import com.example.demo.member.model.Member;
import com.example.demo.member.model.Role;
import com.example.demo.member.payload.SendMailPayload;
import com.example.demo.member.payload.memberAdmin.MemberAdminInsertPayload;
import com.example.demo.member.payload.memberAdmin.MemberAdminUpdatePayload;
import com.example.demo.member.service.LogService;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.springEmail.EmailSenderService;
import com.example.demo.university.exception.UniversityException;
import com.example.demo.university.model.University;
import com.example.demo.university.service.UniversityService;

import lombok.Setter;

@Service
@Setter
public class MemberHeadAdminBusiness {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	UniversityService universityService;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	LogService logService;
	
	public List<LogJson> viewAllLog (){
		return  LogJson.logJsons(logService.logs());
	}
	
	public List<MemberAdminAllJson> viewAllMemberAdmin (){
		return  MemberAdminAllJson.packMemberAdminAllJson(memberService.getAllMemberAdmin());
	}
	
	public List<UniversityAllJson> viewAllListUniversity(){
		return  UniversityAllJson.packUniversityAllJson(memberService.getAllUniversity());
	}
	
	public void insertMemberAdmin(MemberAdminInsertPayload body) throws MemberException, AddressException{
		String memberUsername = body.getMemberUsername();
		String memberPassword = body.getMemberPassword();
		String memberNameTh = body.getMemberNameTh();
		String memberNameEn = body.getMemberNameEn();
		LocalDate memberBirthday = body.getMemberBirthday();
		String memberTel = body.getMemberTel();
		String memberEmail = body.getMemberEmail();
		String addressDetailTh = body.getAddressDetailTh();
		String addressDetailEn = body.getAddressDetailEn();
		Tambon tambon = addressService.getTambon(body.getTambonUuid());
		
		List<Member> memberAdmins = memberService.getAllMemberAdmin();
		if(memberAdmins.size() == 99) {
			throw MemberException.memberAdminOver();
		}
		
		if(memberUsername == null || memberUsername.equals("")) {
			throw MemberException.memberUsernameEmpty();
		}
		if(memberPassword == null || memberPassword.equals("")) {
			throw MemberException.memberPasswordEmpty();
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
		if(memberEmail == null || memberEmail.equals("")) {
			throw MemberException.memberEmailEmpty();
		}
		if(addressDetailTh == null || addressDetailTh.equals("")) {
			throw MemberException.addressDetailThEmpty();
		}
		if(addressDetailEn == null || addressDetailEn.equals("")) {
			throw MemberException.addressDetailEnEmpty();
		}
		if(memberService.getMemberUsername(memberUsername)) {
			throw MemberException.memberUsernameUsed();
		}
		if(memberService.getMemberEmail(memberEmail)) {
			throw MemberException.memberEmailUsed();
		}
		if(memberTel.length() != 10) {
			throw MemberException.memberTelIncorrect();
		}
		try {
			SendMailPayload sendMailPayload = new SendMailPayload();
			sendMailPayload.setMemberEmail(memberEmail);
			sendMailPayload.setSubject("[Enova] กรุณายืนยันตัวตนของคุณ");
			sendMailPayload.setBody("หากต้องการยืนยันตัวตนของคุณเให้คลิกที่ลิงค์ http://localhost:3000/verify/"+body.getMemberEmail());
	
			emailSenderService.sendEmail(sendMailPayload);	
//			
			Address address = new Address();
			address.setAddressDetailTh(addressDetailTh);
			address.setAddressDetailEn(addressDetailEn);
			address.setTambon(tambon);
			addressService.saveAddress(address);
			
			University university = jwtAuth.getCurrentUser().getUniversity();
			
			Role role =  memberService.getRole("aa0ae5c2-fda7-11ec-917a-74d43585fa1b");
			
			Member member = new Member();
			member.setMemberUsername(memberUsername);
			member.setMemberPassword(passwordEncoder.encode(memberPassword));
			member.setMemberNameTh(memberNameTh);
			member.setMemberNameEn(memberNameEn);
			member.setMemberBirthday(memberBirthday);
			member.setMemberTel(memberTel);
			member.setMemberEmail(memberEmail);
			member.setMemberStatus(0);
			member.setAddress(address);
			member.setUniversity(university);
			member.setRole(role);
			member.setMemberImg("user.png");
			memberService.saveMember(member);
			
			address.setMember(member);
			addressService.saveAddress(address);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("เพิ่มข้อมูลผู้ใช้แอดมิน");
			logService.saveLog(log);
			
			
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
		
		
	}
	
	public void updateMemberAdmin(MemberAdminUpdatePayload body) throws MemberException, AddressException{
		Member member = memberService.findMemberByUuid(body.getMemberUuid());
		
		int memberStatusUpdateUsername = body.getMemberStatusUpdateUsername();
		int memberStatusUpdatePassword = body.getMemberStatusUpdatePassword();
		int memberStatusUpdateEmail = body.getMemberStatusUpdateEmail();
		
		String memberUsername = body.getMemberUsername();
		String memberPassword = body.getMemberPassword();
		String memberNameTh = body.getMemberNameTh();
		String memberNameEn = body.getMemberNameEn();
		LocalDate memberBirthday = body.getMemberBirthday();
		String memberTel = body.getMemberTel();
		String memberEmail = body.getMemberEmail();
		String addressDetailTh = body.getAddressDetailTh();
		String addressDetailEn = body.getAddressDetailEn();
		Tambon tambon = addressService.getTambon(body.getTambonUuid());
		Address address = addressService.getAddress(body.getAddressUuid());
		
		
		if(memberStatusUpdateUsername != 0) {
			if(memberUsername == null || memberUsername.equals("")) {
				throw MemberException.memberPasswordEmpty();
			}
			if(memberService.getMemberUsername(memberUsername)) {
				throw MemberException.memberUsernameUsed();
			}
		}
		if(memberStatusUpdatePassword != 0) {
			if(memberPassword == null || memberPassword.equals("")) {
				throw MemberException.memberPasswordEmpty();
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
		
		if(addressDetailTh == null || addressDetailTh.equals("")) {
			throw MemberException.addressDetailThEmpty();
		}
		if(addressDetailEn == null || addressDetailEn.equals("")) {
			throw MemberException.addressDetailEnEmpty();
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
			
			address.setAddressDetailTh(addressDetailTh);
			address.setAddressDetailEn(addressDetailEn);
			address.setTambon(tambon);
			
			addressService.saveAddress(address);
			
			if(memberStatusUpdateUsername != 0) {
				member.setMemberUsername(memberUsername);
			}
			if(memberStatusUpdatePassword != 0) {
				member.setMemberPassword(passwordEncoder.encode(memberPassword));
			}
			member.setMemberNameTh(memberNameTh);
			member.setMemberNameEn(memberNameEn);
			member.setMemberBirthday(memberBirthday);
			member.setMemberTel(memberTel);
			member.setAddress(address);
			
			memberService.saveMember(member);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("แก้ไขข้อมูลผู้ใช้แอดมิน");
			logService.saveLog(log);
			
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void deleteMemberAdmin(String uid) throws MemberException, UniversityException, LogException{
		Member member = memberService.findMemberByUuid(uid);
		if(!member.getRole().getRoleName().equals("ROLE_ADMIN")) {
			throw MemberException.memberIncorrect();
		}
		
		if(!member.getUniversity().getUniversityNameEn().equals("default university")) {
			University university = universityService.findUniversityByUuid(member.getUniversity().getUniversityUuid());
			universityService.delUniversity(university);
		}
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลผู้ใช้แอดมิน");
		logService.saveLog(log);
		
		memberService.delMember(member);
	}
	
	public MemberAdminJson viewMemberAdmin (String uid) throws MemberException {
		Member member = memberService.findMemberByUuid(uid);
		if(member == null || member.getMemberStatus() == 2) {
			throw MemberException.userNotFound();
		}
		if(!member.getRole().getRoleName().equals("ROLE_ADMIN")) {
			throw MemberException.memberIncorrect();
		}
		
		return  MemberAdminJson.packMemberAdminJson(member);
	}
	
	public int countMemberUniversity() throws MemberException {
		return memberService.countMember();
	}
	
	public Map<String, Long> viewAllUniversity (){
		return MemberUniversityRegionCount.packMemberUniversityRegionCount(memberService.getAllUniversity())
				.stream().map(r -> r.getUniversityRegionName()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	}
	
	
}
