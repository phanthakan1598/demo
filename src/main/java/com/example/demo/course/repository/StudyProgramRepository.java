package com.example.demo.course.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.course.model.StudyProgram;
import com.example.demo.faculty.model.Program;
import com.example.demo.subject.model.CourseStructure;
import com.example.demo.university.model.DegreeLevel;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long>{
	Optional<StudyProgram> findOneByStudyProgramUuid(String studyProgramUuid);
	
	Optional<StudyProgram> findOneByCourseStructureProgram(Program program);
	
	boolean existsByCourseStructure(CourseStructure courseStructure);
	boolean existsByNameAndCourseStructure(String name,CourseStructure courseStructure);
	List<StudyProgram> findByCourseStructureProgramDegreeLevel (DegreeLevel degreeLevel);
	
//	List<StudyProgram> findByCourseStructureProgramDegreeLevelAndCourseStructureProgramFacultyAnd(DegreeLevel degreeLevel, Faculty faculty, String typeSub);
}

