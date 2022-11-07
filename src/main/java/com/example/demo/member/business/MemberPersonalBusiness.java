package com.example.demo.member.business;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.address.exception.AddressException;
import com.example.demo.address.model.Address;
import com.example.demo.address.model.Tambon;
import com.example.demo.address.service.AddressService;
import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.faculty.service.FacultyService;
import com.example.demo.faculty.service.ProgramService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.academicFac.MemberAcademicFacAllJson;
import com.example.demo.member.json.academicFac.MemberAcademicFacJson;
import com.example.demo.member.json.academicGe.MemberAcademicGeAllJson;
import com.example.demo.member.json.academicGe.MemberAcademicGeJson;
import com.example.demo.member.json.course.MemberCourseAllJson;
import com.example.demo.member.json.course.MemberCourseJson;
import com.example.demo.member.json.student.MemberStudentAllJson;
import com.example.demo.member.json.student.MemberStudentJson;
import com.example.demo.member.json.teacher.MemberTeacherAllJson;
import com.example.demo.member.json.teacher.MemberTeacherJson;
import com.example.demo.member.model.Log;
import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberFaculty;
import com.example.demo.member.model.Role;
import com.example.demo.member.payload.SendMailPayload;
import com.example.demo.member.payload.memberAcademicFac.MemberAcademicFacInsertPayload;
import com.example.demo.member.payload.memberAcademicFac.MemberAcademicFacUpdatePayload;
import com.example.demo.member.payload.memberAcademicGe.MemberAcademicGeInsertPayload;
import com.example.demo.member.payload.memberAcademicGe.MemberAcademicGeUpdatePayload;
import com.example.demo.member.payload.memberCourse.MemberCourseInsertPayload;
import com.example.demo.member.payload.memberCourse.MemberCourseUpdatePayload;
import com.example.demo.member.payload.memberStudent.MemberStudentCsvInsertPayload;
import com.example.demo.member.payload.memberStudent.MemberStudentInsertPayload;
import com.example.demo.member.payload.memberStudent.MemberStudentUpdatePayload;
import com.example.demo.member.payload.memberTeacher.MemberTeacherInsertPayload;
import com.example.demo.member.payload.memberTeacher.MemberTeacherUpdatePayload;
import com.example.demo.member.service.LogService;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.springEmail.EmailSenderService;
import com.example.demo.university.model.University;

import lombok.Setter;

@Service
@Setter
public class MemberPersonalBusiness {
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ProgramService programService;
	
	@Autowired
	LogService logService;
	
	public List<MemberCourseAllJson> viewAllMemberCourse(){
		University university =  jwtAuth.getCurrentUser().getUniversity();
		return  MemberCourseAllJson.packMemberCourseAllJson(memberService.getAllCourse(university));
	}
	
	public MemberCourseJson viewMemberCourse (String uid) throws MemberException {
		Member member = memberService.findMemberByUuid(uid);
		if(member == null || member.getMemberStatus() == 2) {
			throw MemberException.userNotFound();
		}
		
		if(!member.getUniversity().getUniversityNameEn().equals(jwtAuth.getCurrentUser().getUniversity().getUniversityNameEn())) {
			throw MemberException.memberIncorrect();
		}
		
		if(!member.getRole().getRoleName().equals("ROLE_COURSE")) {
			throw MemberException.memberIncorrect();
		}
			
		return  MemberCourseJson.packMemberCourseJson(member);
	}
	
