package com.example.demo.bill.business;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.exception.BillDetException;
import com.example.demo.bill.exception.BillException;
import com.example.demo.bill.json.BillDetAllJson;
import com.example.demo.bill.model.Bill;
import com.example.demo.bill.model.BillDet;
import com.example.demo.bill.payload.billDet.BillDetInsertPayload;
import com.example.demo.bill.payload.billDet.BillDetUpdatePayload;
import com.example.demo.bill.service.BillDetService;
import com.example.demo.bill.service.BillService;
import com.example.demo.bill.service.CreditPriceService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;

import lombok.Setter;

@Service
@Setter
public class BillDetBusiness {
	
	@Autowired
	BillDetService billDetService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	CreditPriceService creditPriceService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	
	public List<BillDetAllJson> viewAllBillDet(String uid) throws BillException {
		Bill bill = billService.findBillByUuid(uid);
		return BillDetAllJson.billDetCourseAllJsons(billDetService.billDets(bill));
	}
	
	public BillDetAllJson viewBillDet(String uid) throws BillDetException {
		BillDet billDet = billDetService.findBillDetByUuid(uid);
		return BillDetAllJson.packBillDetPersonalAllJson(billDet);
	}
	
	public void insertBillDet(BillDetInsertPayload body)throws BillDetException, BillException, LogException {
		String name = body.getName();
		String price = body.getPrice();
		Bill bill = billService.findBillByUuid(body.getBillUid());
		
		if(name == null || name.equals("")) {
			throw BillDetException.billDetNameEmpty();
		}
		if(price == null || price.equals("")) {
			throw BillDetException.billDetNameEmpty();
		}
		if (billDetService.booleanBillDetName(name, bill)) {
			throw BillDetException.billDetNameUsed();
		}
		
		BillDet billDet = new BillDet();
		billDet.setName(name);
		billDet.setPrice(Float.parseFloat(price));
		billDet.setBill(bill);
		
		billDetService.saveBillDet(billDet);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลรายละเอียดผังค่าใช้จ่าย");
		logService.saveLog(log);
	}
	
	public void updateBillDet(BillDetUpdatePayload body)throws BillDetException, BillException, LogException {
		BillDet billDet = billDetService.findBillDetByUuid(body.getBillDetUid());
		String name = body.getName();
		String nameOld = billDet.getName();
		float price = body.getPrice();
		Bill bill = billDet.getBill();
		
		if(name == null || name.equals("")) {
			throw BillDetException.billDetNameEmpty();
		}
		if(Objects.isNull(price)) {
			throw BillDetException.billDetPriceIsEmptyOrNull();
		}
		if(!name.equals(nameOld)) {
			if (billDetService.booleanBillDetName(name, bill)) {
				throw BillDetException.billDetNameUsed();
			}
		}
		
		billDet.setName(name);
		billDet.setPrice(price);
		billDet.setBill(bill);
		
		billDetService.saveBillDet(billDet);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลรายละเอียดผังค่าใช้จ่าย");
		logService.saveLog(log);

	}
	
	public void delBillDet(String uid)throws BillDetException, LogException {
		BillDet billDet = billDetService.findBillDetByUuid(uid);
		billDetService.delBillDet(billDet);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลรายละเอียดผังค่าใช้จ่าย");
		logService.saveLog(log);
	}
}
