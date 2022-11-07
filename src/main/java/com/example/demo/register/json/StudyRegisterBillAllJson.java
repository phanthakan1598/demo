package com.example.demo.register.json;

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
public class StudyRegisterBillAllJson {
	String name;
	float price;
	
	public static StudyRegisterBillAllJson packStudyRegisterBillAllJson(BillDet billDet) {
		StudyRegisterBillAllJson studyRegisterBillAllJson = new StudyRegisterBillAllJson();
		studyRegisterBillAllJson.setName(billDet.getName());
		studyRegisterBillAllJson.setPrice(billDet.getPrice());
		return studyRegisterBillAllJson;
	}
	
	public static List<StudyRegisterBillAllJson> studyRegisterBillAllJsons(List<BillDet> billDets){
		List<StudyRegisterBillAllJson> studyRegisterBillAllJsons = new ArrayList<>();
		for(BillDet billDet : billDets) {
			studyRegisterBillAllJsons.add(packStudyRegisterBillAllJson(billDet));
		}
		return studyRegisterBillAllJsons;
	}
}
