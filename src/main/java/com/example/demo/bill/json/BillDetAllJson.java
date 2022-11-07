package com.example.demo.bill.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.bill.model.BillDet;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BillDetAllJson {
	String billDetUuid;
	String name;
	String price;
	
	public static BillDetAllJson packBillDetPersonalAllJson(BillDet billDet) {
		BillDetAllJson billDetCourseAllJson = new BillDetAllJson();
		billDetCourseAllJson.setBillDetUuid(billDet.getBillDetUuid());
		billDetCourseAllJson.setName(billDet.getName());
		billDetCourseAllJson.setPrice(String.format("%.2f", billDet.getPrice()));
		return billDetCourseAllJson;
	}
	
	public static List<BillDetAllJson> billDetCourseAllJsons(List<BillDet> billDets){
		List<BillDetAllJson> billDetCourseAllJsons = new ArrayList<>();
		for(BillDet billDet : billDets) {
			billDetCourseAllJsons.add(packBillDetPersonalAllJson(billDet));
		}
		return billDetCourseAllJsons;
	}
}
