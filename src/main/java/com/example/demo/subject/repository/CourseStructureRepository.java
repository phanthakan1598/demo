package com.example.demo.subject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.subject.model.CourseStructure;
import com.example.demo.university.model.DegreeLevel;

@Repository
public interface CourseStructureRepository extends JpaRepository<CourseStructure, Long>{
	
//	Optional<GroupSubject> findOneByGroupSubjectId(long groupSubjectId);
	Optional<CourseStructure> findOneByCourseStructureUuid(String courseStructureUuid);
//	
	boolean existsByProgram(Program program);
	
	boolean existsByProgramFacultyAndName(Faculty faculty , String name);
	
	boolean existsByProgramDegreeLevel(DegreeLevel degreeLevel);
//	
	List<CourseStructure> findByProgramDegreeLevelAndProgramFaculty(DegreeLevel degreeLevel, Faculty faculty);
	
	List<CourseStructure> findByProgramDegreeLevel(DegreeLevel degreeLevel);
}
