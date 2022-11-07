package com.example.demo.register.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.building.model.Room;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.register.exception.StudyRegisterException;
import com.example.demo.register.model.Day;
import com.example.demo.register.model.StudyRegister;
import com.example.demo.register.repository.StudyRegisterRepository;
import com.example.demo.subject.model.Subject;
import com.example.demo.university.model.Term;

import lombok.Setter;

@Service
@Setter
public class StudyRegisterService {
	@Autowired
	StudyRegisterRepository studyRegisterRepository;
	
	public StudyRegister findStudyRegisterUuid(String uuid) throws StudyRegisterException {
        return studyRegisterRepository.findOneByStudyRegisterUuid(uuid).orElseThrow(StudyRegisterException::studyRegisterNotFound);
    }
	
	public StudyRegister findMaxSecSubject(Term term ,Subject subject,String type)throws StudyRegisterException {
        return studyRegisterRepository.findFirstByTermAndAndSubjectAndTypeOrderByStudyRegisterIdDesc(term, subject, type).orElseThrow(StudyRegisterException::studyRegisterNotFound);
    }
	
	public StudyRegister saveStudyRegister(StudyRegister studyRegister) throws StudyRegisterException {
    	if(Objects.isNull(studyRegister)) {
    		throw StudyRegisterException.studyRegisterIsEmptyOrNull();
    	}
		return studyRegisterRepository.save(studyRegister);
	}
	
	public void delStudyRegister(StudyRegister studyRegister) throws StudyRegisterException {
    	if(Objects.isNull(studyRegister)) {
    		throw StudyRegisterException.studyRegisterIsEmptyOrNull();
    	}
    	studyRegisterRepository.delete(studyRegister);
	}
	
	public Boolean booleanRoomDateTimeBeginUsed(Term term,Room room,Day day,LocalTime begin,LocalTime end) {
		return studyRegisterRepository.existsByTermAndRoomAndDayAndTimeBeginBetween(term, room, day, begin, end);
	}
	
	public Boolean booleanRoomDateTimeEndUsed(Term term,Room room,Day day,LocalTime begin,LocalTime end) {
		return studyRegisterRepository.existsByTermAndRoomAndDayAndTimeEndBetween(term, room, day, begin, end);
	}
	

//	public Boolean booleanMemberDateTimeBeginUsed(Term term,Member member,Day day,LocalTime begin,LocalTime end) {
//		return studyRegisterRepository.existsByTermAndMemberAndDayAndTimeBeginBetween(term, member, day, begin, end);
//	}
//	
//	public Boolean booleanMemberDateTimeEndUsed(Term term,Member member,Day day,LocalTime begin,LocalTime end) {
//		return studyRegisterRepository.existsByTermAndMemberAndDayAndTimeEndBetween(term, member, day, begin, end);
//	}
	
	public Boolean booleanSubject(Term term,Subject subject,String type) {
		return studyRegisterRepository.existsByTermAndSubjectAndType(term, subject, type);
	}
	
//	public List<StudyRegister> StudyRegisterDayMember(Day day , Member member){
//		return studyRegisterRepository.findByDayAndMember(day, member);
//	}
	
//	public List<StudyRegister> studyRegisterTermMember(Term term , Member member){
//		return studyRegisterRepository.findByTermAndMember(term, member);
//	}
	
	public List<StudyRegister> studyRegisterTerm(Term term ){
		return studyRegisterRepository.findByTermAndCapacityTotalNot(term,0);
	}
	
	public List<StudyRegister> studyRegisterTermFac(Term term ,Faculty faculty){
		return studyRegisterRepository.findByTermAndSubjectGroupSubjectFaculty(term,faculty);
	}
	
	
}
