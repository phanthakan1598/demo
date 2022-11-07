package com.example.demo.university.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.university.model.RegistrationDate;
import com.example.demo.university.model.Term;

@Repository
public interface RegistrationDateRepository extends JpaRepository<RegistrationDate, Long>{
	Optional<RegistrationDate> findOneByRegistrationDateUuid(String registrationDateUuid);
	Optional<RegistrationDate> findOneByRegistrationDateLevelClassAndRegistrationDateRangeAndTerm(int LevClass, int range ,Term term);
	List<RegistrationDate> findByTerm(Term term);
	
	boolean existsByRegistrationDateLevelClassAndTermAndBeginDateBetween(int LevClass,Term term,LocalDate begin,LocalDate end);
	boolean existsByRegistrationDateLevelClassAndTermAndEndDateBetween(int LevClass,Term term,LocalDate begin,LocalDate end);
	
	boolean existsByRegistrationDateNameAndTerm(String registrationDateName,Term term);
	
	boolean existsByRegistrationDateLevelClassAndRegistrationDateRangeAndTerm(int LevClass, int range ,Term term);
	
	boolean existsByTermAndRegistrationDateLevelClassAndBeginDateLessThanEqualAndEndDateGreaterThanEqual(Term term,int registrationDateLevelClass,LocalDate deteNows, LocalDate deteNowe);
}
