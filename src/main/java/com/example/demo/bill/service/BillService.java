package com.example.demo.bill.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.exception.BillException;
import com.example.demo.bill.model.Bill;
import com.example.demo.bill.repository.BillRepository;
import com.example.demo.university.model.DegreeLevel;

import lombok.Setter;

@Service
@Setter
public class BillService {
	@Autowired
	BillRepository billRepository;
	
	public Bill findBillByUuid(String uuid) throws BillException {
        return billRepository.findOneByBillUuid(uuid).orElseThrow(BillException::billNotFound);
    }
	
	public Bill saveBill(Bill bill) throws BillException {
    	if(Objects.isNull(bill)) {
    		throw BillException.billIsEmptyOrNull();
    	}
		return billRepository.save(bill);
	}
	public void delBill(Bill bill) throws BillException {
    	if(Objects.isNull(bill)) {
    		throw BillException.billNotFound();
    	}
    	billRepository.delete(bill);
	}
	
	public Boolean booleanBillName(String name , DegreeLevel degreeLevel) {
		return billRepository.existsByNameAndDegreeLevel(name, degreeLevel);
	}
	
	public List<Bill> bills (DegreeLevel degreeLevel){
		return billRepository.findByDegreeLevel(degreeLevel);
	}
}

