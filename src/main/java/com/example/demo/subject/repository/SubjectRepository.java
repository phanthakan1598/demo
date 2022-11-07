package com.example.demo.subject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.subject.model.GroupSubject;
import com.example.demo.subject.model.Subject;
import com.example.demo.university.model.DegreeLevel;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
	Optional<Subject> findOneBySubjectId(long subjectId);
	Optional<Subject> findOneBySubjectUuid(String subjectUuid);
	Optional<Subject> findOneBySubjectCodeAndGroupSubjectDegreeLevel(String subjectUuid ,DegreeLevel degreeLevel);
	
	boolean existsBySubjectCodeAndGroupSubjectDegreeLevel(String subjectCode,DegreeLevel degreeLevel);
	boolean existsBySubjectCodeAndGroupSubjectDegreeLevelAndSubjectIdNot(String name,DegreeLevel degreeLevel, long subId);
	boolean existsByNameAndGroupSubject(String name,GroupSubject groupSubject);
	boolean existsByGroupSubjectFaculty(Faculty faculty);
	
//	Optional<Subject> findFirstByGroupSubjectFacultyOrderBySubjectNumberDesc(Faculty faculty);
	
	List<Subject> findByGroupSubject(GroupSubject groupSubject);
	
	List<Subject> findByGroupSubjectDegreeLevel(DegreeLevel degreeLevel);
	
	List<Subject> findByGroupSubjectDegreeLevelAndGroupSubjectTypeSubjectTypeSubjectNameEn(DegreeLevel degreeLevel,String type);
}
