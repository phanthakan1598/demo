package com.example.demo.register.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.model.Member;
import com.example.demo.register.exception.StudyRegisterMemberException;
import com.example.demo.register.model.Day;
import com.example.demo.register.model.StudyRegister;
import com.example.demo.register.model.StudyRegisterMember;
import com.example.demo.register.repository.StudyRegisterMemberRepository;
import com.example.demo.university.model.Term;

import lombok.Setter;

@Service
@Setter
public class StudyRegisterMemberService {
	@Autowired
	StudyRegisterMemberRepository studyRegisterMemberRepository;
	
	public StudyRegisterMember findStudyRegisterMemberUuid(String uuid) throws StudyRegisterMemberException {
        return studyRegisterMemberRepository.findOneByStudyRegisterMemberUuid(uuid).orElseThrow(StudyRegisterMemberException::studyRegisterMemberNotFound);
    }
	
	public StudyRegisterMember saveStudyRegisterMember(StudyRegisterMember studyRegister) throws StudyRegisterMemberException {
    	if(Objects.isNull(studyRegister)) {
    		throw StudyRegisterMemberException.studyRegisterMemberIsEmptyOrNull();
    	}
		return studyRegisterMemberRepository.save(studyRegister);
	}
	
	public void delStudyRegisterMember(StudyRegisterMember studyRegister) throws StudyRegisterMemberException {
    	if(Objects.isNull(studyRegister)) {
    		throw StudyRegisterMemberException.studyRegisterMemberIsEmptyOrNull();
    	}
    	studyRegisterMemberRepository.delete(studyRegister);
	}
	
	public Boolean booleanMemberUsed(StudyRegister studyRegister,Member member) {
		return studyRegisterMemberRepository.existsByStudyRegisterAndMember(studyRegister, member);
	}
	
	public Boolean booleanMemberDateTimeBeginUsed(Term term,Member member,Day day,LocalTime begin,LocalTime end) {
		return studyRegisterMemberRepository.existsByStudyRegisterTermAndMemberAndStudyRegisterDayAndStudyRegisterTimeBeginBetween(term, member, day, begin, end);
	}
	
	public Boolean booleanMemberDateTimeEndUsed(Term term,Member member,Day day,LocalTime begin,LocalTime end) {
		return studyRegisterMemberRepository.existsByStudyRegisterTermAndMemberAndStudyRegisterDayAndStudyRegisterTimeEndBetween(term, member, day, begin, end);
	}
	
	public List<StudyRegisterMember> studyRegisterTermMember(Term term , Member member){
		return studyRegisterMemberRepository.findByStudyRegisterTermAndMember(term, member);
	}
	
	public List<StudyRegisterMember> studyRegisterMembers (StudyRegister studyRegister){
		return studyRegisterMemberRepository.findByStudyRegister(studyRegister);
	}
	
}
