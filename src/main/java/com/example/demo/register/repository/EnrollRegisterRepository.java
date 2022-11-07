package com.example.demo.register.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.member.model.Member;
import com.example.demo.register.model.Day;
import com.example.demo.register.model.EnrollRegister;
import com.example.demo.university.model.Term;

@Repository
public interface EnrollRegisterRepository extends JpaRepository<EnrollRegister, Long>{
	Optional<EnrollRegister> findOneByEnrollRegisterUuid(String enrollRegisterUuid);
	
	List<EnrollRegister> findByMemberAndStudyRegisterTerm(Member member ,Term term );
	
	List<EnrollRegister> findByMemberAndStudyRegisterTermAndEnrollRegisterStatus(Member member ,Term term ,int status);
	
	List<EnrollRegister> findByMemberAndEnrollRegisterStatus(Member member ,int status);
	
	List<EnrollRegister> findByMember(Member member );
	
//	boolean existsByTermAndMemberAndDayAndTimeBeginBetween(Term term,Member member,Day day,LocalTime begin,LocalTime end);
	
	boolean existsByMemberAndStudyRegisterTermAndStudyRegisterDayAndStudyRegisterTimeBeginBetween(Member member ,Term term,Day day, LocalTime begin,LocalTime end);
	boolean existsByMemberAndStudyRegisterTermAndStudyRegisterDayAndStudyRegisterTimeEndBetween(Member member ,Term term,Day day, LocalTime begin,LocalTime end);
	
	boolean existsByMemberAndStudyRegisterTerm(Member member ,Term term );
	
	boolean existsByMember(Member member);
	
	
}
