package com.example.demo.bill.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bill.model.Bill;
import com.example.demo.bill.model.BillDet;

@Repository
public interface BillDetRepository extends JpaRepository<BillDet, Long>{
	Optional<BillDet> findOneByBillDetUuid(String billDetUuid);
	boolean existsByNameAndBill(String name,Bill bill);
	List<BillDet> findByBill(Bill bill);
}
