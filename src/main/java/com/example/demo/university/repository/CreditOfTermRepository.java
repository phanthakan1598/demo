package com.example.demo.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.university.model.CreditOfTerm;
import com.example.demo.university.model.DegreeLevel;

@Repository
public interface CreditOfTermRepository extends JpaRepository<CreditOfTerm, Long>{
	Optional<CreditOfTerm> findOneByCreditOfTermUuid(String creditOfTermUuid);
	
	Optional<CreditOfTerm> findOneByProgram(Program program);
	
	boolean existsByProgram(Program program);
	List<CreditOfTerm> findByProgramFacultyAndProgramDegreeLevel(Faculty faculty,DegreeLevel degreeLevel);
	
	List<CreditOfTerm> findByProgram(Program program);
	
}
