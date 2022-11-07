package com.example.demo.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.university.model.Calendar;
import com.example.demo.university.model.Term;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long>{
	Optional<Calendar> findOneByCalendarUuid(String calendarUuid);
	Optional<Calendar> findOneByNameAndTerm(String name,Term term);
	List<Calendar> findByTermOrderByBeginDateAscEndDateAsc(Term term);
//	List<Calendar> findByTermUniversityAndDegreeLevelAndTermOrderByBeginDateAsc(University university,DegreeLevel degreeLevel,Term term); 
}