	public void insertMemberCourse(MemberCourseInsertPayload body) throws MemberException, AddressException{
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
			
			Address address = new Address();
			address.setAddressDetailTh(addressDetailTh);
			address.setAddressDetailEn(addressDetailEn);
			address.setTambon(tambon);
			addressService.saveAddress(address);
			
			Role role =  memberService.getRole("91d1d07b-04d0-11ed-a17a-0492261420bd");
			
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
			member.setUniversity(jwtAuth.getCurrentUser().getUniversity());
			member.setRole(role);
			member.setMemberImg("user.png");
			memberService.saveMember(member);
			
			address.setMember(member);
			addressService.saveAddress(address);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("เพิ่มข้อมูลผู้ใช้หน่วยงานหลักสูตร");
			logService.saveLog(log);
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void updateMemberCourse (MemberCourseUpdatePayload body) throws MemberException, AddressException{
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
		if(memberTel.length() != 10) {
			throw MemberException.memberTelIncorrect();
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
			log.setLogEvent("แก้ไขข้อมูลผู้ใช้หน่วยงานหลักสูตร");
			logService.saveLog(log);
			
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void deleteMemberCourse(String uid) throws MemberException, LogException{
		Member member = memberService.findMemberByUuid(uid);
		if(!member.getRole().getRoleName().equals("ROLE_COURSE")) {
			throw MemberException.memberIncorrect();
		}
		memberService.delMember(member);

		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลผู้ใช้หน่วยงานหลักสูตร");
		logService.saveLog(log);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	
	public List<MemberAcademicGeAllJson> viewAllMemberAcademicGe(){
		University university =  jwtAuth.getCurrentUser().getUniversity();
		return  MemberAcademicGeAllJson.packMemberAcademicGeAllJson(memberService.getAllAcademicGe(university));
	}
	
	public MemberAcademicGeJson viewMemberAcademicGe (String uid) throws MemberException {
		Member member = memberService.findMemberByUuid(uid);
		if(member == null || member.getMemberStatus() == 2) {
			throw MemberException.userNotFound();
		}
		
		if(!member.getUniversity().getUniversityNameEn().equals(jwtAuth.getCurrentUser().getUniversity().getUniversityNameEn())) {
			throw MemberException.memberIncorrect();
		}
		
		if(!member.getRole().getRoleName().equals("ROLE_ACADEMIC_GE")) {
			throw MemberException.memberIncorrect();
		}
			
		return  MemberAcademicGeJson.packMemberAcademicGeJson(member);
	}
	
	public void insertMemberAcademicGe(MemberAcademicGeInsertPayload body) throws MemberException, AddressException{
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
			
			Address address = new Address();
			address.setAddressDetailTh(addressDetailTh);
			address.setAddressDetailEn(addressDetailEn);
			address.setTambon(tambon);
			addressService.saveAddress(address);
			
			Role role =  memberService.getRole("7800c967-04d0-11ed-a17a-0492261420bd");
			
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
			member.setUniversity(jwtAuth.getCurrentUser().getUniversity());
			member.setRole(role);
			member.setMemberImg("user.png");
			memberService.saveMember(member);
			
			address.setMember(member);
			addressService.saveAddress(address);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("เพิ่มข้อมูลผู้ใช้หน่วยงานวิชาการศึกษาทั่วไป");
			logService.saveLog(log);
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void updateMemberAcademicGe (MemberAcademicGeUpdatePayload body) throws MemberException, AddressException{
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
		if(memberTel.length() != 10) {
			throw MemberException.memberTelIncorrect();
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
			log.setLogEvent("แก้ไขข้อมูลผู้ใช้หน่วยงานวิชาการศึกษาทั่วไป");
			logService.saveLog(log);
			
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void deleteMemberAcademicGe(String uid) throws MemberException, LogException{
		Member member = memberService.findMemberByUuid(uid);
		if(!member.getRole().getRoleName().equals("ROLE_ACADEMIC_GE")) {
			throw MemberException.memberIncorrect();
		}
		memberService.delMember(member);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลผู้ใช้หน่วยงานวิชาการศึกษาทั่วไป");
		logService.saveLog(log);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	
	public List<MemberAcademicFacAllJson> viewAllMemberAcademicFac(){
		University university =  jwtAuth.getCurrentUser().getUniversity();
		return  MemberAcademicFacAllJson.packMemberAcademicFacAllJson(memberService.getAllAcademicFac(university));
	}
	
	public MemberAcademicFacJson viewMemberAcademicFac (String uid) throws MemberException {
		MemberFaculty memberFaculty = memberService.findMemberFacultyMemberByUuid(uid);
		if(memberFaculty == null || memberFaculty.getMember().getMemberStatus() == 2) {
			throw MemberException.userNotFound();
		}
		
		if(!memberFaculty.getMember().getUniversity().getUniversityNameEn().equals(jwtAuth.getCurrentUser().getUniversity().getUniversityNameEn())) {
			throw MemberException.memberIncorrect();
		}
		
		if(!memberFaculty.getMember().getRole().getRoleName().equals("ROLE_ACADEMIC_FAC")) {
			throw MemberException.memberIncorrect();
		}
			
		return  MemberAcademicFacJson.packMemberAcademicFacJson(memberFaculty);
	}
	
	public void insertMemberAcademicFac(MemberAcademicFacInsertPayload body) throws MemberException, AddressException, FacultyException{
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
		Faculty faculty = facultyService.findFacultyByUuid(body.getFacultyUuid());
		
		
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
			
			Address address = new Address();
			address.setAddressDetailTh(addressDetailTh);
			address.setAddressDetailEn(addressDetailEn);
			address.setTambon(tambon);
			addressService.saveAddress(address);
			
			Role role =  memberService.getRole("82ab8785-04d0-11ed-a17a-0492261420bd");
			
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
			member.setUniversity(jwtAuth.getCurrentUser().getUniversity());
			member.setRole(role);
			member.setMemberImg("user.png");
			memberService.saveMember(member);
			
			MemberFaculty memberFaculty = new MemberFaculty();
			memberFaculty.setFaculty(faculty);
			memberFaculty.setMember(member);
			memberService.saveMemberFaculty(memberFaculty);
			
			address.setMember(member);
			addressService.saveAddress(address);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("เพิ่มข้อมูลผู้ใช้หน่วยงานวิชาการคณะ");
			logService.saveLog(log);
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void updateMemberAcademicFac (MemberAcademicFacUpdatePayload body) throws MemberException, AddressException, FacultyException{
		Member member = memberService.findMemberByUuid(body.getMemberUuid());
		MemberFaculty memberFaculty = memberService.findMemberFacultyMemberByUuid(member.getMemberUuid());
		
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
		Faculty faculty = facultyService.findFacultyByUuid(body.getFacultyUuid());
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
		if(memberTel.length() != 10) {
			throw MemberException.memberTelIncorrect();
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
			
			memberFaculty.setFaculty(faculty);
			memberFaculty.setMember(member);
			memberService.saveMemberFaculty(memberFaculty);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("แก้ไขข้อมูลผู้ใช้หน่วยงานวิชาการคณะ");
			logService.saveLog(log);
			
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void deleteMemberAcademicFac(String uid) throws MemberException, LogException{
		Member member = memberService.findMemberByUuid(uid);
		if(!member.getRole().getRoleName().equals("ROLE_ACADEMIC_FAC")) {
			throw MemberException.memberIncorrect();
		}
		memberService.delMember(member);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลผู้ใช้หน่วยงานวิชาการคณะ");
		logService.saveLog(log);
	}
	
//---------------------------------------------------------------------------------------------------------------------------

	public List<MemberTeacherAllJson> viewAllMemberTeacher(){
		University university =  jwtAuth.getCurrentUser().getUniversity();
		return  MemberTeacherAllJson.packMemberTeacherAllJson(memberService.getAllTeacher(university));
	}
	
	public MemberTeacherJson viewMemberTeacher (String uid) throws MemberException {
		MemberFaculty memberFaculty = memberService.findMemberFacultyMemberByUuid(uid);
		if(memberFaculty == null || memberFaculty.getMember().getMemberStatus() == 2) {
			throw MemberException.userNotFound();
		}
		
		if(!memberFaculty.getMember().getUniversity().getUniversityNameEn().equals(jwtAuth.getCurrentUser().getUniversity().getUniversityNameEn())) {
			throw MemberException.memberIncorrect();
		}
		
		if(!memberFaculty.getMember().getRole().getRoleName().equals("ROLE_TEACHER")) {
			throw MemberException.memberIncorrect();
		}
			
		return  MemberTeacherJson.packMemberTeacherJson(memberFaculty);
	}
	
	public void insertMemberTeacher(MemberTeacherInsertPayload body) throws MemberException, AddressException, FacultyException, ProgramException{
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
		Faculty faculty = facultyService.findFacultyByUuid(body.getFacultyUuid());
		
		
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
			
			Address address = new Address();
			address.setAddressDetailTh(addressDetailTh);
			address.setAddressDetailEn(addressDetailEn);
			address.setTambon(tambon);
			addressService.saveAddress(address);
			
			Role role =  memberService.getRole("87cd19d6-04d0-11ed-a17a-0492261420bd");
			
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
			member.setUniversity(jwtAuth.getCurrentUser().getUniversity());
			member.setRole(role);
			member.setMemberImg("user.png");
			memberService.saveMember(member);
			
			MemberFaculty memberFaculty = new MemberFaculty();
			memberFaculty.setFaculty(faculty);
			memberFaculty.setMember(member);
			memberService.saveMemberFaculty(memberFaculty);
			
			address.setMember(member);
			addressService.saveAddress(address);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("เพิ่มข้อมูลผู้ใช้อาจารย์");
			logService.saveLog(log);
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void updateMemberTeacher (MemberTeacherUpdatePayload body) throws MemberException, AddressException, FacultyException, ProgramException{
		Member member = memberService.findMemberByUuid(body.getMemberUuid());
		MemberFaculty memberFaculty = memberService.findMemberFacultyMemberByUuid(member.getMemberUuid());
		
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
		Faculty faculty = facultyService.findFacultyByUuid(body.getFacultyUuid());
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
		if(memberTel.length() != 10) {
			throw MemberException.memberTelIncorrect();
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
			
			memberFaculty.setFaculty(faculty);
			memberFaculty.setMember(member);
			memberService.saveMemberFaculty(memberFaculty);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("แก้ไขข้อมูลผู้ใช้อาจารย์");
			logService.saveLog(log);
			
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void deleteMemberTeacher(String uid) throws MemberException, LogException{
		Member member = memberService.findMemberByUuid(uid);
		if(!member.getRole().getRoleName().equals("ROLE_TEACHER")) {
			throw MemberException.memberIncorrect();
		}
		memberService.delMember(member);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลผู้ใช้อาจารย์");
		logService.saveLog(log);
	}
	
//---------------------------------------------------------------------------------------------------------------------------
	public void insertMemberStudentCsv(MemberStudentCsvInsertPayload body) {
		
	}
	
	
	public List<MemberStudentAllJson> viewAllMemberStudent(){
		University university =  jwtAuth.getCurrentUser().getUniversity();
		return  MemberStudentAllJson.packMemberStudentAllJson(memberService.getAllStudent(university));
	}
	
	public MemberStudentJson viewMemberStudent (String uid) throws MemberException {
		MemberFaculty memberFaculty = memberService.findMemberFacultyMemberByUuid(uid);
		if(memberFaculty == null || memberFaculty.getMember().getMemberStatus() == 2) {
			throw MemberException.userNotFound();
		}
		
		if(!memberFaculty.getMember().getUniversity().getUniversityNameEn().equals(jwtAuth.getCurrentUser().getUniversity().getUniversityNameEn())) {
			throw MemberException.memberIncorrect();
		}
		
		if(!memberFaculty.getMember().getRole().getRoleName().equals("ROLE_STUDENT")) {
			throw MemberException.memberIncorrect();
		}
			
		return  MemberStudentJson.packMemberStudentJson(memberFaculty);
	}
	
	public void insertMemberStudent(MemberStudentInsertPayload body) throws MemberException, AddressException, FacultyException, ProgramException{
//		System.out.println("Hi");
//		String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)).substring(2);
		
		
		
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
		Faculty faculty = facultyService.findFacultyByUuid(body.getFacultyUuid());
		Program program = programService.findProgramByUuid(body.getProgramUuid());
		
		
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
		
		String studentNumber = null;
		String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)).substring(2);
		String universityNumber = jwtAuth.getCurrentUser().getUniversity().getUniversityNumber();
		String facNumber = faculty.getFacultyNumber();
		String degreeNumber = program.getDegreeLevel().getDegreeLevelNumber();
		
		if(memberService.studentNumberBoolean(faculty)) {
			int studentMaxNum = Integer.parseInt(memberService.findFirstFacultyNumberDesc(faculty).getStudentNumber());
			studentNumber = Integer.toString(studentMaxNum + 1);
		}else {
			studentNumber = "0";
		}
		
		if (Integer.parseInt(studentNumber) > 9999) {
			throw MemberException.studentOver();
		}
		
		if (studentNumber.length() < 2) {
			studentNumber = "000" +studentNumber;
		}else if (studentNumber.length() < 3) {
			studentNumber = "00" +studentNumber;
		}else if (studentNumber.length() < 4) {
			studentNumber = "0" +studentNumber;
		}
		String sId = year + universityNumber + facNumber + degreeNumber + studentNumber;
		
		try {
			SendMailPayload sendMailPayload = new SendMailPayload();
			sendMailPayload.setMemberEmail(memberEmail);
			sendMailPayload.setSubject("[Enova] กรุณายืนยันตัวตนของคุณ");
			sendMailPayload.setBody("หากต้องการยืนยันตัวตนของคุณเให้คลิกที่ลิงค์ http://localhost:3000/verify/"+body.getMemberEmail());
	
			emailSenderService.sendEmail(sendMailPayload);	
			
			Address address = new Address();
			address.setAddressDetailTh(addressDetailTh);
			address.setAddressDetailEn(addressDetailEn);
			address.setTambon(tambon);
			addressService.saveAddress(address);
			
			Role role =  memberService.getRole("d17ea4a5-0445-11ed-8e7d-0492261420bd");
			
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
			member.setUniversity(jwtAuth.getCurrentUser().getUniversity());
			member.setRole(role);
			member.setMemberImg("user.png");
			memberService.saveMember(member);
			
			MemberFaculty memberFaculty = new MemberFaculty();
			memberFaculty.setFaculty(faculty);
			memberFaculty.setMember(member);
			memberFaculty.setProgram(program);
			memberFaculty.setStudentId(sId);
			memberFaculty.setStudentNumber(studentNumber);
			memberService.saveMemberFaculty(memberFaculty);
			
			address.setMember(member);
			addressService.saveAddress(address);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("เพิ่มข้อมูลผู้ใช้นิสิต");
			logService.saveLog(log);
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void updateMemberStudent (MemberStudentUpdatePayload body) throws MemberException, AddressException, FacultyException, ProgramException{
		Member member = memberService.findMemberByUuid(body.getMemberUuid());
		MemberFaculty memberFaculty = memberService.findMemberFacultyMemberByUuid(member.getMemberUuid());
		
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
		Faculty faculty = facultyService.findFacultyByUuid(body.getFacultyUuid());
		Program program = programService.findProgramByUuid(body.getProgramUuid());
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
		if(memberTel.length() != 10) {
			throw MemberException.memberTelIncorrect();
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
			
			memberFaculty.setFaculty(faculty);
			memberFaculty.setMember(member);
			memberFaculty.setProgram(program);
			memberService.saveMemberFaculty(memberFaculty);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("แก้ไขข้อมูลผู้ใช้นิสิต");
			logService.saveLog(log);
			
		} catch (Exception e) {
			throw MemberException.memberEmailIncorrect();
		}
	}
	
	public void deleteMemberStudent(String uid) throws MemberException, LogException{
		Member member = memberService.findMemberByUuid(uid);
		if(!member.getRole().getRoleName().equals("ROLE_STUDENT")) {
			throw MemberException.memberIncorrect();
		}
		memberService.delMember(member);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลผู้ใช้นิสิต");
		logService.saveLog(log);
	}

	
}
