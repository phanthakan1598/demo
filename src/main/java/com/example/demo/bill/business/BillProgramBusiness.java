package com.example.demo.bill.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.exception.BillException;
import com.example.demo.bill.exception.BillProgramException;
import com.example.demo.bill.json.BillProgramAllJson;
import com.example.demo.bill.model.Bill;
import com.example.demo.bill.model.BillProgram;
import com.example.demo.bill.payload.billProgram.BillProgramInsertPayload;
import com.example.demo.bill.payload.billProgram.BillProgramUpdatePayload;
import com.example.demo.bill.service.BillProgramService;
import com.example.demo.bill.service.BillService;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.faculty.model.Program;
import com.example.demo.faculty.service.ProgramService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.service.DegreeLevelService;

import lombok.Setter;

@Service
@Setter
public class BillProgramBusiness {
	@Autowired
	BillProgramService billProgramService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	ProgramService programService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	public List<BillProgramAllJson> viewAllBillProgram(String uid) throws DegreeLevelException {
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(uid);
		return BillProgramAllJson.billProgramCourseAllJsons(billProgramService.billPrograms(degreeLevel));
	}
	
	public BillProgramAllJson viewBillProgram(String uid) throws BillProgramException  {
		BillProgram billProgram = billProgramService.findBillProgramByUuid(uid);
		return BillProgramAllJson.packBillProgramPersonalAllJson(billProgram);
	}
	
	public void insertBillProgram(BillProgramInsertPayload body)throws BillProgramException, BillException, ProgramException, LogException {
		Bill bill = billService.findBillByUuid(body.getBillUid());
		Program program = programService.findProgramByUuid(body.getProgramUid());
		
		String billDegreeLevel = bill.getDegreeLevel().getDegreeLevelUuid();
		String programDegreeLevel = program.getDegreeLevel().getDegreeLevelUuid();
		
		if(!billDegreeLevel.equals(programDegreeLevel)) {
			throw BillProgramException.billProgramIncorrect();
		}
		if(billProgramService.booleanBillProgram(bill, program)) {
			throw BillProgramException.billProgramUsed();
		}
		
		BillProgram billProgram = new BillProgram();
		billProgram.setBill(bill);
		billProgram.setProgram(program);
		
		billProgramService.saveBillProgram(billProgram);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลผังค่าใช้จ่ายในสาขา");
		logService.saveLog(log);
	}
	
	public void updateBillProgram(BillProgramUpdatePayload body) throws BillProgramException, BillException, ProgramException, LogException{
		BillProgram billProgram = billProgramService.findBillProgramByUuid(body.getBillProgramUid());
		Bill bill = billService.findBillByUuid(body.getBillUid());
		Program program = programService.findProgramByUuid(body.getProgramUid());
		
		Bill billOld = billProgram.getBill();
		Program programOld = billProgram.getProgram();
		
		String billDegreeLevel = bill.getDegreeLevel().getDegreeLevelUuid();
		String programDegreeLevel = program.getDegreeLevel().getDegreeLevelUuid();
		
		if (!bill.equals(billOld) || !program.equals(programOld)) {
			if(billProgramService.booleanBillProgram(bill, program)) {
				throw BillProgramException.billProgramUsed();
			}
		}
		
		if(!billDegreeLevel.equals(programDegreeLevel)) {
			throw BillProgramException.billProgramIncorrect();
		}
		
		billProgram.setBill(bill);
		billProgram.setProgram(program);
		
		billProgramService.saveBillProgram(billProgram);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลผังค่าใช้จ่ายในสาขา");
		logService.saveLog(log);
	}
	
	public void delBillProgram(String uid)throws BillProgramException, LogException {
		BillProgram billProgram = billProgramService.findBillProgramByUuid(uid);
		billProgramService.delBillProgram(billProgram);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลผังค่าใช้จ่ายในสาขา");
		logService.saveLog(log);
	}
}
