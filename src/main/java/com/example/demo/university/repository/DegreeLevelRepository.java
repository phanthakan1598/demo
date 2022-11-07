package com.example.demo.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.University;

@Repository
public interface DegreeLevelRepository extends JpaRepository<DegreeLevel, Long>{
	Optional<DegreeLevel> findOneByDegreeLevelUuid(String degreeLevelUuid);
//	Optional<DegreeLevel> findOneByUniversity(University  String degreeLevelUuid);
	List<DegreeLevel> findByUniversity (University university);
}
