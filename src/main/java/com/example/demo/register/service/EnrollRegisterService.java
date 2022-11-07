package com.example.demo.register.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.model.Member;
import com.example.demo.register.exception.EnrollRegisterException;
import com.example.demo.register.model.Day;
import com.example.demo.register.model.EnrollRegister;
import com.example.demo.register.repository.EnrollRegisterRepository;
import com.example.demo.university.model.Term;

import lombok.Setter;

@Service
@Setter
public class EnrollRegisterService {
	@Autowired
	EnrollRegisterRepository enrollRegisterRepository;
	
	public EnrollRegister findEnrollRegister(String uuid) throws EnrollRegisterException {
        return enrollRegisterRepository.findOneByEnrollRegisterUuid(uuid).orElseThrow(EnrollRegisterException::enrollRegisterNotFound);
    }
	
	public EnrollRegister saveEnrollRegister(EnrollRegister enrollRegister) throws EnrollRegisterException {
    	if(Objects.isNull(enrollRegister)) {
    		throw EnrollRegisterException.enrollRegisterIsEmptyOrNull();
    	}
		return enrollRegisterRepository.save(enrollRegister);
	}
	
	public void delEnrollRegister(EnrollRegister enrollRegister) throws EnrollRegisterException {
    	if(Objects.isNull(enrollRegister)) {
    		throw EnrollRegisterException.enrollRegisterIsEmptyOrNull();
    	}
    	enrollRegisterRepository.delete(enrollRegister);
	}
	
	public Boolean booleanEnrollRegister(Member member ,Term term ) {
		return enrollRegisterRepository.existsByMemberAndStudyRegisterTerm(member, term);
	}
	
	public Boolean booleanEnrollRegisterMember(Member member ) {
		return enrollRegisterRepository.existsByMember(member);
	}
	
	public Boolean booleanEnrollRegisterTimeBegin(Member member ,Term term,Day day, LocalTime begin,LocalTime end) {
		return enrollRegisterRepository.existsByMemberAndStudyRegisterTermAndStudyRegisterDayAndStudyRegisterTimeBeginBetween(member, term, day, begin, end);
	}
	
	public Boolean booleanEnrollRegisterTimeEnd(Member member ,Term term,Day day, LocalTime begin,LocalTime end) {
		return enrollRegisterRepository.existsByMemberAndStudyRegisterTermAndStudyRegisterDayAndStudyRegisterTimeEndBetween(member, term, day, begin, end);
	}
	
	public List<EnrollRegister> enrollRegistersTable (Member member , Term term){
		return enrollRegisterRepository.findByMemberAndStudyRegisterTermAndEnrollRegisterStatus(member, term,1);
	}
	
	public List<EnrollRegister> enrollRegisters (Member member , Term term){
		return enrollRegisterRepository.findByMemberAndStudyRegisterTerm(member, term);
	}
	
	public List<EnrollRegister> enrollRegisterMembers (Member member ){
		return enrollRegisterRepository.findByMember(member);
	}
	
	public List<EnrollRegister> enrollRegisterMemberStatus (Member member ){
		return enrollRegisterRepository.findByMemberAndEnrollRegisterStatus(member,1);
	}
}
