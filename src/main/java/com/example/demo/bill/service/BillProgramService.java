package com.example.demo.bill.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.exception.BillProgramException;
import com.example.demo.bill.model.Bill;
import com.example.demo.bill.model.BillProgram;
import com.example.demo.bill.repository.BillProgramRepository;
import com.example.demo.faculty.model.Program;
import com.example.demo.university.model.DegreeLevel;

import lombok.Setter;

@Service
@Setter
public class BillProgramService {
	@Autowired
	BillProgramRepository billProgramRepository;
	
	public BillProgram findBillProgramByUuid(String uuid) throws BillProgramException {
        return billProgramRepository.findOneByBillProgramUuid(uuid).orElseThrow(BillProgramException::billProgramNotFound);
    }
	
	public BillProgram findProgram(Program program) throws BillProgramException {
        return billProgramRepository.findOneByProgram(program).orElseThrow(BillProgramException::billProgramNotFound);
    }
	
	public BillProgram saveBillProgram(BillProgram billProgram) throws BillProgramException {
    	if(Objects.isNull(billProgram)) {
    		throw BillProgramException.billProgramIsEmptyOrNull();
    	}
		return billProgramRepository.save(billProgram);
	}
	
	public void delBillProgram(BillProgram billProgram) throws BillProgramException {
    	if(Objects.isNull(billProgram)) {
    		throw BillProgramException.billProgramIsEmptyOrNull();
    	}
    	billProgramRepository.delete(billProgram);
	}
	
	public Boolean booleanBillProgram(Bill bill , Program program) {
		return billProgramRepository.existsByBillAndProgram(bill, program);
	}
	
	public Boolean booleanProgram(Program program) {
		return billProgramRepository.existsByProgram(program);
	}
	
	public List<BillProgram> billPrograms (DegreeLevel degreeLevel){
		return billProgramRepository.findByBillDegreeLevel(degreeLevel);
	}
}
