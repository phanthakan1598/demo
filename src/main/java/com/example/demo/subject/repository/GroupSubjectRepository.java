package com.example.demo.subject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.subject.model.GroupSubject;
import com.example.demo.subject.model.TypeSubject;
import com.example.demo.university.model.DegreeLevel;

@Repository
public interface GroupSubjectRepository extends JpaRepository<GroupSubject, Long>{
	Optional<GroupSubject> findOneByGroupSubjectId(long groupSubjectId);
	Optional<GroupSubject> findOneByGroupSubjectUuid(String groupSubjectUuid);
	
	boolean existsByNameAndTypeSubjectAndDegreeLevelAndFaculty(String name,TypeSubject typeSubject,DegreeLevel degreeLevel, Faculty faculty );
	
	List<GroupSubject> findByTypeSubjectTypeSubjectNameEnAndDegreeLevelAndFaculty(String typeSubject,DegreeLevel degreeLevel, Faculty faculty);
}
