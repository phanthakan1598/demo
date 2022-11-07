package com.example.demo.register.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.register.model.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Long>{
	Optional<Day> findOneByDayUuid(String dayUuid);
}
