package com.example.demo.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.university.model.CreditTotalDet;

@Repository
public interface CreditTotalDetRepository extends JpaRepository<CreditTotalDet, Long>{
	Optional<CreditTotalDet> findOneByCreditTotalDetUuid(String creditTotalDetUuid);
}
