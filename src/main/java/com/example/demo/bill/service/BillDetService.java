package com.example.demo.bill.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.exception.BillDetException;
import com.example.demo.bill.model.Bill;
import com.example.demo.bill.model.BillDet;
import com.example.demo.bill.repository.BillDetRepository;

import lombok.Setter;

@Service
@Setter
public class BillDetService {
	@Autowired
	BillDetRepository billDetRepository;
	
	public BillDet findBillDetByUuid(String uuid) throws BillDetException {
        return billDetRepository.findOneByBillDetUuid(uuid).orElseThrow(BillDetException::billDetNotFound);
    }
	
	public BillDet saveBillDet(BillDet billDet) throws BillDetException {
    	if(Objects.isNull(billDet)) {
    		throw BillDetException.billDetIsEmptyOrNull();
    	}
		return billDetRepository.save(billDet);
	}
	
	public void delBillDet(BillDet billDet) throws BillDetException {
    	if(Objects.isNull(billDet)) {
    		throw BillDetException.billDetIsEmptyOrNull();
    	}
    	billDetRepository.delete(billDet);
	}
	
	public Boolean booleanBillDetName(String name , Bill bill) {
		return billDetRepository.existsByNameAndBill(name, bill);
	}
	
	public List<BillDet> billDets (Bill bill){
		return billDetRepository.findByBill(bill);
	}
}
