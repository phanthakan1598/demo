package com.example.demo.bill.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bill.model.Bill;
import com.example.demo.university.model.DegreeLevel;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{
	Optional<Bill> findOneByBillUuid(String billUuid);
	boolean existsByNameAndDegreeLevel(String name,DegreeLevel degreeLevel);
	List<Bill> findByDegreeLevel(DegreeLevel degreeLevel);
}
