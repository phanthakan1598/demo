package com.example.demo.university.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.university.exception.CreditTotalDetException;
import com.example.demo.university.model.CreditTotalDet;
import com.example.demo.university.repository.CreditTotalDetRepository;

import lombok.Setter;

@Service
@Setter
public class CreditTotalDetService {
	@Autowired
	CreditTotalDetRepository creditTotalDetRepository;
	
	public CreditTotalDet findCreditOfTermByUuid(String uuid) throws CreditTotalDetException {
        return creditTotalDetRepository.findOneByCreditTotalDetUuid(uuid).orElseThrow(CreditTotalDetException::creditTotalDetNotFound);
    }
	
	public CreditTotalDet saveCreditTotalDet(CreditTotalDet creditTotalDet) throws CreditTotalDetException {
    	if(Objects.isNull(creditTotalDet)) {
    		throw CreditTotalDetException.creditTotalDetIsEmptyOrNull();
    	}
		return creditTotalDetRepository.save(creditTotalDet);
	}
}
