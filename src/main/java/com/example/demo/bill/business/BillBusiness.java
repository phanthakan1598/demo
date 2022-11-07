package com.example.demo.bill.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.exception.BillException;
import com.example.demo.bill.json.BillAllJson;
import com.example.demo.bill.model.Bill;
import com.example.demo.bill.payload.bill.BillInsertPayload;
import com.example.demo.bill.payload.bill.BillUpdatePayload;
import com.example.demo.bill.service.BillService;
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
public class BillBusiness {
	
	@Autowired
	BillService billService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	LogService logService;
	
	public List<BillAllJson> viewAllBill(String uid) throws DegreeLevelException {
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(uid);
		return BillAllJson.billCourseAllJsons(billService.bills(degreeLevel));
	}
	
	public BillAllJson viewBill(String uid) throws BillException {
		Bill bill = billService.findBillByUuid(uid);
		return BillAllJson.packBillPersonalAllJson(bill);
	}
	
	public void insertBill(BillInsertPayload body) throws BillException, DegreeLevelException, LogException {
		
		String name = body.getName();
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(body.getDegreeLevelUid());
		if(name == null || name.equals("")) {
			throw BillException.billNameEmpty();
		}
		if (billService.booleanBillName(name, degreeLevel)) {
			throw BillException.billNameUsed();
		}
		
		Bill bill = new Bill();
		bill.setName(name);
		bill.setDegreeLevel(degreeLevel);
		billService.saveBill(bill);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลผังค่าใช้จ่าย");
		logService.saveLog(log);
	}
	
	public void updateBill(BillUpdatePayload body) throws BillException, LogException {
		Bill bill = billService.findBillByUuid(body.getBillUid());
		String name = body.getName();
		String nameOld = bill.getName();
		DegreeLevel degreeLevel = bill.getDegreeLevel();
		
		if(!name.equals(nameOld)) {
			if (billService.booleanBillName(name, degreeLevel)) {
				throw BillException.billNameUsed();
			}
		}
		
		if(name == null || name.equals("")) {
			throw BillException.billNameEmpty();
		}
		bill.setName(name);
		billService.saveBill(bill);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลผังค่าใช้จ่าย");
		logService.saveLog(log);
	}

	public void deleteBill(String uuid) throws BillException, LogException {
		Bill bill = billService.findBillByUuid(uuid);
		billService.delBill(bill);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลผังค่าใช้จ่าย");
		logService.saveLog(log);
		
	}

}
