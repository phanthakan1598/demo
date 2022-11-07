package com.example.demo.register.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.member.model.Member;
import com.example.demo.register.model.Day;
import com.example.demo.register.model.StudyRegister;
import com.example.demo.register.model.StudyRegisterMember;
import com.example.demo.university.model.Term;

@Repository
public interface StudyRegisterMemberRepository extends JpaRepository<StudyRegisterMember, Long>{
	
	Optional<StudyRegisterMember> findOneByStudyRegisterMemberUuid(String studyRegisterMemberUuid);
	
	boolean existsByStudyRegisterAndMember(StudyRegister studyRegister,Member member);
	
	boolean existsByStudyRegisterTermAndMemberAndStudyRegisterDayAndStudyRegisterTimeBeginBetween(Term term,Member member,Day day,LocalTime begin,LocalTime end);
	boolean existsByStudyRegisterTermAndMemberAndStudyRegisterDayAndStudyRegisterTimeEndBetween(Term term,Member member,Day day,LocalTime begin,LocalTime end);
	
	List<StudyRegisterMember> findByStudyRegisterTermAndMember(Term term , Member member);
	List<StudyRegisterMember> findByStudyRegister(StudyRegister studyRegister );
}
