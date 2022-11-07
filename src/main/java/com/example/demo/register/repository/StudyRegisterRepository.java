package com.example.demo.register.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.building.model.Room;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.register.model.Day;
import com.example.demo.register.model.StudyRegister;
import com.example.demo.subject.model.Subject;
import com.example.demo.university.model.Term;

@Repository
public interface StudyRegisterRepository extends JpaRepository<StudyRegister, Long>{
	Optional<StudyRegister> findFirstByTermAndAndSubjectAndTypeOrderByStudyRegisterIdDesc(Term term,Subject subject,String type);
	
	Optional<StudyRegister> findOneByStudyRegisterUuid(String studyRegisterUuid);
	
	boolean existsByTermAndRoomAndDayAndTimeBeginBetween(Term term,Room room,Day day,LocalTime begin,LocalTime end);
	boolean existsByTermAndRoomAndDayAndTimeEndBetween(Term term,Room room,Day day,LocalTime begin,LocalTime end);
	
//	boolean existsByTermAndMemberAndDayAndTimeBeginBetween(Term term,Member member,Day day,LocalTime begin,LocalTime end);
//	boolean existsByTermAndMemberAndDayAndTimeEndBetween(Term term,Member member,Day day,LocalTime begin,LocalTime end);
	
	boolean existsByTermAndSubjectAndType(Term term,Subject subject,String type);
	
//	List<StudyRegister> findByDayAndMember(Day day , Member member);
//	
//	List<StudyRegister> findByTermAndMember(Term term , Member member);
	
	List<StudyRegister> findByTermAndCapacityTotalNot(Term term,int capacityTotal);
	
	List<StudyRegister> findByTermAndSubjectGroupSubjectFaculty(Term term, Faculty faculty);
}
