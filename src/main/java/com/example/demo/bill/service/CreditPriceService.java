package com.example.demo.bill.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.exception.CreditPriceException;
import com.example.demo.bill.model.CreditPrice;
import com.example.demo.bill.repository.CreditPriceRepository;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.University;

import lombok.Setter;

@Service
@Setter
public class CreditPriceService {
	@Autowired
	CreditPriceRepository creditPriceRepository;
	
	public CreditPrice findCreditPriceByUuid(String uuid) throws CreditPriceException {
        return creditPriceRepository.findOneByCreditPriceUuid(uuid).orElseThrow(CreditPriceException::creditPriceNotFound);
    }
	
	public CreditPrice findCreditPriceByDegree(DegreeLevel degreeLevel) throws CreditPriceException {
        return creditPriceRepository.findOneByDegreeLevel(degreeLevel).orElseThrow(CreditPriceException::creditPriceNotFound);
    }
	
	public CreditPrice saveCreditPrice(CreditPrice creditPrice) throws CreditPriceException {
    	if(Objects.isNull(creditPrice)) {
    		throw CreditPriceException.creditPriceIsEmptyOrNull();
    	}
		return creditPriceRepository.save(creditPrice);
	}
	public void delCreditPrice(CreditPrice creditPrice) throws CreditPriceException {
    	if(Objects.isNull(creditPrice)) {
    		throw CreditPriceException.creditPriceNotFound();
    	}
    	creditPriceRepository.delete(creditPrice);
	}
	public Boolean booleanCreditPrice(DegreeLevel degreeLevel) {
		return creditPriceRepository.existsByDegreeLevel(degreeLevel);
	}
	
	public List<CreditPrice> creditPrices (University university){
		return creditPriceRepository.findByDegreeLevelUniversity(university);
	}
	
}

