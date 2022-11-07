//package com.example.demo.university.repository;
//
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.example.demo.faculty.model.Faculty;
//import com.example.demo.faculty.model.Program;
//import com.example.demo.university.model.CreditCriterion;
//import com.example.demo.university.model.DegreeLevel;
//
//@Repository
//public interface CreditCriterionRepository extends JpaRepository<CreditCriterion, Long>{
//	Optional<CreditCriterion> findOneByCreditCriterionUuid(String creditCriterionUuid);
//	List<CreditCriterion> findByProgramFacultyAndProgramDegreeLevelOrderByUpdatedAtDesc(Faculty faculty,DegreeLevel degreeLevel);
//	boolean existsByProgram(Program program);
////	List<CreditCriterion> findByDegreeLevelUniversity(University university);
//}
