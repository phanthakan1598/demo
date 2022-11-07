package com.example.demo.university.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.university.model.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long>{
	Optional<University> findOneByUniversityUuid(String UniversityUuid);
	
	Optional<University> findFirstByOrderByUniversityNumberDesc();
	
	boolean existsByUniversityNameTh(String universityNameTh);
	boolean existsByUniversityNameEn(String universityNameEn);
	boolean existsByUniversityCodeName(String universityCodeName);
	
	
	
	<T>Optional<T> findOneByUniversityUuid(String UniversityUuid, Class<T> type);
}
