package com.example.demo.bill.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bill.model.Bill;
import com.example.demo.bill.model.BillProgram;
import com.example.demo.faculty.model.Program;
import com.example.demo.university.model.DegreeLevel;

@Repository
public interface BillProgramRepository extends JpaRepository<BillProgram, Long>{
	Optional<BillProgram> findOneByBillProgramUuid(String billProgramUuid);
	Optional<BillProgram> findOneByProgram(Program program);
	boolean existsByBillAndProgram(Bill bill,Program program);
	boolean existsByProgram(Program program);
	List<BillProgram> findByBillDegreeLevel(DegreeLevel degreeLevel);
//	List<BillProgram> findByBillDegreeLevel(DegreeLevel degreeLevel);
}