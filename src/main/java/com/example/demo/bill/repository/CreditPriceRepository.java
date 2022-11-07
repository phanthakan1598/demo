package com.example.demo.bill.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bill.model.CreditPrice;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.University;

@Repository
public interface CreditPriceRepository extends JpaRepository<CreditPrice, Long>{
	Optional<CreditPrice> findOneByCreditPriceUuid(String creditPriceUuid);
	Optional<CreditPrice> findOneByDegreeLevel(DegreeLevel degreeLevel);
	boolean existsByDegreeLevel(DegreeLevel degreeLevel);
	List<CreditPrice> findByDegreeLevelUniversity(University university);
}
