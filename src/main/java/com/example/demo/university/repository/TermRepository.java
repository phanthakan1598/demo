package com.example.demo.university.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.Term;
import com.example.demo.university.model.University;

@Repository
public interface TermRepository  extends JpaRepository<Term, Long>{
	Optional<Term> findOneByDegreeLevelAndYearAndTerm(DegreeLevel degreeLevel,int year,int term);
	Optional<Term> findOneByTermUuid(String termUuid);
	Optional<Term> findOneByDegreeLevelAndBeginDateLessThanEqualAndEndDateGreaterThanEqual(DegreeLevel degreeLevel,LocalDate deteNows, LocalDate deteNowe);
//	Optional<Term> findOneByDegreeLevelAndBeginDateGreaterThanEqual(DegreeLevel degreeLevel, LocalDate deteNowe);
//	Optional<Term> findOneByDegreeLevelAndBeginDateBeforeAndEndDateAfter(DegreeLevel degreeLevel,LocalDate deteNows,LocalDate deteNowe);
//	Optional<Term> findOneByTermUuid(String termUuid);
	
	boolean existsByDegreeLevelAndBeginDateLessThanEqualAndEndDateGreaterThanEqual(DegreeLevel degreeLevel,LocalDate deteNows, LocalDate deteNowe);
	
	
//	List<Term> findByDegreeLevelAndBeginDateLessThanEqual(DegreeLevel degreeLevel,  LocalDate deteNowe);
	
	boolean existsByDegreeLevelAndYearAndBeginDateBetweenOrEndDateBetween(DegreeLevel degreeLevel,int year,LocalDate begin,LocalDate end,LocalDate begin2,LocalDate end2);
	
	boolean existsByTermIdNotAndDegreeLevelAndYearAndBeginDateBetween(long termId, DegreeLevel degreeLevel,int year,LocalDate begin,LocalDate end);
	boolean existsByTermIdNotAndDegreeLevelAndYearAndEndDateBetween(long termId, DegreeLevel degreeLevel,int year,LocalDate begin,LocalDate end);
	
	boolean existsByDegreeLevelAndYearAndBeginDateBetween(DegreeLevel degreeLevel,int year,LocalDate begin,LocalDate end);
	boolean existsByDegreeLevelAndYearAndEndDateBetween(DegreeLevel degreeLevel,int year,LocalDate begin,LocalDate end);
	
	boolean existsByDegreeLevelAndYearAndTerm(DegreeLevel degreeLevel,int year,int term);
	
	List<Term> findByDegreeLevelOrderByYearDescTermAsc(DegreeLevel degreeLevel);
	
	List<Term> findByDegreeLevelAndYearOrderByTermAsc(DegreeLevel degreeLevel,int year);
	
	List<Term> findByDegreeLevelUniversityAndYearAndTerm(University university,int year, int term);
	
//	List<Term> findByUniversityAndYearBetweenAndYearNotOrderByTermAsc(University university,int yearNow,int yearNext,int yearNot);
}
