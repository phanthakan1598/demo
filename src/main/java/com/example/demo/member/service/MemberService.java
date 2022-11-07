package com.example.demo.member.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberFaculty;
import com.example.demo.member.model.Role;
import com.example.demo.member.repository.MemberFacultyRepository;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.repository.RoleRepository;
import com.example.demo.security.payload.Forgotpassword;
import com.example.demo.security.payload.VerifyPayload;
import com.example.demo.university.model.University;

import lombok.Setter;

@Service
@Setter
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberFacultyRepository memberFacultyRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	public Member findMemberId(long id) throws MemberException {
        return memberRepository.findOneByMemberId(id).orElseThrow(MemberException::userNotFound);
    }
	
	public boolean getMemberEmail(String email){
        return memberRepository.existsByMemberEmail(email);
    }
	
	public boolean getEmailMember(String MemberEmail){
		return memberRepository.existsByMemberEmailAndMemberStatus(MemberEmail,1);
	}
	
	public Member findMemberByUuid(String uuid) throws MemberException {
        return memberRepository.findOneByMemberUuid(uuid).orElseThrow(MemberException::userNotFound);
    }
	
	public MemberFaculty findMemberFacultyMemberByUuid(String uuid) throws MemberException {
        return memberFacultyRepository.findOneByMemberMemberUuid(uuid).orElseThrow(MemberException::userNotFound);
    }
	
	public MemberFaculty findMemberFacultyByUuid(String uuid) throws MemberException {
        return memberFacultyRepository.findOneByMemberFacultyUuid(uuid).orElseThrow(MemberException::userNotFound);
    }
	
	public MemberFaculty findMemberFaculty(Member member) throws MemberException {
        return memberFacultyRepository.findOneByMember(member).orElseThrow(MemberException::userNotFound);
    }
	
	public boolean getMemberUsername(String username) throws MemberException {
        return memberRepository.existsByMemberUsername(username);
    }
	
	public Member saveMember(Member user) throws MemberException {
    	if(Objects.isNull(user)) {
    		throw MemberException.userIsEmptyOrNull();
    	}
		return memberRepository.save(user);
	}
	
	public void delMember(Member member) throws MemberException {
    	if(Objects.isNull(member)) {
    		throw MemberException.userIsEmptyOrNull();
    	}
		memberRepository.delete(member);
	}
	
	public MemberFaculty saveMemberFaculty(MemberFaculty memberFaculty) throws MemberException {
    	if(Objects.isNull(memberFaculty)) {
    		throw MemberException.userIsEmptyOrNull();
    	}
		return memberFacultyRepository.save(memberFaculty);
	}
	
	public Member forgotPassword(Forgotpassword payload) throws MemberException {
		if(Objects.isNull(payload)) {
    		throw MemberException.userIsEmptyOrNull();    		
    	}
		Member member = memberRepository.findOneByMemberEmail(payload.getMemberEmail()).orElseThrow(MemberException::userNotFound);
		
//		System.out.println("member : "+member.getMemberNameEn());
		
		member.setMemberPassword(passwordEncoder.encode(payload.getMemberPassword()));
//		member.setMemberPassword(payload.getMemberPassword());
		
		return memberRepository.save(member);
		
	}
	
	public Member verifyIdentity(VerifyPayload payload) throws MemberException {
		if(Objects.isNull(payload)) {
    		throw MemberException.userIsEmptyOrNull();    		
    	}
		Member member = memberRepository.findOneByMemberEmail(payload.getMemberEmail()).orElseThrow(MemberException::userNotFound);
		member.setMemberStatus(1);
		return memberRepository.save(member);
		
	}
	
	
	
	public int countMember() throws MemberException{
		return memberRepository.countByRoleRoleNameInAndMemberStatusNotAndUniversityUniversityNameEnNot(Arrays.asList("ROLE_ADMIN"),2,"default university");
	}
	
	public List<Member> getAllUniversity(){
		return memberRepository.findByRoleRoleNameInAndMemberStatusNotAndUniversityUniversityNameEnNotOrderByUpdatedAtDesc(Arrays.asList("ROLE_ADMIN"),2,"default university");
	}
	
	public Role getRole(String RoleUuid) throws MemberException{
		return roleRepository.findOneByRoleUuid(RoleUuid).orElseThrow(MemberException::userNotFound);
	}
	
	public List<Member> getAllMemberAdmin(){
		return memberRepository.findByRoleRoleNameInAndMemberStatusNotOrderByUpdatedAtDesc(Arrays.asList("ROLE_ADMIN"),2);
	}
	
	public List<Member> getAllPersonal(University university){
		return memberRepository.findByUniversityAndRoleRoleNameInAndMemberStatusNotOrderByUpdatedAtDesc(university,Arrays.asList("ROLE_PERSONAL"), 2);
	}
	
	public List<Member> getAllCourse(University university){
		return memberRepository.findByUniversityAndRoleRoleNameInAndMemberStatusNotOrderByUpdatedAtDesc(university,Arrays.asList("ROLE_COURSE"), 2);
	}
	
	public List<Member> getAllAcademicGe(University university){
		return memberRepository.findByUniversityAndRoleRoleNameInAndMemberStatusNotOrderByUpdatedAtDesc(university,Arrays.asList("ROLE_ACADEMIC_GE"), 2);
	}
	
	public List<MemberFaculty> getAllAcademicFac(University university){
		return memberFacultyRepository.findByMemberUniversityAndMemberRoleRoleNameInAndMemberMemberStatusNotOrderByMemberUpdatedAtDesc(university,Arrays.asList("ROLE_ACADEMIC_FAC"), 2);
	}
	
	public List<MemberFaculty> getAllTeacher(University university){
		return memberFacultyRepository.findByMemberUniversityAndMemberRoleRoleNameInAndMemberMemberStatusNotOrderByMemberUpdatedAtDesc(university,Arrays.asList("ROLE_TEACHER"), 2);
	}
	
	public List<MemberFaculty> getAllTeacherFaculty(Faculty faculty){
		return memberFacultyRepository.findByFacultyAndMemberRoleRoleNameInAndMemberMemberStatusNotOrderByMemberUpdatedAtDesc(faculty,Arrays.asList("ROLE_TEACHER"), 2);
	}
	
	public List<MemberFaculty> getAllStudent(University university){
		return memberFacultyRepository.findByMemberUniversityAndMemberRoleRoleNameInAndMemberMemberStatusNotOrderByMemberUpdatedAtDesc(university,Arrays.asList("ROLE_STUDENT"), 2);
	}
	
//	public List<Member> getAllAcademicFac(University university){
//		return memberRepository.findByUniversityAndRoleRoleNameInAndMemberStatusNotOrderByUpdatedAtDesc(university,Arrays.asList("ROLE_ACADEMIC_FAC"), 2);
//	}
	
	public List<Member> getAllMember(University university){
		return memberRepository.findByUniversityAndRoleRoleNameNotInAndMemberStatusNotOrderByUpdatedAtDesc(university,Arrays.asList("ROLE_ADMIN","ROLE_PERSONAL","ROLE_HEAD_ADMIN"), 2);
	}
	
	
	public boolean studentNumberBoolean(Faculty faculty){
        return memberFacultyRepository.existsByMemberRoleRoleNameInAndFaculty(Arrays.asList("ROLE_STUDENT"), faculty);
    }
	
	public MemberFaculty findFirstFacultyNumberDesc(Faculty faculty) throws MemberException {
        return memberFacultyRepository.findFirstByMemberRoleRoleNameInAndFacultyOrderByStudentNumberDesc(Arrays.asList("ROLE_STUDENT"), faculty).orElseThrow(MemberException::userNotFound);
    }
}
