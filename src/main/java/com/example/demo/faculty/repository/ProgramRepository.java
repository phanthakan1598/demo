package com.example.demo.faculty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.university.model.DegreeLevel;

public interface ProgramRepository extends JpaRepository<Program, Long>{
	List<Program> findByFacultyAndDegreeLevel(Faculty faculty,DegreeLevel degreeLevel);
	Optional<Program> findOneByProgramUuid(String programUuid);
	
	boolean existsByFacultyAndDegreeLevelAndProgramNameTh(Faculty university, DegreeLevel degreeLevel, String programNameTh);
	boolean existsByFacultyAndDegreeLevelAndProgramNameEn(Faculty university, DegreeLevel degreeLevel, String programNameEn);
	boolean existsByFacultyAndDegreeLevelAndProgramCodeName(Faculty university, DegreeLevel degreeLevel, String programCodeName);
}
