package com.example.demo.subject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.subject.model.TypeSubject;

@Repository
public interface TypeSubjectRepository extends JpaRepository<TypeSubject, Long>{
	Optional<TypeSubject> findOneByTypeSubjectUuid(String typeSubjectUuid);
}
