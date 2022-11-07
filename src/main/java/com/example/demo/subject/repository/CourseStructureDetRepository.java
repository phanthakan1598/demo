package com.example.demo.subject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.subject.model.CourseStructure;
import com.example.demo.subject.model.CourseStructureDet;
import com.example.demo.subject.model.Subject;
import com.example.demo.university.model.DegreeLevel;

@Repository
public interface CourseStructureDetRepository extends JpaRepository<CourseStructureDet, Long>{
	
	
//	Optional<GroupSubject> findOneByGroupSubjectId(long groupSubjectId);
	Optional<CourseStructureDet> findOneByCourseStructureDetUuid(String courseStructureDetUuid);
//	
	boolean existsBySubjectAndCourseStructure(Subject subject,CourseStructure courseStructure);
//	
	List<CourseStructureDet> findByCourseStructureProgramDegreeLevelAndCourseStructureProgramFacultyAndSubjectGroupSubjectTypeSubjectTypeSubjectNameEn(DegreeLevel degreeLevel,Faculty faculty,String typeSubject);
	List<CourseStructureDet> findByCourseStructureProgramDegreeLevelAndSubjectGroupSubjectTypeSubjectTypeSubjectNameEn(DegreeLevel degreeLevel,String typeSubject);
	List<CourseStructureDet> findByCourseStructureProgramDegreeLevelAndCourseStructureProgramFaculty(DegreeLevel degreeLevel,Faculty faculty);
	List<CourseStructureDet> findByCourseStructure(CourseStructure courseStructure);
}
