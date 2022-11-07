package com.example.demo.member.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.member.model.Log;

public interface LogRepository extends JpaRepository<Log, Long>{
	Optional<Log> findOneByLogUuid(String LogUuid);
	List<Log> findByOrderByCreateAtDesc();
}
