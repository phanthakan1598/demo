package com.example.demo.faculty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.university.model.University;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{
	List<Faculty> findByUniversityAndFacultyNumberNot(University university , String facultyNumber);
	List<Faculty> findByUniversity(University university);
	
	boolean existsByUniversityAndFacultyNameTh(University university, String facultyNameTh);
	boolean existsByUniversityAndFacultyNameEn(University university, String facultyNameEn);
	boolean existsByUniversityAndFacultyCodeName(University university, String facultyCodeName);
	
	boolean existsByUniversity(University university);
	
	Optional<Faculty> findFirstByUniversityOrderByFacultyNumberDesc(University university);
	Optional<Faculty> findOneByFacultyUuid(String facultyUuid);
	Optional<Faculty> findOneByUniversityAndFacultyCodeName(University university, String facultyUuid);
}
