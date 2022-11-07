package com.example.demo.bill.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.exception.CreditPriceException;
import com.example.demo.bill.json.CreditPriceAllJson;
import com.example.demo.bill.model.CreditPrice;
import com.example.demo.bill.payload.creditPrice.CreditPriceInsertPayload;
import com.example.demo.bill.payload.creditPrice.CreditPriceUpdatePayload;
import com.example.demo.bill.service.CreditPriceService;
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
public class CreditPriceBusiness {
	
	@Autowired
	CreditPriceService creditPriceService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	LogService logService;
	
	public List<CreditPriceAllJson> viewAllCreditPrice(){
		return CreditPriceAllJson.creditPriceCourseAllJsons(creditPriceService.creditPrices(jwtAuth.getCurrentUser().getUniversity()));
	}
	
	public CreditPriceAllJson viewCreditPrice(String uid) throws CreditPriceException {
		CreditPrice creditPrice = creditPriceService.findCreditPriceByUuid(uid);
		return CreditPriceAllJson.packCreditPricePersonalAllJson(creditPrice);
	}
	
	public void insertCreditPrice(CreditPriceInsertPayload body) throws CreditPriceException, DegreeLevelException, LogException {
		String price = body.getCreditPrice();
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(body.getDegreeLevelUid());
		if(price == null || price.equals("")) {
			throw CreditPriceException.creditPriceEmpty();
		}
		if (creditPriceService.booleanCreditPrice(degreeLevel)) {
			throw CreditPriceException.creditPriceUsed();
		}
		
		CreditPrice creditPrice = new CreditPrice();
		
		creditPrice.setCreditPrice(Float.parseFloat(price));
		creditPrice.setDegreeLevel(degreeLevel);

		creditPriceService.saveCreditPrice(creditPrice);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลค่าหน่วยกิต");
		logService.saveLog(log);
	}
	
	public void updateCreditPrice(CreditPriceUpdatePayload body) throws CreditPriceException, LogException {
		CreditPrice creditPrice = creditPriceService.findCreditPriceByUuid(body.getCreditPriceUuid());
		String price = body.getCreditPrice();
		
		if(price == null || price.equals("")) {
			throw CreditPriceException.creditPriceEmpty();
		}
		creditPrice.setCreditPrice(Float.parseFloat(price));
		creditPriceService.saveCreditPrice(creditPrice);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลค่าหน่วยกิต");
		logService.saveLog(log);
	}

	public void deleteCreditPrice(String uuid) throws CreditPriceException, LogException {
		CreditPrice creditPrice = creditPriceService.findCreditPriceByUuid(uuid);
		creditPriceService.delCreditPrice(creditPrice);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลค่าหน่วยกิต");
		logService.saveLog(log);
	}

}
