package com.example.demo.bill.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.bill.model.Bill;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BillAllJson {
	String billUuid;
	String name;
	
	public static BillAllJson packBillPersonalAllJson(Bill bill) {
		BillAllJson billCourseAllJson = new BillAllJson();
		billCourseAllJson.setBillUuid(bill.getBillUuid());
		billCourseAllJson.setName(bill.getName());
		return billCourseAllJson;
	}
	
	public static List<BillAllJson> billCourseAllJsons(List<Bill> bills){
		List<BillAllJson> billCourseAllJsons = new ArrayList<>();
		for(Bill bill : bills) {
			billCourseAllJsons.add(packBillPersonalAllJson(bill));
		}
		return billCourseAllJsons;
	}
}
